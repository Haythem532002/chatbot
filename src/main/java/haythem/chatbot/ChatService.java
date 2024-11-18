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
        headers.set("Authorization", "Bearer ya29.a0AeDClZDjda7rsdLuKXvLZljQY7i4W-QVljHMm6xbJ1QZHijiSJXgqAFuPrVmX8vMGIio0E_KxHuoLvhRsqNL8LmGathL1apoK3EDs4H6EUAHBb6bovruJ7cLZ_g5HyMFJ9TLQTlMV-xSyiQg24bhP46pQ9wzsEeai8Aw2_GOcBdpaJ9QWotWXilQaYE1xQwofn08-47-PNpvms_ZBITSOBknkrNkDSSR96B1qtfbhJjxfCIq5wpy7hjCeZKEqJiNGkAA563IuyTwRbwUh_kjD1I4iiUZeeJrLGiT_nTrFJ_RBxOuhDq-TDVbNJVV6sR8PEv2uxf9_UkUpm6DNLAWRI0xt-cuMUz42bKKqPWZoVhdS2H68MWu7Vqs-KwobwoNB-He-juV7vK0Hi1GZWxcqvQZ4M71Z_Ut-jp5QM6lTuOsKQaCgYKAVgSARASFQHGX2MiH587BG1wBgiQj8-pqlyP2Q0437");

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
