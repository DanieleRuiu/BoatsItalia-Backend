package Progetto.BoatsItalia.BoatsItalia.controller;

import Progetto.BoatsItalia.BoatsItalia.exception.BadRequestException;
import Progetto.BoatsItalia.BoatsItalia.exception.InternalServerErrorException;
import Progetto.BoatsItalia.BoatsItalia.exception.UnauthorizedException;
import Progetto.BoatsItalia.BoatsItalia.exception.ValidationMessages;
import Progetto.BoatsItalia.BoatsItalia.model.entities.User;
import Progetto.BoatsItalia.BoatsItalia.model.entities.reqDTO.LoginDTO;
import Progetto.BoatsItalia.BoatsItalia.model.entities.reqDTO.UserDTO;
import Progetto.BoatsItalia.BoatsItalia.model.entities.resDTO.AccessTokenRes;
import Progetto.BoatsItalia.BoatsItalia.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indica che questa classe è un controller REST
public class AuthController {

    @Autowired
    private AuthService authSvc; // Iniezione di dipendenza del servizio AuthService

    @PostMapping("/auth/register") // Mappatura per le richieste POST su /auth/register
    public User register(@RequestBody @Validated UserDTO userDTO, BindingResult validation) throws BadRequestException, InternalServerErrorException, org.apache.coyote.BadRequestException {
        // Metodo per la registrazione di un nuovo utente
        if (validation.hasErrors()) // Se ci sono errori di validazione nei dati inviati
            throw new BadRequestException(ValidationMessages.generateValidationErrorMessage(validation)); // Solleva un'eccezione BadRequestException con i messaggi di validazione
        return authSvc.register(userDTO); // Chiama il metodo del servizio per registrare un nuovo utente
    }

    @PostMapping("/auth/login") // Mappatura per le richieste POST su /auth/login
    public AccessTokenRes login(@RequestBody @Validated LoginDTO loginDTO, BindingResult validation) throws BadRequestException, UnauthorizedException, UnauthorizedException {
        // Metodo per l'autenticazione di un utente
        if (validation.hasErrors()) // Se ci sono errori di validazione nei dati inviati
            throw new BadRequestException(ValidationMessages.generateValidationErrorMessage(validation)); // Solleva un'eccezione BadRequestException con i messaggi di validazione
        return authSvc.login(loginDTO.email(), loginDTO.password()); // Chiama il metodo del servizio per il login di un utente
    }

}
