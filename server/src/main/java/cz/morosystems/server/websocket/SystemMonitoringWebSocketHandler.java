package cz.morosystems.server.websocket;

import cz.morosystems.dto.MessageDto;
import cz.morosystems.server.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import utilities.Serializer;

import java.io.IOException;

@Component
@Slf4j
public class SystemMonitoringWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private MessageService service;

    @Override
    public void handleTextMessage(final WebSocketSession session, final TextMessage message) throws IOException {
        String receivedPayload = message.getPayload();
        try {
            MessageDto messageDto = (MessageDto) Serializer.deserialize(receivedPayload);
            service.saveMessage(service.transformtDtoIntoMessage(messageDto));
            session.sendMessage(new TextMessage("200 OK"));
        } catch (ClassNotFoundException e) {
            log.error("Deserializace byla neúspěšná", e);
            session.sendMessage(new TextMessage("500 Serialization error"));
        }
    }

}
