package nl.fhict.mazegameserver.services;

import nl.fhict.mazegameserver.models.Player;
import nl.fhict.mazegameserver.restclient.RESTClient;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    public boolean attemptLogin(Player player){
        if(RESTClient.attemptLogin(player)){
            player.setId(RESTClient.getCurrentPlayer(player.getAuthenticationToken()).getId());
            return true;
        }
        return false;
    }
}
