package medvedev.ilya.example.chat.server.controller;

import medvedev.ilya.example.chat.server.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @MessageMapping("message")
    @SendTo("/topic/message")
    public Message message(final Message message) {
        return message;
    }
}
