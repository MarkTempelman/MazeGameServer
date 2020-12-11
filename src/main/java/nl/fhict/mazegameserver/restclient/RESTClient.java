package nl.fhict.mazegameserver.restclient;

import com.fasterxml.jackson.databind.util.JSONPObject;
import nl.fhict.mazegameserver.models.Player;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


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
}
//https://www.baeldung.com/rest-template
