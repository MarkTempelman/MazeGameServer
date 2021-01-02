package nl.fhict.mazegameserver.controllers;

import lombok.RequiredArgsConstructor;
import nl.fhict.mazegameserver.enums.MessageType;
import nl.fhict.mazegameserver.models.Message;
import nl.fhict.mazegameserver.models.Player;
import nl.fhict.mazegameserver.services.LobbyService;
import nl.fhict.mazegameserver.services.PlayerService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class LobbyController {
    private final SimpMessagingTemplate template;
    private final PlayerService playerService;
    private final LobbyService lobbyService;
    private static final String messagePrefix = "/message/";

    @MessageMapping("/join/random")
    public void joinRandomLobby(Message messageIn){
        Player player = playerService.getPlayerById(Integer.parseInt(messageIn.sender));
        lobbyService.joinRandomLobby(player);
    }
}
