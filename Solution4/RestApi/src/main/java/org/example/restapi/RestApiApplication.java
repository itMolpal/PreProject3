package org.example.restapi;

import org.example.restapi.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class RestApiApplication {

    private static String sessionId;

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder finalCode = new StringBuilder();
        String url = "http://94.198.50.185:7081/api/users";

        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);

        if (response.getHeaders().containsKey("Set-Cookie")) {
            sessionId = response.getHeaders().get("Set-Cookie").get(0);
        }

        User newUser = new User();
        newUser.setId(3L);
        newUser.setName("James");
        newUser.setLastName("Brown");
        newUser.setAge((byte) 25);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);
        HttpEntity<User> requestEntity = new HttpEntity<>(newUser, headers);

        ResponseEntity<String> createResponse = restTemplate.postForEntity(url, requestEntity, String.class);
        finalCode.append(getCodePart(createResponse.getBody()));

        newUser.setName("Thomas");
        newUser.setLastName("Shelby");
        HttpEntity<User> updateEntity = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> updateResponse = restTemplate.exchange(url, HttpMethod.PUT, updateEntity, String.class);
        finalCode.append(getCodePart(updateResponse.getBody()));

        ResponseEntity<String> deleteResponse = restTemplate.exchange(url + "/3", HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        finalCode.append(getCodePart(deleteResponse.getBody()));

        System.out.println("Итоговый код: " + finalCode.toString());
        System.out.println("Длина кода: " + finalCode.length());
    }

    private static String getCodePart(String responseBody) {
        System.out.println("Response Body: " + responseBody);

        return responseBody != null ? responseBody.trim() : "";
    }
}
