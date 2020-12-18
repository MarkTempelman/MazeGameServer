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
        Player player = new Player("name2", "pw");
        SpringApplication.run(MazeGameServerApplication.class, args);
        RESTClient.attemptLogin(player);
        System.out.println(player.getAuthenticationToken());
        System.out.println(RESTClient.getAllPlayers(player.getAuthenticationToken()));
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
