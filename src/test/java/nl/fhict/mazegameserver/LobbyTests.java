package nl.fhict.mazegameserver;

import nl.fhict.mazegameserver.enums.Direction;
import nl.fhict.mazegameserver.models.Lobby;
import nl.fhict.mazegameserver.models.Message;
import nl.fhict.mazegameserver.models.Player;
import nl.fhict.mazegameserver.services.LobbyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class LobbyTests {
    private LobbyService lobbyService;
    private Player player;
    private Player player2;

    @BeforeEach
    public void setup(){
        lobbyService = new LobbyService();
        player = new Player();
        player2 = new Player();
        player.setId(1);
        player2.setId(2);
    }

    @Test
    public void TestJoinLobbyWhenNoneExist(){
        Lobby lobby = lobbyService.joinRandomLobby(player);
        assertEquals(lobby.getLobbyId(), 1);
        assertEquals(lobby.getPlayers().size(), 1);
    }

    @Test
    public void TestJoinLobbyWhenLobbyWithSpaceExists(){
        lobbyService.joinRandomLobby(player);
        Lobby lobby = lobbyService.joinRandomLobby(player2);
        assertEquals(lobby.getLobbyId(), 1);
        assertEquals(lobby.getPlayers().size(), 2);
    }

    @Test
    public void TestJoinLobbyWhenAllLobbiesAreFull(){
        Player player3 = new Player();
        player3.setId(3);
        lobbyService.joinRandomLobby(player);
        lobbyService.joinRandomLobby(player2);
        Lobby lobby = lobbyService.joinRandomLobby(player3);
        assertEquals(lobby.getLobbyId(), 2);
        assertEquals(lobby.getPlayers().size(), 1);
    }

    @Test
    public void TestTryStartGameWhenPossible(){
        lobbyService.joinRandomLobby(player);
        lobbyService.joinRandomLobby(player2);
        Lobby lobby = lobbyService.tryStartGame(1);
        assertTrue(lobby.isStarted());
    }

    @Test
    public void TestTryMovePlayerWhenMovementPossible(){
        lobbyService.joinRandomLobby(player);
        lobbyService.joinRandomLobby(player2);
        Lobby lobby = lobbyService.tryStartGame(1);
        Message message = lobby.tryMovePlayer(1, Direction.Down);
        assertNotEquals(null, message);
        assertEquals(2, lobby.getPlayerById(1).getPosition().getY());
    }
}
