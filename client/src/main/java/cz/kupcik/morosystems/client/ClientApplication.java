package cz.kupcik.morosystems.client;

import cz.kupcik.morosystems.core.utilities.Serializer;
import cz.kupcik.morosystems.kafka.Producer;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;


@Slf4j
public class ClientApplication {

    private static String createMessage() throws IOException {
        return Serializer.serialize(SystemInfoGenerator.generate());
    }

    private static void kafkaCommunication() throws IOException {
        Producer producer = new Producer();
        producer.produce(SystemInfoGenerator.generate());
    }

    private static void webSocketCommunication() throws DeploymentException, IOException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://localhost:8080/websocket";
        Session session = container.connectToServer(WebSocketClient.class, URI.create(uri));
        final String message = createMessage();
        session.getBasicRemote().sendText(message);
        session.close();
    }

    public static void main(String[] args) {

        try {
            kafkaCommunication();
//			webSocketCommunication();
        } catch (Exception e) {
            log.error("Odesílání dat z klienta selhalo.", e);
        }
    }
}
