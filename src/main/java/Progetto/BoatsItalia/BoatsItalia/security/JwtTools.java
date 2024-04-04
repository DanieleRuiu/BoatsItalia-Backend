package Progetto.BoatsItalia.BoatsItalia.security;

import Progetto.BoatsItalia.BoatsItalia.exception.UnauthorizedException;
import Progetto.BoatsItalia.BoatsItalia.model.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@PropertySource("application.properties")
public class JwtTools {

private final JwtParser jwtParser;
String secret_key = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";
SecretKey secret;

private final long exp= 60*60*1000;
    public JwtTools(){
        secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret_key));
        this.jwtParser = Jwts.parserBuilder().setSigningKey(secret).build();
    }




    // Metodo per creare un token JWT
    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("firstName",user.getFirstName());
        claims.put("lastName",user.getLastName());
        claims.put("password",user.getPassword());
        claims.put("role",user.getRole());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(exp));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(secret, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null && !token.isEmpty()) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader("Token"); // request.getHeader(TOKEN_HEADER);
        System.out.println("resolveToken "+bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    // Metodo per validare un token JWT
  /*  public void validateToken(String token) throws UnauthorizedException {
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
    }*/
}
