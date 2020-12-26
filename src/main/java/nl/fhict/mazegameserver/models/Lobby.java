package nl.fhict.mazegameserver.models;

import lombok.Getter;

import java.util.ArrayList;

public class Lobby {
    @Getter
    private int id;
    private ArrayList<Player> players = new ArrayList<>();

    public Lobby(int id, Player firstPlayer){
        this.id = id;
        players.add(firstPlayer);
    }
}
