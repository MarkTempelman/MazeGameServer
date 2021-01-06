package nl.fhict.mazegameserver.services;

import lombok.RequiredArgsConstructor;
import nl.fhict.mazegameserver.enums.Direction;
import nl.fhict.mazegameserver.models.Lobby;
import nl.fhict.mazegameserver.models.Message;
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

    public Lobby tryStartGame(int lobbyId) {
        Lobby lobby = getLobbyById(lobbyId);
        if(lobby.canGameStart()){
            lobby.start();
            return lobby;
        }
        return null;
    }

    public Message tryMovePlayer(int lobbyId, int playerId, Direction direction){
        Lobby lobby = getLobbyById(lobbyId);
        // TODO: 06/01/2021 return error message instead of null
        if(!lobby.doesLobbyContainPlayer(playerId)){
            return null;
        }
        return lobby.tryMovePlayer(playerId, direction);
    }

    public Lobby getLobbyById(int lobbyId){
        return lobbies.stream().filter(lobby -> lobby.getLobbyId() == lobbyId).findFirst().orElse(null);
    }

    //TODO: check if player is already in a lobby
}
