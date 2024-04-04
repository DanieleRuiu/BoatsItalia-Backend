package Progetto.BoatsItalia.BoatsItalia.security;

import Progetto.BoatsItalia.BoatsItalia.exception.UnauthorizedException;
import Progetto.BoatsItalia.BoatsItalia.model.entities.User;
import Progetto.BoatsItalia.BoatsItalia.model.entities.resDTO.HttpErrorRes;
import Progetto.BoatsItalia.BoatsItalia.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;
import org.springframework.lang.NonNull;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private AuthService authSvc;




    // Questo metodo viene eseguito per ogni richiesta HTTP
    @Override
    protected void doFilterInternal(@NonNull final HttpServletRequest req,
                                    @NonNull final HttpServletResponse res,
                                    @NonNull final FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Requesting check for JWT");

        String token = jwtTools.resolveToken(req);

        if (token != null && !token.isEmpty()) {

            Claims claims = jwtTools.resolveClaims(req);
            //ArrayList<String> roles = jwtUtil.getRoles(claims);

            ObjectMapper mapper = new ObjectMapper();
            System.out.println("Claims: "+mapper.writeValueAsString(claims));

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication = new  UsernamePasswordAuthenticationToken(
                    claims.getSubject(),
                    claims.get("token"),
                    AuthorityUtils.createAuthorityList(claims.get("role").toString()));
            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);

            // Create a new session and add the security context.
            HttpSession session = req.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", context);


            filterChain.doFilter(req, res);

        }else if(req.getRequestURL().toString().contains("/api/users")
       || req.getRequestURL().toString().contains("/announcments")
       || req.getMethod().equals("OPTIONS")){//nel caso di auth non serve l'autenticazione e nemmeno il jwt

            System.out.println("no token needed");
            filterChain.doFilter(req, res);

        }else {//in ogni altro caso in cui non sia presente il token dò Unathorize
            res.setStatus(401);
        }
    }

    // Questo metodo viene utilizzato per determinare se il filtro dovrebbe essere eseguito per una determinata richiesta
    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException {
        return new AntPathMatcher().match("/**", req.getServletPath()); // Controlla se il percorso della richiesta corrisponde a "/auth/**"
    }
}
