package hello;

import hello.beans.AgentRepresentation;
import hello.utils.commands.AgentDispatcher;
import hello.utils.commands.CommandDispatcher;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import utils.AgentCommands;
import utils.Commands.SimpleMessage;

import java.io.IOException;

@WebSocket
public class EchoSocket {
    private static final Logger LOG = Log.getLogger(EchoSocket.class);
    private Session session;
    private RemoteEndpoint remote;

    CommandDispatcher commandDispatcher = CommandDispatcher.getInstance();
    private AgentDispatcher agentDispatcher = AgentDispatcher.getInstance();

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
            try {
                SimpleMessage simpleMessage = commandDispatcher.parseCommand(message);
                if (AgentCommands.START.equals(simpleMessage.getCommand())) {
                    AgentRepresentation agent = agentDispatcher.addNewAgent(session);
                    this.remote.sendStringByFuture(commandDispatcher.generateCommand(AgentCommands.START, agent.getId()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
