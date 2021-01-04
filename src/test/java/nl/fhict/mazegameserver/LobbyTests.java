package nl.fhict.mazegameserver;

import nl.fhict.mazegameserver.models.Lobby;
import nl.fhict.mazegameserver.models.Player;
import nl.fhict.mazegameserver.services.LobbyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class LobbyTests {
    private LobbyService lobbyService;
    private Player player;

    @BeforeEach
    public void setup(){
        lobbyService = new LobbyService();
        player = new Player();
        player.setId(1);
    }

    @Test
    public void TestJoinLobbyWhenNoneExist(){
        Lobby lobby = lobbyService.joinRandomLobby(player);
        assertEquals(lobby.getLobbyId(), 1);
    }
}
