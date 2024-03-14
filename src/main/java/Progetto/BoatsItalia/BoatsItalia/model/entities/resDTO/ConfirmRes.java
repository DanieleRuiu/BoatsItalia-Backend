package Progetto.BoatsItalia.BoatsItalia.model.entities.resDTO;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data // Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString
public class ConfirmRes {
    private Timestamp timestamp; // Campo per contenere il timestamp della risposta
    private int statusCode; // Campo per contenere il codice di stato della risposta HTTP
    private String message; // Campo per contenere il messaggio della risposta

    // Costruttore che accetta un messaggio e un HttpStatus per inizializzare gli attributi della classe
    public ConfirmRes(String message, HttpStatus httpStatus) {
        this.message = message; // Imposta il messaggio della risposta
        timestamp = Timestamp.valueOf(LocalDateTime.now()); // Imposta il timestamp attuale
        statusCode = httpStatus.value(); // Imposta il codice di stato HTTP dal valore di HttpStatus
    }
}
