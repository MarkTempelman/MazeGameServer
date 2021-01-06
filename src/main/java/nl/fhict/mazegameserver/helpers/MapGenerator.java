package nl.fhict.mazegameserver.helpers;

import nl.fhict.mazegameserver.models.Position;
import nl.fhict.mazegameserver.models.Wall;

import java.util.ArrayList;

public class MapGenerator {
    private static final int mapWidth = 20;
    private static final int mapHeight = 20;

    private static ArrayList<Wall> generateStraightWall(Position startingPosition, int length, boolean isHorizontal) {
        ArrayList<Wall> walls = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Position position;
            if (isHorizontal) {
                position = new Position(startingPosition.getX() + i, startingPosition.getY());
            } else {
                position = new Position(startingPosition.getX(), startingPosition.getY() + i);
            }
            walls.add(new Wall(position));
        }
        return walls;
    }

    public static Wall[][] getMap(){
        return generateMap();
    }

    private static Wall[][] convertArrayListTo2DArray(ArrayList<Wall> objects){
        Wall[][] objectArray = new Wall[mapWidth][mapHeight];
        for (Wall object: objects) {
            objectArray[object.getPosition().getY()][object.getPosition().getX()] = object;
        }
        return objectArray;
    }

    private static Wall[][] generateMap(){
        ArrayList<Wall> walls = new ArrayList<>();
        walls.addAll(generateStraightWall(new Position(0, 0), 20, true));
        walls.addAll(generateStraightWall(new Position(0, 1), 18, false));
        walls.addAll(generateStraightWall(new Position(19, 1), 18, false));
        walls.addAll(generateStraightWall(new Position(0, 19), 20, true));
        walls.addAll(generateStraightWall(new Position(5, 5), 9, true));
        walls.addAll(generateStraightWall(new Position(5, 15), 9, true));
        return convertArrayListTo2DArray(walls);
    }
}
