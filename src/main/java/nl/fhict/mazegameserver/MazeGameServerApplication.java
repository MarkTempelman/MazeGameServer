package nl.fhict.mazegameserver;

import nl.fhict.mazegameserver.models.Player;
import nl.fhict.mazegameserver.restclient.RESTClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MazeGameServerApplication {
    public static void main(String[] args) {
        Player player = new Player("name2", "pw");
        SpringApplication.run(MazeGameServerApplication.class, args);
        RESTClient.attemptLogin(player);

    }

}
