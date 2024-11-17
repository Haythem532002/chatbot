package haythem.chatbot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {


    private final ChatService chatService;

    @GetMapping("/chatbot")
    public ResponseEntity<String> chat(@RequestParam String message) {
        String response = chatService.sendMessageToChatbot(message);
        return ResponseEntity.ok(response);
    }
}

