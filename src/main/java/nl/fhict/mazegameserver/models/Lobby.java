package nl.fhict.mazegameserver.models;

import lombok.Getter;
import nl.fhict.mazegameserver.enums.MessageType;

import java.util.ArrayList;

public class Lobby {
    @Getter
    private ArrayList<Player> players = new ArrayList<>();
    private final int maxPlayers = 2;

    public boolean doesLobbyHaveSpace(){
        return players.size() < maxPlayers;
    }

    public void addPlayer(Player player){
        players.add(player);
    }
}
