package nl.fhict.mazegameserver;

import nl.fhict.mazegameserver.models.Player;
import nl.fhict.mazegameserver.restclient.RESTClient;
import nl.fhict.mazegameserver.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class PlayerTests {
    @Mock
    private RESTClient restClient;

    @InjectMocks
    private PlayerService playerService;

    private Player player;

    @BeforeEach
    public void setup(){
        player = new Player();
    }

//    @Test
//    public void TestSuccessfulLoginAttempt(){
//        when(restClient.attemptLogin(player)).then
//        when(restClient.getCurrentPlayer("auth")).thenReturn(player)
//        player.setAuthenticationToken("auth");
//    }
}
