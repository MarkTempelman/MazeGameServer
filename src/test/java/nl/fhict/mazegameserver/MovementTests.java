package nl.fhict.mazegameserver;

import nl.fhict.mazegameserver.enums.Direction;
import nl.fhict.mazegameserver.logic.MovementLogic;
import nl.fhict.mazegameserver.models.Lobby;
import nl.fhict.mazegameserver.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class MovementTests {
    Player player1;
    Player player2;
    Lobby lobby;

    @BeforeEach
    public void setup(){
        player1 = new Player();
        player2 = new Player();
        player1.setId(1);
        player2.setId(2);
        lobby = new Lobby(1);
        lobby.addPlayer(player1);
        lobby.addPlayer(player2);
        lobby.start();
    }

    @Test
    public void TestMoveWhenMovementIsPossible(){
        boolean isSuccessful = MovementLogic.tryMove(player1, Direction.Right, lobby);
        assertTrue(isSuccessful);
        assertEquals(2, player1.getPosition().getX());
        assertEquals(1, player1.getPosition().getY());
    }

    @Test
    public void TestMoveWhenMovementIsNotPossible(){
        boolean isSuccessful = MovementLogic.tryMove(player1, Direction.Left, lobby);
        assertFalse(isSuccessful);
        assertEquals(1, player1.getPosition().getX());
        assertEquals(1, player1.getPosition().getY());
    }
}
