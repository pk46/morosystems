package cz.kupcik.morosystems.client;

import jakarta.websocket.*;
import jakarta.websocket.OnOpen;
import lombok.extern.slf4j.Slf4j;

@ClientEndpoint
@Slf4j
public class WebSocketClient {

    @OnOpen
    public void onOpen() {
        log.info("Spojeni navazano");
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("Prijato ze serveru: " + message);
    }

    @OnClose
    public void onClose() {
        log.info("Spojeni ukonceno");
    }

}
