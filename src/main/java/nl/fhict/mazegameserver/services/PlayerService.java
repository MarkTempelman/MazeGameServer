package nl.fhict.mazegameserver.services;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.fhict.mazegameserver.models.Player;
import nl.fhict.mazegameserver.restclient.RESTClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private static ArrayList<Player> players = new ArrayList<>();
    private final RESTClient restClient;

    public boolean attemptLogin(Player player){
        if(restClient.attemptLogin(player)){
            player.setId(restClient.getCurrentPlayer(player.getAuthenticationToken()).getId());
            addPlayerToPlayers(player);
            return true;
        }
        return false;
    }

    public void registerPlayer(Player player){
        restClient.registerPlayer(player);
    }

    public Player getPlayerById(int id){
        return players.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private void removePlayerById(int id){
        players.removeIf(player -> player.getId() == id);
    }

    private void addPlayerToPlayers(Player player){
        if(getPlayerById(player.getId()) != null){
            removePlayerById(player.getId());
        }
        players.add(player);
    }
}
