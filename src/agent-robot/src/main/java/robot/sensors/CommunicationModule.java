package robot.sensors;


import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class CommunicationModule extends AbstractSensor {

    private WebSocketClient client;
    private SimpleSocket socket;

    public void on() {
        setOn(true);
        String destUri = "ws://localhost:8089/echo";
        init(destUri);
        sendMessage("привет");
        System.out.println("Communication module is on");
    }

    private void init(String destUri) {
        client = new WebSocketClient();
        socket = new SimpleSocket();
        try {
            client.start();
            URI echoUri = new URI(destUri);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(socket, echoUri, request);
            System.out.printf("Connecting to : %s%n", echoUri);
            socket.awaitClose(5, TimeUnit.SECONDS);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void sendMessage(String msg) {
        socket.sendMessage(msg);
    }


    public void off() {
        setOn(false);
        System.out.println("Communication module is off");
    }

    public void status() {
        System.out.println("Communication module enabled status is" + isOn());
    }
}
