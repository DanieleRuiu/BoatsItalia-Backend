package Progetto.BoatsItalia.BoatsItalia.controller;

import Progetto.BoatsItalia.BoatsItalia.model.User;
import Progetto.BoatsItalia.BoatsItalia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok().build();

    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody User user) {
        user.setId(userId);
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User loggedUser = userService.login(user.getUsername(), user.getPassword());
        if (loggedUser != null) {
            return ResponseEntity.ok(loggedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

