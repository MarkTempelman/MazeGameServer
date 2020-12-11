package nl.fhict.mazegameserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Player {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String authenticationToken;

    public Player(String username, String password){
        this.username = username;
        this.password = password;
    }
}
