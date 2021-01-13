package nl.fhict.mazegameserver;

import nl.fhict.mazegameserver.enums.TileType;
import nl.fhict.mazegameserver.logic.Pathfinder;
import nl.fhict.mazegameserver.models.Position;
import nl.fhict.mazegameserver.models.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static nl.fhict.mazegameserver.logic.MapGenerator.*;
import static org.junit.jupiter.api.Assertions.*;

public class PathfinderTests {
    private ArrayList<Tile> tiles = new ArrayList<>();
    private Tile[][] map;

    @ExtendWith(SpringExtension.class)
    @BeforeEach
    public void setup(){

    }

    @Test
    public void TestFindPathOnEmptyMap(){
        tiles.addAll(generateStraightWall(new Position(0, 0), 20, true));
        tiles.addAll(generateStraightWall(new Position(0, 1), 18, false));
        tiles.addAll(generateStraightWall(new Position(19, 1), 18, false));
        tiles.addAll(generateStraightWall(new Position(0, 19), 20, true));
        tiles.add(new Tile(new Position(1, 1), TileType.Start));
        tiles.add(new Tile(new Position(18, 18), TileType.End));
        map = fillEmptySpaces(convertArrayListTo2DArray(tiles));

        map = Pathfinder.generatePath(map);

        int actualLength = Arrays.stream(map).flatMap(Arrays::stream).filter(tile -> tile.getTileType() == TileType.Path).collect(Collectors.summingInt(a -> 1));
        int expectedLength = 34;
        assertEquals(expectedLength, actualLength);
    }
}
