package nl.fhict.mazegameserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Player implements Cloneable{
    @Getter @Setter
    private Integer id = 0;
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

    public Player(String username){
        this.username = username;
    }

    public void removeSensitiveInformation(){
        id = null;
        password = null;
        authenticationToken = null;
    }

    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Player clonePlayer(){
        try{
            return (Player)clone();
        } catch (CloneNotSupportedException e){
            System.out.println(e);
            return null;
        }
    }
}
