package nl.fhict.mazegameserver.logic;

import nl.fhict.mazegameserver.enums.TileType;
import nl.fhict.mazegameserver.models.Position;
import nl.fhict.mazegameserver.models.Tile;

import java.util.ArrayList;

public class MapGenerator {
    private static final int mapWidth = 20;
    private static final int mapHeight = 20;

    public static ArrayList<Tile> generateStraightWall(Position startingPosition, int length, boolean isHorizontal) {
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

    public static Tile[][] convertArrayListTo2DArray(ArrayList<Tile> objects){
        Tile[][] objectArray = new Tile[mapWidth][mapHeight];
        for (Tile object: objects) {
            objectArray[object.getPosition().getY()][object.getPosition().getX()] = object;
        }
        return objectArray;
    }

    public static Tile[][] fillEmptySpaces(Tile[][] map){
        for(int row = 0; row < map.length; row++){
            for(int col = 0; col < map[row].length; col++){
                if(map[row][col] == null){
                    map[row][col] = new Tile(new Position(col, row), TileType.Empty);
                }
            }
        }
        return map;
    }

    public static ArrayList<Tile> generateWalls(){
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.addAll(generateStraightWall(new Position(0, 0), 20, true));
        tiles.addAll(generateStraightWall(new Position(0, 1), 18, false));
        tiles.addAll(generateStraightWall(new Position(19, 1), 18, false));
        tiles.addAll(generateStraightWall(new Position(0, 19), 20, true));

        tiles.addAll(generateStraightWall(new Position(2, 2), 4, false));
        tiles.addAll(generateStraightWall(new Position(4, 2), 4, false));
        tiles.addAll(generateStraightWall(new Position(8, 2), 4, false));
        tiles.addAll(generateStraightWall(new Position(6, 1), 4, false));

        tiles.addAll(generateStraightWall(new Position(2, 6), 7, true));

        tiles.addAll(generateStraightWall(new Position(2, 8), 5, true));
        tiles.addAll(generateStraightWall(new Position(2, 9), 5, true));

        tiles.addAll(generateStraightWall(new Position(2, 10), 5, false));
        tiles.addAll(generateStraightWall(new Position(4, 11), 5, false));
        tiles.addAll(generateStraightWall(new Position(6, 10), 8, false));

        tiles.addAll(generateStraightWall(new Position(2, 16), 7, true));

        tiles.addAll(generateStraightWall(new Position(2, 17), 1, true));
        tiles.addAll(generateStraightWall(new Position(4, 18), 1, true));
        tiles.addAll(generateStraightWall(new Position(8, 18), 1, true));
        tiles.addAll(generateStraightWall(new Position(10, 1), 1, true));
        tiles.addAll(generateStraightWall(new Position(12, 2), 1, true));
        tiles.addAll(generateStraightWall(new Position(18, 2), 1, true));
        tiles.addAll(generateStraightWall(new Position(17, 4), 1, true));
        tiles.addAll(generateStraightWall(new Position(11, 6), 1, true));
        tiles.addAll(generateStraightWall(new Position(15, 6), 1, true));
        tiles.addAll(generateStraightWall(new Position(12, 8), 1, true));
        tiles.addAll(generateStraightWall(new Position(18, 8), 1, true));
        tiles.addAll(generateStraightWall(new Position(12, 15), 1, true));
        tiles.addAll(generateStraightWall(new Position(11, 17), 1, true));

        tiles.addAll(generateStraightWall(new Position(10, 3), 5, true));
        tiles.addAll(generateStraightWall(new Position(12, 4), 2, true));

        tiles.addAll(generateStraightWall(new Position(10, 4), 6, false));
        tiles.addAll(generateStraightWall(new Position(8, 8), 3, false));
        tiles.addAll(generateStraightWall(new Position(13, 6), 12, false));

        tiles.addAll(generateStraightWall(new Position(8, 11), 10, true));
        tiles.addAll(generateStraightWall(new Position(15, 9), 4, true));

        tiles.addAll(generateStraightWall(new Position(16, 1), 7, false));

        tiles.addAll(generateStraightWall(new Position(8, 13), 4, true));
        tiles.addAll(generateStraightWall(new Position(8, 14), 2, false));
        tiles.addAll(generateStraightWall(new Position(10, 14), 4, false));

        tiles.addAll(generateStraightWall(new Position(17, 13), 2, true));
        tiles.addAll(generateStraightWall(new Position(15, 13), 6, false));
        tiles.addAll(generateStraightWall(new Position(17, 15), 4, false));
        return tiles;
    }
}
