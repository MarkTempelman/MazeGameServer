package nl.fhict.mazegameserver.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.fhict.mazegameserver.enums.TileType;

@RequiredArgsConstructor
public class Tile {
    @Getter
    @NonNull
    private Position position;

    @Getter
    @Setter
    @NonNull
    private TileType tileType;

    @Getter @Setter
    private int totalCost;

    @Getter @Setter
    private int distanceFromStart;

    @Getter @Setter
    private Tile parent;

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }

        if(!(o instanceof Tile)){
            return false;
        }

        Tile t = (Tile) o;
        return position.getX() == t.getPosition().getX() && position.getY() == t.getPosition().getY();
    }
}
