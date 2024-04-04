package Progetto.BoatsItalia.BoatsItalia.service;

import Progetto.BoatsItalia.BoatsItalia.model.entities.User;
import Progetto.BoatsItalia.BoatsItalia.model.enums.UserRole;
import Progetto.BoatsItalia.BoatsItalia.repository.UserRepository;
import Progetto.BoatsItalia.BoatsItalia.security.JwtFilter;
import Progetto.BoatsItalia.BoatsItalia.security.JwtTools;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passEncoder;
    @Autowired
    private JwtTools jwt;

    public User registerUser (User user){
        if (userRepository.findByUsername(user.getUsername()) != null){
            throw new IllegalArgumentException("Username already exist");
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("SERVICE "+mapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        user.setPassword(passEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        User userResp = userRepository.save(user);

        String token = jwt.createToken(user);

        userResp.setToken(token);
        return userResp;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

   public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //metodo per login

    public User login(String username, String password){
        User user = userRepository.findByUsername(username);
        System.out.println("USER: "+user.getId());
        System.out.println("USER: "+passEncoder.matches(password, user.getPassword()));
        if (user != null && passEncoder.matches(password, user.getPassword())){
            String token = jwt.createToken(user);
            user.setToken(token);
            return user;
        }
        return null;
    }
}
