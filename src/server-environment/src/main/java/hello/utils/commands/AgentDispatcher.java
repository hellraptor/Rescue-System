package hello.utils.commands;


import hello.beans.AgentRepresentation;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.api.Session;
import utils.AgentCommands;
import utils.AgentStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AgentDispatcher {

    private Map<Integer, AgentRepresentation> agentsStore = new ConcurrentHashMap();
    private AgentRepresentation lastAgent;


    private static AgentDispatcher instance;
    private static final Logger LOG = Log.getLogger(AgentDispatcher.class);

    public static synchronized AgentDispatcher getInstance() {
        if (instance == null) {
            instance = new AgentDispatcher();
        }
        return instance;
    }


    public AgentRepresentation addNewAgent(Session session) {
        AgentRepresentation agent = new AgentRepresentation();
        agent.setSession(session);
        agent.setStatus(AgentStatus.ON);
        agent.setCommand(AgentCommands.START);
        int id;
        if (lastAgent == null) {
            id = 0;
        } else {
            id = lastAgent.getId() + 1;
        }
        agent.setId(id);
        agentsStore.put(agent.getId(), agent);
        return agent;
    }
}
