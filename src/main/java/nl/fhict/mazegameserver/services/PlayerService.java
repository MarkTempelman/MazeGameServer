package nl.fhict.mazegameserver.services;

import lombok.Getter;
import nl.fhict.mazegameserver.models.Player;
import nl.fhict.mazegameserver.restclient.RESTClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PlayerService {
    private ArrayList<Player> players = new ArrayList<>();

    public boolean attemptLogin(Player player){
        if(RESTClient.attemptLogin(player)){
            player.setId(RESTClient.getCurrentPlayer(player.getAuthenticationToken()).getId());
            players.add(player);
            return true;
        }
        return false;
    }

    public void registerPlayer(Player player){
        RESTClient.registerPlayer(player);
    }

    public Player getPlayerById(int id){
        return players.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}
