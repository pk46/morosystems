package cz.morosystems;

import jakarta.websocket.ContainerProvider;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;

import java.net.URI;

import static java.lang.Thread.sleep;


//@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {

//		SpringApplication.run(ClientApplication.class, args);
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			String uri = "ws://localhost:8080/websocket";
			Session session = container.connectToServer(WebSocketClient.class, URI.create(uri));

			for(int i = 0; i < 3; i++) {
				sleep(1000);
				session.getBasicRemote().sendText("Hello server");
			}
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
