package nl.fhict.mazegameserver.controllers;

import lombok.RequiredArgsConstructor;
import nl.fhict.mazegameserver.enums.MessageType;
import nl.fhict.mazegameserver.models.Message;
import nl.fhict.mazegameserver.models.Player;
import nl.fhict.mazegameserver.services.PlayerService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlayerController {
    private final SimpMessagingTemplate template;
    private final PlayerService playerService;

    @MessageMapping("/login")
    public void login(Message messageIn){
        Player player = new Player(messageIn.username, messageIn.password);
        boolean isSuccessful = playerService.attemptLogin(player);
        Message messageOut = new Message(MessageType.LoginResponse, isSuccessful, player.getId());
        template.convertAndSend(messageIn.sender, messageOut);
    }

    @MessageMapping("/send/message")
    public void sendMessage(String message){
        System.out.println("Sending message: " + message);
        this.template.convertAndSend("/message", message);
    }
}
