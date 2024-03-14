package Progetto.BoatsItalia.BoatsItalia.service;

import Progetto.BoatsItalia.BoatsItalia.model.entities.User;
import Progetto.BoatsItalia.BoatsItalia.model.enums.UserRole;
import Progetto.BoatsItalia.BoatsItalia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser (User user){
        if (userRepository.findByUsername(user.getUsername()) != null){
            throw new IllegalArgumentException("Username already exist");
        }
        user.setPassword(Arrays.toString(passwordEncoder.encode(user.getPassword()).toCharArray()));
        user.setRole(UserRole.USER);
        userRepository.save(user);
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

    public String login(String username, String password){
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())){
            return "ok";
        }
        return null;
    }
}
