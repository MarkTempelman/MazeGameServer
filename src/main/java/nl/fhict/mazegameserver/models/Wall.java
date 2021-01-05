package nl.fhict.mazegameserver.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Wall {
    @Getter
    @NonNull
    private Position position;
}
