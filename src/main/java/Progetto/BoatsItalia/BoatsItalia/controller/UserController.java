package Progetto.BoatsItalia.BoatsItalia.controller;

import Progetto.BoatsItalia.BoatsItalia.model.entities.User;
import Progetto.BoatsItalia.BoatsItalia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController // Indica che questa classe è un controller REST
@RequestMapping("/api/users") // Mappatura di base per le richieste HTTP in questo controller
@CrossOrigin(origins="http://localhost:4200",maxAge = 3600 )
public class UserController {
    @Autowired
    private UserService userService; // Iniezione di dipendenza del servizio UserService

    @PostMapping // Mappatura per le richieste POST su /api/users
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User responseUser = userService.registerUser(user); // Chiama il metodo del servizio per registrare un utente
        return ResponseEntity.ok(responseUser); // Restituisce una risposta HTTP 200 OK senza contenuto
    }

    @GetMapping("/{username}") // Mappatura per le richieste GET su /api/users/{username}
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username); // Ottiene un utente per il nome utente specificato
        if (user != null) {
            return ResponseEntity.ok(user); // Restituisce l'utente trovato con una risposta HTTP 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Restituisce una risposta HTTP 404 Not Found se l'utente non è stato trovato
        }
    }

    @PutMapping("/{userId}") // Mappatura per le richieste PUT su /api/users/{userId}
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody User user) {
        user.setId(userId); // Imposta l'ID dell'utente con quello fornito nella richiesta
        userService.updateUser(user); // Chiama il metodo del servizio per aggiornare l'utente
        return ResponseEntity.ok().build(); // Restituisce una risposta HTTP 200 OK senza contenuto
    }

    @DeleteMapping("/{userId}") // Mappatura per le richieste DELETE su /api/users/{userId}
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId); // Chiama il metodo del servizio per eliminare l'utente
        return ResponseEntity.ok().build(); // Restituisce una risposta HTTP 200 OK senza contenuto
    }

    @PostMapping("/login") // Mappatura per le richieste POST su /api/users/login
    public ResponseEntity<User> login(@RequestBody User user) {
        User loggedUser = userService.login(user.getUsername(), user.getPassword()); // Effettua il login dell'utente
        if (loggedUser != null) {
            return ResponseEntity.ok(loggedUser); // Restituisce il nome utente dell'utente loggato con una risposta HTTP 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Restituisce una risposta HTTP 404 Not Found se il login non ha avuto successo
        }
    }

    @DeleteMapping("/logout") // Mappatura per le richieste DELETE su /api/users/logout
    public ResponseEntity<User> logout(@RequestBody User user) {
        // Implementazione del logout dell'utente (potrebbe richiedere azioni aggiuntive)
        return ResponseEntity.ok().build(); // Restituisce una risposta HTTP 200 OK senza contenuto
    }
}


