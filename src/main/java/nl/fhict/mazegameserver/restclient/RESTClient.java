package nl.fhict.mazegameserver.restclient;

import com.fasterxml.jackson.databind.util.JSONPObject;
import nl.fhict.mazegameserver.models.Player;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RESTClient {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String baseURL = "http://localhost:8080/";

    public static boolean attemptLogin(Player player){
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("username", player.getUsername());
        userJsonObject.put("password", player.getPassword());

        HttpEntity<String> request = new HttpEntity<>(userJsonObject.toString(), getHttpHeaders());

        ResponseEntity<String> response = restTemplate.postForEntity(baseURL+"login", request, String.class);
        System.out.println(response.getHeaders());

        player.setAuthenticationToken(response.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));

        if(response.getStatusCode().is2xxSuccessful()){
            return true;
        }
        return false;
    }

    public static void registerPlayer(Player player){
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("username", player.getUsername());
        userJsonObject.put("password", player.getPassword());

        HttpEntity<String> request = new HttpEntity<>(userJsonObject.toString(), getHttpHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(baseURL+"all/user", request, String.class);
    }

    public static Player getCurrentPlayer(String token){
        HttpEntity request = new HttpEntity(getHttpHeaders(token));

        ResponseEntity<Player> response = restTemplate.exchange(baseURL+"member/current", HttpMethod.GET, request, Player.class);
        return response.getBody();
    }

    public static ArrayList<Player> getAllPlayers(String token){
        ArrayList<Player> players = new ArrayList<>();

        HttpEntity request = new HttpEntity(getHttpHeaders(token));

        ResponseEntity<Player[]> response = restTemplate.exchange(baseURL+"member/user", HttpMethod.GET, request, Player[].class);
        Collections.addAll(players, response.getBody());

        return players;
    }

    private static HttpHeaders getHttpHeaders(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        return headers;
    }

    private static HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
