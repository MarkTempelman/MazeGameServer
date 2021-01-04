package nl.fhict.mazegameserver.services;

import lombok.RequiredArgsConstructor;
import nl.fhict.mazegameserver.models.Lobby;
import nl.fhict.mazegameserver.models.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class LobbyService {
    private ArrayList<Lobby> lobbies = new ArrayList<>();

    public Lobby joinRandomLobby(Player player){
        Lobby lobby = findRandomAvailableLobby();
        if(lobby == null){
            lobbies.add(new Lobby(getNextLobbyId()));
        }
        lobby.addPlayer(player);
        return lobby;
    }

    private Lobby findRandomAvailableLobby(){
        if(lobbies.size() == 0){
            lobbies.add(new Lobby(getNextLobbyId()));
        }
        return lobbies.stream().filter(l -> l.doesLobbyHaveSpace()).findAny().orElse(null);
    }

    private int getNextLobbyId(){
        return lobbies.stream().mapToInt(l -> l.getLobbyId()).max().orElse(0) + 1;
    }
}
