package haythem.chatbot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final RestTemplate restTemplate;


    public String sendMessageToChatbot(String message) {
        String url = "https://dialogflow.googleapis.com/v2/projects/chatbot-learning-platform-xayo/agent/sessions/2d356172-38fc-29b9-5018-4222d0d784a1:detectIntent";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json; charset=utf-8");
        headers.set("Authorization", "Bearer ya29.a0AeDClZB7IN-j943N1F2ZJfDthLjjjia091ZjyHKZ-t1DZxrZsq4oaGWUt2g-f9i2Cj-bIPJ7JM9iHT1EzgcpSjQwWW4TkFkKIz4SskLxJLVXR_GC1mjgyQqfq2aPuwoE-UPUrAt_C1lJt7sAUa5fpANuDfUJzanRq5mf7swRf7Owl3fZrkQT7tU0ynnQl8KJGk-BIv-FjU7zCH2mclydB6v84dls-QZTy4XxkIaAPYGlW0nU4KlNItcHLES2SJapFGDAAvlOpAIaZ0mF8GRhZnMYLfLIkiZb8AoWyXk0hfoXG3AJ6zcIRfmGMuJmJ29_cK39HXU6x1q42EldFdpzwYBgBaBvsOdNps63wUqteX5whxK2Rn0p5IkEI7b5JO8_AH1L0nQyaWzzcN3EvliCFNDW04gjbjFFgw9ZpyQh3d-mqAaCgYKASwSARASFQHGX2MiKcjQhoSmAgvP7kvbq-k0yw0437");

        Map<String, String> textContent = new HashMap<>();
        textContent.put("text", message);
        textContent.put("languageCode", "en");

        Map<String, Map<String, String>> textQuery = new HashMap<>();
        textQuery.put("text", textContent);

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("source", "DIALOGFLOW_CONSOLE");
        queryParams.put("timeZone", "Africa/Lagos");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("queryInput", textQuery);
        requestBody.put("queryParams", queryParams);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }
}
