package cz.kupcik.morosystems.client;

import cz.kupcik.morosystems.core.utilities.Serializer;
import cz.kupcik.morosystems.kafka.Producer;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.URI;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@Slf4j
public class ClientApplication {

    private static Session session;

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
        session = container.connectToServer(WebSocketClient.class, URI.create(uri));
    }

    @Scheduled(fixedRate = 5000)
    private static void sendMessage() {
        try {
            final String message = createMessage();
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("Odesílání dat z klienta selhalo.", e);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
        try {
//            kafkaCommunication();
			webSocketCommunication();
        } catch (Exception e) {
            log.error("Spojení se serverem se nepodařilo navázat.");
        }
    }
}
