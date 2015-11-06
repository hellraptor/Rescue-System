package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.beans.SimpleMessage;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;

@WebSocket
public class EchoSocket {
    private static final Logger LOG = Log.getLogger(EchoSocket.class);
    private Session session;
    private RemoteEndpoint remote;

    ComandAnaliser comandAnaliser = ComandAnaliser.getInstance();

    @OnWebSocketClose
    public void onWebSocketClose(int statusCode, String reason) {
        this.session = null;
        this.remote = null;
        LOG.info("WebSocket Close: {} - {}", statusCode, reason);
    }

    @OnWebSocketConnect
    public void onWebSocketConnect(Session session) {
        this.session = session;
        this.remote = this.session.getRemote();
        LOG.info("WebSocket Connect: {}", session);
        this.remote.sendStringByFuture("You are now connected to " + this.getClass().getName());
    }

    @OnWebSocketError
    public void onWebSocketError(Throwable cause) {
        LOG.warn("WebSocket Error", cause);
    }

    @OnWebSocketMessage
    public void onWebSocketText(String message) {
        if (this.session != null && this.session.isOpen() && this.remote != null) {
            LOG.info("Echoing back text message [{}]", message);

            ObjectMapper mapper = new ObjectMapper();
            SimpleMessage simpleMessage = null;
            try {
                simpleMessage = mapper.readValue(message, SimpleMessage.class);

                String command = simpleMessage.getCommand();
                if (command.equals("start")) {

                }
                comandAnaliser.methodOne();
                this.remote.sendStringByFuture(simpleMessage.getCommand());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
