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
    public int lobbyId;
    public ArrayList<Player> players;
    public Player player;
    public Wall[][] walls;

    public Message(MessageType messageType, boolean isSuccessful, int playerId) {
        this.isSuccessful = isSuccessful;
        this.messageType = messageType;
        this.playerId = playerId;
    }

    public Message(MessageType messageType, Player player) {
        this.messageType = messageType;
        this.player = player;
    }

    public Message(MessageType messageType, int lobbyId, ArrayList<Player> players) {
        this.messageType = messageType;
        this.lobbyId = lobbyId;
        this.players = players;
    }

    public Message(MessageType messageType, Wall[][] walls) {
        this.messageType = messageType;
        this.walls = walls;
    }

    public void removeSensitiveInformation(){
        if(players != null){
            ArrayList<Player> currentPlayers = players;
            players = new ArrayList<>();
            for (Player player:currentPlayers) {
                Player clone = player.clonePlayer();
                clone.removeSensitiveInformation();
                players.add(clone);
            }
        }
        if (player != null) {
            player = player.clonePlayer();
            player.removeSensitiveInformation();
        }
    }
}
