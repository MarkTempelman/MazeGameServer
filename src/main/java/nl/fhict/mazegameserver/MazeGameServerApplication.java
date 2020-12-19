package nl.fhict.mazegameserver;

import nl.fhict.mazegameserver.models.Player;
import nl.fhict.mazegameserver.restclient.RESTClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MazeGameServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MazeGameServerApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowCredentials(true).allowedOrigins("http://localhost:4200").allowedMethods("");
            }
        };
    }

}
