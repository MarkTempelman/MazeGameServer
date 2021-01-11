package nl.fhict.mazegameserver.logic;

import nl.fhict.mazegameserver.enums.TileType;
import nl.fhict.mazegameserver.models.Position;
import nl.fhict.mazegameserver.models.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Pathfinder {
    private static ArrayList<Tile> openTiles = new ArrayList<>();
    private static ArrayList<Tile> closedTiles = new ArrayList<>();
    private static ArrayList<Tile> allTiles = new ArrayList<>();
    private static ArrayList<Tile> path = new ArrayList<>();
    private static Position endPosition;

    public static Tile[][] generatePath(Tile[][] tiles){
        flattenTiles(tiles);
        initializeOpenList();
        setEndPosition();


        while(openTiles.size() > 0){

            Tile currentTile = openTiles.stream().min(Comparator.comparing(Tile::getTotalCost)).orElse(null);

            if(currentTile.getTileType() == TileType.End){
                break;
            }

            openTiles.remove(currentTile);
            closedTiles.add(currentTile);

            ArrayList<Tile> neighbouringTiles = getNeighbouringTiles(currentTile);

            for (Tile neighbour: neighbouringTiles) {
                int cost = currentTile.getTotalCost() + 1;
                if(neighbour.getTileType() == TileType.Wall){
                    continue;
                }
                removeTileFromOpenTilesIfNecessary(neighbour, cost);
                removeTileFromClosedTilesIfNecessary(neighbour, cost);
                tryAddNeighbourToOpenTiles(neighbour, cost, currentTile);
            }
        }


        addEndToPath();
        addOthersToPath();

        return addPathToMap(path, tiles);
    }

    private static Tile[][] addPathToMap(ArrayList<Tile> path, Tile[][] map){
        for (Tile tile: path) {
            map[tile.getPosition().getY()][tile.getPosition().getX()] = tile;
        }
        return map;
    }

    private static void setEndPosition(){
        Tile endTile = allTiles.stream().filter(tile -> tile.getTileType() == TileType.End).findFirst().orElse(null);
        if(endTile != null){
            endPosition = endTile.getPosition();
        }
    }

    private static void flattenTiles(Tile[][] tiles){
        allTiles.addAll(Arrays.stream(tiles).flatMap(Arrays::stream).collect(Collectors.toList()));
    }

    private static void removeTileFromOpenTilesIfNecessary(Tile neighbour, int cost){
        Tile otherTile = openTiles.stream().filter(t -> t.equals(neighbour)).findFirst().orElse(null);
        if(otherTile != null && otherTile.getDistanceFromStart() > cost){
            openTiles.remove(otherTile);
        }
    }

    private static void removeTileFromClosedTilesIfNecessary(Tile neighbour, int cost){
        Tile otherTile = closedTiles.stream().filter(t -> t.equals(neighbour)).findFirst().orElse(null);
        if(otherTile != null && otherTile.getDistanceFromStart() > cost){
            closedTiles.remove(otherTile);
        }
    }

    private static void tryAddNeighbourToOpenTiles(Tile neighbour, int cost, Tile currentTile){
        if(!openTiles.stream().anyMatch(t -> t.equals(neighbour)) && !closedTiles.stream().anyMatch(t -> t.equals(neighbour))){
            setNeighbourVariables(neighbour, cost, currentTile);
            openTiles.add(neighbour);
        }
    }

    private static void setNeighbourVariables(Tile neighbour, int cost, Tile parent){
        neighbour.setDistanceFromStart(cost);
        neighbour.setTotalCost(cost + getDistanceToEnd(neighbour));
        neighbour.setParent(parent);
    }

    private static void addEndToPath(){
        path.add(openTiles.stream().filter(tile -> tile.getTileType() == TileType.End).findFirst().orElse(null));
    }

    private static void addOthersToPath(){
        Tile tileToAdd = path.get(path.size() - 1).getParent();
        tileToAdd.setTileType(TileType.Path);
        path.add(tileToAdd);
        if(tileToAdd.getParent() != null){
            addOthersToPath();
        }
    }

    private static boolean tilesAreValid(){
        return allTiles.stream().filter(tile -> tile.getTileType() == TileType.Start).collect(Collectors.toList()).toArray().length == 1 &&
                allTiles.stream().filter(tile -> tile.getTileType() == TileType.End).collect(Collectors.toList()).toArray().length == 1;

    }

    private static Tile getStartNode(){
        return allTiles.stream().filter(tile -> tile.getTileType() == TileType.Start).findFirst().orElse(null);
    }

    private static void initializeOpenList(){
        Tile tile = getStartNode();
        tile.setTotalCost(0);
        openTiles.add(tile);
    }

    private static ArrayList<Tile> getNeighbouringTiles(Tile tile){
        ArrayList<Tile> tiles = new ArrayList<>();
        addNeighbour(tiles, tile, 1, 0);
        addNeighbour(tiles, tile, -1, 0);
        addNeighbour(tiles, tile, 0, 1);
        addNeighbour(tiles, tile, 0, -1);
        return tiles;
    }

    private static void addNeighbour(ArrayList<Tile> tiles, Tile tile, int xModifier, int yModifier){
        Tile currentTile = allTiles.stream().filter(t -> t.getPosition().getX() == tile.getPosition().getX() + xModifier && t.getPosition().getY() == tile.getPosition().getY() + yModifier).findFirst().orElse(null);
        if(currentTile != null){
            tiles.add(currentTile);
        }
    }

    private static int getDistanceToEnd(Tile currentTile){
        return Math.abs(currentTile.getPosition().getX() - endPosition.getX()) + Math.abs(currentTile.getPosition().getY() - endPosition.getY());
    }
}
