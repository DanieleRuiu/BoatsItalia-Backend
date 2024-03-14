package Progetto.BoatsItalia.BoatsItalia.security;

import Progetto.BoatsItalia.BoatsItalia.exception.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityChain {

    // Iniezione delle dipendenze necessarie
    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private JwtFilter jwtFilter;

    // Definizione di un gestore degli accessi negati
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    // Configurazione della catena di sicurezza
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configura le richieste autorizzate
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().permitAll() // Consente l'accesso a tutte le richieste
                )
                // Aggiunge il filtro JwtFilter prima del filtro di autenticazione UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                // Configura la gestione delle sessioni
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Imposta la politica di creazione delle sessioni come stateless
                )
                // Configura la gestione delle eccezioni
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedHandler(accessDeniedHandler()) // Imposta il gestore degli accessi negati
                );

        return http.build(); // Restituisce la catena di sicurezza configurata
    }
}
