package medvedev.ilya.example.chat.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    @Override
    protected void configureInbound(final MessageSecurityMetadataSourceRegistry messages) {
        messages.simpTypeMatchers(SimpMessageType.CONNECT, SimpMessageType.DISCONNECT)
                .authenticated()
                .simpSubscribeDestMatchers("/topic/message")
                .authenticated()
                .simpMessageDestMatchers("/app/message")
                .hasRole("READ-AND-WRITE")
                .anyMessage()
                .denyAll();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
