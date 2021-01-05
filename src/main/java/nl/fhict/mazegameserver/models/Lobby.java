package nl.fhict.mazegameserver.models;

import lombok.Getter;
import nl.fhict.mazegameserver.helpers.MapGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Lobby {
    @Getter
    private ArrayList<Player> players = new ArrayList<>();

    private final int maxPlayers = 2;

    @Getter
    private final int lobbyId;

    @Getter
    private Wall[][] walls;

    private boolean isStarted = false;

    public Lobby(int lobbyId){
        this.lobbyId = lobbyId;
    }

    public boolean doesLobbyHaveSpace(){
        return players.size() < maxPlayers;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public ArrayList<Player> getOtherPlayers(int id){
        return new ArrayList<>(players.stream().filter(player -> player.getId() != id).collect(Collectors.toList()));
    }

    public boolean canGameStart(){
        return players.size() == maxPlayers && !isStarted;
    }

    public void start(){
        generateWalls();
        isStarted = true;
    }

    private void generateWalls(){
        walls = MapGenerator.getMap();
    }
}
