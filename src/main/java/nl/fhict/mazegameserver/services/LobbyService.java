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
    private static MessagingService messagingService;

    public void joinRandomLobby(Player player){
        Lobby lobby = findRandomAvailableLobby();
        if(lobby == null){
            lobby = new Lobby();
            lobbies.add(lobby);
        }
        lobby.addPlayer(player);
        sendLobbyPlayerUpdate(lobby);
    }

    public Lobby findRandomAvailableLobby(){
        return lobbies.stream().filter(l -> l.doesLobbyHaveSpace()).findAny().orElse(null);
    }

    public void sendLobbyPlayerUpdate(Lobby lobby){
        messagingService.sendMessageToPlayers(new Message(MessageType.PlayerJoined, lobby.getPlayers()), lobby.getPlayers());
    }
}
