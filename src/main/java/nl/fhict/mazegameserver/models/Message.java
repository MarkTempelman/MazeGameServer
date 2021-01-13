package nl.fhict.mazegameserver.models;

import lombok.NoArgsConstructor;
import nl.fhict.mazegameserver.enums.Direction;
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
    public Tile[][] tiles;
    public Direction direction;
    public int shortestRouteLength;

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

    public Message(MessageType messageType, Tile[][] tiles, ArrayList<Player> players) {
        this.messageType = messageType;
        this.tiles = tiles;
        this.players = players;
    }

    public Message(MessageType messageType, Tile[][] tiles, ArrayList<Player> players, int shortestRouteLength) {
        this.messageType = messageType;
        this.tiles = tiles;
        this.players = players;
        this.shortestRouteLength = shortestRouteLength;
    }

    public Message(MessageType messageType, ArrayList<Player> players) {
        this.messageType = messageType;
        this.players = players;
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
