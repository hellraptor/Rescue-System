package hello.beans;


import org.eclipse.jetty.websocket.api.Session;
import utils.AgentCommands;
import utils.AgentStatus;

public class AgentRepresentation {


    private Session session;
    private AgentStatus status;
    private AgentCommands command;
    private int id;

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setStatus(AgentStatus status) {
        this.status = status;
    }

    public AgentStatus getStatus() {
        return status;
    }

    public void setCommand(AgentCommands command) {
        this.command = command;
    }

    public AgentCommands getCommand() {
        return command;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
