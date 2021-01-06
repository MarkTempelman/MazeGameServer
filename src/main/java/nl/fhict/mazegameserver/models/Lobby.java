package nl.fhict.mazegameserver.models;

import lombok.Getter;
import nl.fhict.mazegameserver.enums.Direction;
import nl.fhict.mazegameserver.enums.MessageType;
import nl.fhict.mazegameserver.helpers.MapGenerator;
import nl.fhict.mazegameserver.helpers.MovementLogic;

import java.util.ArrayList;
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
        for (Player player: players) {
            player.setPosition(new Position(1,1));
        }
    }

    public boolean doesLobbyContainPlayer(int playerId){
        Player player = getPlayerById(playerId);
        if(player != null){
            return true;
        }
        return false;
    }

    public Message tryMovePlayer(int playerId, Direction direction){
        Player player = getPlayerById(playerId);
        if(player == null || !isStarted){
            return null;
        }
        if(MovementLogic.tryMove(player, direction, this)){
            return new Message(MessageType.MovementUpdate, players);
        }
        return null;
    }

    public Player getPlayerById (int playerId){
        return players.stream().filter(player -> player.getId() == playerId).findFirst().orElse(null);
    }

    private void generateWalls(){
        walls = MapGenerator.getMap();
    }
}
