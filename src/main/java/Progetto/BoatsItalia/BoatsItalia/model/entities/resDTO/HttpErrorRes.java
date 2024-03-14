package Progetto.BoatsItalia.BoatsItalia.model.entities.resDTO;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data // Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString
public class HttpErrorRes {
    private Timestamp timestamp; // Campo per contenere il timestamp della risposta
    private int statusCode; // Campo per contenere il codice di stato della risposta HTTP
    private String error; // Campo per contenere la descrizione dell'errore
    private String message; // Campo per contenere il messaggio dell'errore

    // Costruttore che accetta un HttpStatus, una stringa di errore e un messaggio per inizializzare gli attributi della classe
    public HttpErrorRes(HttpStatus httpStatus, String error, String message) {
        timestamp = Timestamp.valueOf(LocalDateTime.now()); // Imposta il timestamp attuale
        statusCode = httpStatus.value(); // Imposta il codice di stato HTTP dal valore di HttpStatus
        this.error = error; // Imposta la stringa di errore
        this.message = message; // Imposta il messaggio dell'errore
    }
}
