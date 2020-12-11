package nl.fhict.mazegameserver.restclient;

import com.fasterxml.jackson.databind.util.JSONPObject;
import nl.fhict.mazegameserver.models.Player;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class RESTClient {

    private static RestTemplate restTemplate = new RestTemplate();
    private static final String baseURL = "http://localhost:8080/";

    public static boolean attemptLogin(Player player){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("username", player.getUsername());
        userJsonObject.put("password", player.getPassword());

        HttpEntity<String> request = new HttpEntity<>(userJsonObject.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseURL+"login", request, String.class);
        System.out.println(response.getHeaders());

        player.setAuthenticationToken(response.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));

        if(response.getStatusCode().is2xxSuccessful()){
            return true;
        }
        return false;
    }

    public static ArrayList<Player> getAllPlayers(String token){
        ArrayList<Player> players = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<Player[]> response = restTemplate.exchange(baseURL+"member/user", HttpMethod.GET, request, Player[].class);
        Collections.addAll(players, response.getBody());

        return players;
    }
}
//https://www.baeldung.com/rest-template
