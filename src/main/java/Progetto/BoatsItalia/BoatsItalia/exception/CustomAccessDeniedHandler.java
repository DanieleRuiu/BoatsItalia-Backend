package Progetto.BoatsItalia.BoatsItalia.exception;

import Progetto.BoatsItalia.BoatsItalia.model.entities.resDTO.HttpErrorRes;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    // Metodo per gestire l'accesso negato
    @Override
    public void handle(HttpServletRequest req,
                       HttpServletResponse res,
                       AccessDeniedException accessDeniedException)  {

        // Creazione di un oggetto ObjectMapper per convertire oggetti in formato JSON
        ObjectMapper mapper = new ObjectMapper();

        // Impostazione del tipo di contenuto della risposta come JSON
        res.setContentType("application/json;charset=UTF-8");

        // Impostazione dello stato della risposta come UNAUTHORIZED (401)
        res.setStatus(HttpStatus.UNAUTHORIZED.value());

        try {
            // Scrittura dell'oggetto HttpErrorRes come JSON nella risposta
            res.getWriter().write(mapper.writeValueAsString(
                    new HttpErrorRes(HttpStatus.UNAUTHORIZED, "Unauthorized",
                            "You don't have permissions to access this resource")));
        } catch (IOException e) {
            // Se si verifica un errore durante la scrittura della risposta, viene sollevata un'eccezione di runtime
            throw new RuntimeException(e.getMessage());
        }
    }
}
