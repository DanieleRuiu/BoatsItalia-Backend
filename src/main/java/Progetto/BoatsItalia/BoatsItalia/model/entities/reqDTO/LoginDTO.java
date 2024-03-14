package Progetto.BoatsItalia.BoatsItalia.model.entities.reqDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Record Java che rappresenta i dati di input per il login
public record LoginDTO(
        // Annotazione che specifica che il campo 'email' non deve essere vuoto e deve essere un indirizzo email valido
        @NotBlank(message = "'email' is required and must not be empty")
        @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "malformed 'email' field")
        String email,

        // Annotazione che specifica che il campo 'password' non deve essere vuoto e deve contenere almeno 8 caratteri
        @NotBlank(message = "'password' is required and must not be empty")
        @Size(min = 8, message = "'password' must contain at least 8 characters")
        String password
) {} // Fine della definizione del record
