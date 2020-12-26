package nl.fhict.mazegameserver.models;

import lombok.NoArgsConstructor;
import nl.fhict.mazegameserver.enums.MessageType;

import java.util.ArrayList;

@NoArgsConstructor
public class Message {
    public MessageType messageType;
    public int playerId;
    public String sender;
    public String username;
    public String password;
    public boolean isSuccessful;
    public ArrayList<Player> players;

    public Message(MessageType messageType, boolean isSuccessful, int playerId) {
        this.isSuccessful = isSuccessful;
        this.messageType = messageType;
        this.playerId = playerId;
    }

    public Message(MessageType messageType, ArrayList<Player> players){
        this.messageType = messageType;
        this.players = players;
    }
}
