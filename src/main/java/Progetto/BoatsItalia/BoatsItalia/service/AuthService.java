package Progetto.BoatsItalia.BoatsItalia.service;

import Progetto.BoatsItalia.BoatsItalia.exception.InternalServerErrorException;
import Progetto.BoatsItalia.BoatsItalia.exception.UnauthorizedException;
import Progetto.BoatsItalia.BoatsItalia.model.entities.User;
import Progetto.BoatsItalia.BoatsItalia.model.entities.reqDTO.UserDTO;
import Progetto.BoatsItalia.BoatsItalia.model.entities.resDTO.AccessTokenRes;
import Progetto.BoatsItalia.BoatsItalia.model.enums.UserRole;
import Progetto.BoatsItalia.BoatsItalia.repository.UserRepository;
import Progetto.BoatsItalia.BoatsItalia.security.JwtTools;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRp;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtTools jwtTools;

    // Metodo per registrare un nuovo utente
    public User register(UserDTO userDTO) throws BadRequestException, InternalServerErrorException {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setHashPassword(encoder.encode(userDTO.getPassword())); // Codifica la password prima di salvarla
        user.setRole(UserRole.USER); // Imposta il ruolo dell'utente come utente standard
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());

        try {
            return userRp.save(user); // Salva l'utente nel repository degli utenti
        } catch (DataIntegrityViolationException e) {
            // Gestisce le eccezioni di violazione dell'integrità dei dati
            if (userRp.getAllEmails().contains(user.getEmail()))
                throw new BadRequestException("'email' already exists, cannot register");
            throw new InternalServerErrorException("Data Integrity violation error. " + e.getMessage());
        }
    }

    // Metodo per trovare un utente per ID
    public Optional<User> findUserById(Long id) {
        return userRp.findById(id);
    }

    // Metodo per effettuare il login
    public AccessTokenRes login(String email, String password) throws UnauthorizedException {
        // Cerca l'utente per l'email fornita
        User user = (User) userRp.findByEmail(email).orElseThrow(
                () -> new UnauthorizedException("Email and/or password are incorrect")
        );
        // Verifica se la password fornita corrisponde alla password dell'utente
        if (!encoder.matches(password, user.getHashPassword()))
            throw new UnauthorizedException("Email and/or password are incorrect");
        // Se le credenziali sono corrette, crea e restituisce un nuovo token di accesso
        return new AccessTokenRes(jwtTools.createToken(user));
    }
}
