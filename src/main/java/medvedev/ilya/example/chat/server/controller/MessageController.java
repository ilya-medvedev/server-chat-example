package medvedev.ilya.example.chat.server.controller;

import medvedev.ilya.example.chat.server.model.ClientMessage;
import medvedev.ilya.example.chat.server.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MessageController {
    @MessageMapping("message")
    @SendTo("/topic/message")
    public Message receiveMessage(final Principal principal, final ClientMessage message) {
        final String name = principal.getName();
        final String text = message.getText();

        return new Message(name, text);
    }
}
