package Progetto.BoatsItalia.BoatsItalia.model.entities.reqDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

// Record Java che rappresenta i dati di input per l'iscrizione
public record SubscriptionDTO(
        // Annotazione che specifica che il campo 'userId' non deve essere nullo e deve seguire un pattern specifico per un UUID
        @NotNull(message = "'userId' is required")
        @Pattern(regexp = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$",
                message = "'userId' field is malformed since it doesn't respect the Universal Unique ID pattern"
        )
        String userId
) {} // Fine della definizione del record
