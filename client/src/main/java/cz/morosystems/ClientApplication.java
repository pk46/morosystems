package cz.morosystems;

import jakarta.websocket.ContainerProvider;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import lombok.extern.slf4j.Slf4j;
import utilities.Serializer;

import java.io.IOException;
import java.net.URI;

import static java.lang.Thread.sleep;


//@SpringBootApplication
@Slf4j
public class ClientApplication {

	private static String createMessage() throws IOException {
		return Serializer.serialize(SystemInfoGenerator.generate());
	}
	public static void main(String[] args) {

//		SpringApplication.run(ClientApplication.class, args);
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			String uri = "ws://localhost:8080/websocket";
			Session session = container.connectToServer(WebSocketClient.class, URI.create(uri));

			for(int i = 0; i < 3; i++) {
				sleep(1000);
				final String message = createMessage();
				log.error(message);
				session.getBasicRemote().sendText("message");
			}
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
