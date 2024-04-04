package Progetto.BoatsItalia.BoatsItalia.security;

import Progetto.BoatsItalia.BoatsItalia.exception.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableMethodSecurity
public class SecurityChain {

    // Iniezione delle dipendenze necessarie


    // Definizione di un gestore degli accessi negati
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    // Configurazione della catena di sicurezza
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        //http.csrf(AbstractHttpConfigurer::disable).cors(cors -> cors.disable())


               http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.POST, "/admin").hasAnyRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/admin/**").hasAnyRole("USER")
                                .requestMatchers(HttpMethod.DELETE, "/admin/**").hasAnyRole("USER")
                                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/**").permitAll()

                                .anyRequest().permitAll()
                )
                       .csrf(CsrfConfigurer::disable);;

                /*.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                // Configura la gestione delle sessioni
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedHandler(accessDeniedHandler())
                );*/

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
