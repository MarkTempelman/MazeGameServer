package nl.fhict.mazegameserver.helpers;

import nl.fhict.mazegameserver.enums.Direction;
import nl.fhict.mazegameserver.enums.TileType;
import nl.fhict.mazegameserver.models.*;

public class MovementLogic {

    private MovementLogic(){

    }

    public static boolean tryMove(Player player, Direction direction, Lobby lobby){
        Position nextPosition = getNextPosition(player.getPosition(), direction);

        if(isPositionOfType(nextPosition, lobby.getTiles(), TileType.Wall)){
            return false;
        }
        if(isPositionOfType(nextPosition, lobby.getTiles(), TileType.End))
            player.setFinished(true);
        move(player, nextPosition);
        return true;
    }

    private static void move(Player player, Position position){
        player.setPosition(position);
    }

    private static Position getNextPosition(Position position, Direction direction){
        switch(direction){
            case Right:
                return new Position(position.getX() + 1, position.getY());
            case Left:
                return new Position(position.getX() - 1, position.getY());
            case Up:
                return new Position(position.getX(), position.getY() - 1);
            case Down:
                return new Position(position.getX(), position.getY() + 1);
            default: return position;
        }
    }

    private static boolean isPositionOfType(Position position, Tile[][] tiles, TileType type){
        return tiles[position.getY()][position.getX()].getTileType() == type;
    }
}
