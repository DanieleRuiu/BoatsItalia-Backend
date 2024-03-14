package Progetto.BoatsItalia.BoatsItalia.model.entities.reqDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterDTO(
        @NotBlank(message = "Il nome utente è obbligatorio e non può essere vuoto.")
        String username,

        @NotBlank(message = "L'email è obbligatoria e non può essere vuota.")
        @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Il campo 'email' non è valido.")
        String email,

        @NotBlank(message = "La password è obbligatoria e non può essere vuota.")
        @Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=!])(?=\\S+$).{8,}$",
                message = "La password deve essere lunga almeno 8 caratteri e contenere almeno un numero, una lettera maiuscola, una minuscola e un carattere speciale.")
        String password
) {}
