package nl.fhict.mazegameserver.services;

import lombok.RequiredArgsConstructor;
import nl.fhict.mazegameserver.enums.MessageType;
import nl.fhict.mazegameserver.models.Lobby;
import nl.fhict.mazegameserver.models.Message;
import nl.fhict.mazegameserver.models.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class LobbyService {
    private ArrayList<Lobby> lobbies = new ArrayList<>();
    private final MessagingService messagingService;

    public void joinRandomLobby(Player player){
        Lobby lobby = findRandomAvailableLobby();
        lobby.addPlayer(player);
        sendLobbyPlayerUpdate(lobby, player);
    }

    public Lobby findRandomAvailableLobby(){
        if(lobbies.size() == 0){
            lobbies.add(new Lobby(getNextLobbyId()));
        }
        return lobbies.stream().filter(l -> l.doesLobbyHaveSpace()).findAny().orElse(null);
    }

    public void sendLobbyPlayerUpdate(Lobby lobby, Player player){
        messagingService.sendMessageToPlayers(new Message(MessageType.PlayerJoined, player), lobby.getOtherPlayers(player.getId()));
        messagingService.sendMessageToPlayer(new Message(MessageType.JoinedLobby, lobby.getLobbyId(), lobby.getPlayers()), player);
    }

    private int getNextLobbyId(){
        return lobbies.stream().mapToInt(l -> l.getLobbyId()).max().orElse(0) + 1;
    }
}
