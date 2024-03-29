package Progetto.BoatsItalia.BoatsItalia.security;

import Progetto.BoatsItalia.BoatsItalia.exception.UnauthorizedException;
import Progetto.BoatsItalia.BoatsItalia.model.entities.User;
import Progetto.BoatsItalia.BoatsItalia.model.entities.resDTO.HttpErrorRes;
import Progetto.BoatsItalia.BoatsItalia.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private AuthService authSvc;

    // Questo metodo viene eseguito per ogni richiesta HTTP
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Requesting check for JWT");
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.writeValueAsString(req));
            System.out.println(req.getHeaders("Authorization"));
            String authorization = req.getHeader("Authorization"); // Ottiene l'intestazione Authorization dalla richiesta
            System.out.println("Authorization: " + authorization);

            if(!req.getRequestURL().toString().contains("auth")){


            if (authorization == null) {
                System.out.println("No provided access token");
                throw new UnauthorizedException("No provided access token");
            } // Se l'intestazione Authorization è null, solleva un'eccezione UnauthorizedException
            else if (!authorization.startsWith("Bearer "))
                throw new UnauthorizedException("malformed 'Authorization' header"); // Se l'intestazione Authorization non inizia con "Bearer ", solleva un'eccezione UnauthorizedException

            String token = authorization.split(" ")[1]; // Estrae il token JWT dall'intestazione Authorization

            jwtTools.validateToken(token); // Valida il token JWT utilizzando JwtTools

            Long userId = jwtTools.extractUserIdFromToken(token); // Estrae l'ID utente dal token JWT

            User u = authSvc.findUserById(userId).orElseThrow(
                    () -> new UnauthorizedException("Invalid access token") // Trova l'utente corrispondente all'ID utente estratto dal token JWT; se l'utente non è trovato, solleva un'eccezione UnauthorizedException
            );

            // Imposta l'autenticazione corrente con l'utente trovato
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(req, res); // Prosegue nella catena dei filtri

            }


        } catch (UnauthorizedException e) { // Gestisce le eccezioni di tipo UnauthorizedException
            System.out.println("Unauthorized request: " + e.getMessage());
            ObjectMapper mapper = new ObjectMapper(); // Crea un'istanza di ObjectMapper per la serializzazione dell'oggetto HttpErrorRes in formato JSON
            res.setStatus(HttpStatus.UNAUTHORIZED.value()); // Imposta lo stato della risposta HTTP a UNAUTHORIZED (401)
            res.setContentType("application/json;charset=UTF-8"); // Imposta il tipo di contenuto della risposta HTTP a JSON
            res.getWriter().write(mapper.writeValueAsString( // Scrive l'oggetto HttpErrorRes serializzato nella risposta HTTP
                    new HttpErrorRes(HttpStatus.UNAUTHORIZED,
                            "Unauthorized", e.getMessage() // Crea un nuovo oggetto HttpErrorRes con i dettagli dell'errore
                    )));
        }
    }

    // Questo metodo viene utilizzato per determinare se il filtro dovrebbe essere eseguito per una determinata richiesta
    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException {
        return new AntPathMatcher().match("/**", req.getServletPath()); // Controlla se il percorso della richiesta corrisponde a "/auth/**"
    }
}
