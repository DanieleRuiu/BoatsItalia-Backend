package Progetto.BoatsItalia.BoatsItalia.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class ValidationMessages {

    // Metodo per generare un messaggio di errore di validazione a partire da un BindingResult
    public static String generateValidationErrorMessage(BindingResult validation) {
        // Utilizza lo stream per mappare ogni errore in un messaggio di errore e unirli in un'unica stringa
        return validation.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage) // Estrae il messaggio di errore da ogni oggetto errore
                .collect(Collectors.joining("; ")); // Unisce i messaggi di errore separati da un punto e virgola
    }
}
