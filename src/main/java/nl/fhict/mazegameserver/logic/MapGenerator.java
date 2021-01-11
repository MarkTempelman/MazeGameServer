package nl.fhict.mazegameserver.logic;

import nl.fhict.mazegameserver.enums.TileType;
import nl.fhict.mazegameserver.models.Position;
import nl.fhict.mazegameserver.models.Tile;

import java.util.ArrayList;

public class MapGenerator {
    private static final int mapWidth = 20;
    private static final int mapHeight = 20;

    private static ArrayList<Tile> generateStraightWall(Position startingPosition, int length, boolean isHorizontal) {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Position position;
            if (isHorizontal) {
                position = new Position(startingPosition.getX() + i, startingPosition.getY());
            } else {
                position = new Position(startingPosition.getX(), startingPosition.getY() + i);
            }
            tiles.add(new Tile(position, TileType.Wall));
        }
        return tiles;
    }

    public static Tile[][] getMap(){
        ArrayList<Tile> tiles = generateWalls();
        tiles.add(new Tile(new Position(1, 1), TileType.Start));
        tiles.add(new Tile(new Position(18, 18), TileType.End));
        return fillEmptySpaces(convertArrayListTo2DArray(tiles));
    }

    private static Tile[][] convertArrayListTo2DArray(ArrayList<Tile> objects){
        Tile[][] objectArray = new Tile[mapWidth][mapHeight];
        for (Tile object: objects) {
            objectArray[object.getPosition().getY()][object.getPosition().getX()] = object;
        }
        return objectArray;
    }

    private static ArrayList<Tile> generateWalls(){
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.addAll(generateStraightWall(new Position(0, 0), 20, true));
        tiles.addAll(generateStraightWall(new Position(0, 1), 18, false));
        tiles.addAll(generateStraightWall(new Position(19, 1), 18, false));
        tiles.addAll(generateStraightWall(new Position(0, 19), 20, true));
        tiles.addAll(generateStraightWall(new Position(5, 5), 9, true));
        tiles.addAll(generateStraightWall(new Position(5, 15), 9, true));
        return tiles;
    }

    private static Tile[][] fillEmptySpaces(Tile[][] map){
        for(int row = 0; row < map.length; row++){
            for(int col = 0; col < map[row].length; col++){
                if(map[row][col] == null){
                    map[row][col] = new Tile(new Position(col, row), TileType.Empty);
                }
            }
        }
        return map;
    }
}
