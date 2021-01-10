package nl.fhict.mazegameserver.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.fhict.mazegameserver.enums.TileType;

@RequiredArgsConstructor
public class Tile {
    @Getter
    @NonNull
    private Position position;

    @Getter
    @NonNull
    private TileType tileType;
}
