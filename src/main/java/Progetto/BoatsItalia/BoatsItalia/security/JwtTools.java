package Progetto.BoatsItalia.BoatsItalia.security;

import Progetto.BoatsItalia.BoatsItalia.exception.UnauthorizedException;
import Progetto.BoatsItalia.BoatsItalia.model.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.UUID;

@Component
@PropertySource("application.properties")
public class JwtTools {
    @org.springframework.beans.factory.annotation.Value("${access_token.secret}")
    private String secret;

    @org.springframework.beans.factory.annotation.Value("${access_token.expiresIn}")
    private String exp;

    // Metodo per creare un token JWT
    public String createToken(User u) {
        return Jwts.builder()
                .setSubject(String.valueOf(u.getId()))
                .claim("userId", u.getId())
                .claim("username", u.getUsername())
                .claim("email", u.getEmail())

                .setIssuedAt(new Date(System.currentTimeMillis())) // Imposta la data di emissione del token come tempo corrente
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(exp))) // Imposta la data di scadenza del token come tempo corrente più l'intervallo di scadenza configurato
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) // Firma il token con una chiave segreta
                .compact(); // Compatta il token in una stringa
    }

    // Metodo per validare un token JWT
    public void validateToken(String token) throws UnauthorizedException {
        try {
            // Parsa il token e verifica la firma utilizzando la chiave segreta
            Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token);
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid access token");
        }
    }

    // Metodo per estrarre l'ID utente dal token JWT
    public Long extractUserIdFromToken(String token) throws UnauthorizedException {
        try {
            // Parsa il token e restituisce il soggetto come stringa, che viene poi convertito in UUID
            return Long.parseLong(Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build()
                    .parseClaimsJws(token).getBody().getSubject());
        } catch (IllegalArgumentException e){
                System.out.println(e);
            throw new UnauthorizedException("Invalid access token");
        }
    }

    // Metodo per verificare se l'ID utente nel token JWT corrisponde all'ID utente fornito
    public boolean matchTokenSub(UUID userId) throws UnauthorizedException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest req;
        // Ottiene la richiesta HTTP corrente
        if (requestAttributes instanceof ServletRequestAttributes) {
            req = ((ServletRequestAttributes)requestAttributes).getRequest();
        } else {
            return false;
        }
        // Ottiene il token JWT dall'intestazione Authorization della richiesta
        String token = req.getHeader("Authorization").split(" ")[1];
        // Estrae l'ID utente dal token JWT
        Long tokenUserId = extractUserIdFromToken(token);
        // Confronta l'ID utente estratto con l'ID utente fornito e restituisce true se corrispondono
        return tokenUserId.equals(userId);
    }
}
