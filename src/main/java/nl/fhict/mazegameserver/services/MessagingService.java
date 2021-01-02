package nl.fhict.mazegameserver.services;

import lombok.RequiredArgsConstructor;
import nl.fhict.mazegameserver.models.Message;
import nl.fhict.mazegameserver.models.Player;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MessagingService {
    private final SimpMessagingTemplate template;
    private static final String messagePrefix = "/message/";

    public void sendMessageToPlayers(Message message, ArrayList<Player> players){
        for (Player player: players) {
            sendMessageToPlayer(message, player);
        }
    }

    public void sendMessageToPlayer(Message message, Player player){
        message.removeSensitiveInformation();
        template.convertAndSend(messagePrefix + player.getId(), message);
    }


}
