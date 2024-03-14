package Progetto.BoatsItalia.BoatsItalia.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Indica che questa classe è una classe di configurazione per Spring
public class AppConfig {

    // Definizione di un bean per l'encoder delle password
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Restituisce un'istanza di BCryptPasswordEncoder, un'implementazione di PasswordEncoder
        return new BCryptPasswordEncoder();
    }
}
