package cz.kupcik.morosystems.client;

import cz.kupcik.morosystems.core.utilities.Serializer;
import jakarta.websocket.ContainerProvider;
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

	public static void main(String[] args) {

		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			String uri = "ws://localhost:8080/websocket";
			Session session = container.connectToServer(WebSocketClient.class, URI.create(uri));
			final String message = createMessage();
			log.error(message);
			session.getBasicRemote().sendText(message);
			// session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
