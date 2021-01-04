package nl.fhict.mazegameserver.controllers;

import lombok.RequiredArgsConstructor;
import nl.fhict.mazegameserver.enums.MessageType;
import nl.fhict.mazegameserver.models.Lobby;
import nl.fhict.mazegameserver.models.Message;
import nl.fhict.mazegameserver.models.Player;
import nl.fhict.mazegameserver.services.LobbyService;
import nl.fhict.mazegameserver.services.MessagingService;
import nl.fhict.mazegameserver.services.PlayerService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class LobbyController {
    private final PlayerService playerService;
    private final LobbyService lobbyService;
    private final MessagingService messagingService;

    @MessageMapping("/join/random")
    public void joinRandomLobby(Message messageIn){
        Player player = playerService.getPlayerById(Integer.parseInt(messageIn.sender));
        sendLobbyPlayerUpdate(lobbyService.joinRandomLobby(player), player);
    }

    private void sendLobbyPlayerUpdate(Lobby lobby, Player player){
        messagingService.sendMessageToPlayers(new Message(MessageType.PlayerJoined, player), lobby.getOtherPlayers(player.getId()));
        messagingService.sendMessageToPlayer(new Message(MessageType.JoinedLobby, lobby.getLobbyId(), lobby.getPlayers()), player);
    }
}
