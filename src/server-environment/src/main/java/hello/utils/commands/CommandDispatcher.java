package hello.utils.commands;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.springframework.stereotype.Service;
import utils.AgentCommands;
import utils.Commands.SimpleMessage;

@Service
public class CommandDispatcher {

    private static CommandDispatcher instance;
    private static final Logger LOG = Log.getLogger(CommandDispatcher.class);

    public static synchronized CommandDispatcher getInstance() {
        if (instance == null) {
            instance = new CommandDispatcher();
        }
        return instance;
    }

    public SimpleMessage parseCommand(String jsonStringCommand) throws Exception {

        if (jsonStringCommand == null || jsonStringCommand.length() == 0) {
            LOG.info("Command is empty: {} ", jsonStringCommand);
            throw new NullPointerException("Command is empty");
        }

        ObjectMapper mapper = new ObjectMapper();
        SimpleMessage simpleMessage = mapper.readValue(jsonStringCommand, SimpleMessage.class);
        LOG.info("Command is: robot - {} must {}", simpleMessage.getRobotId(), simpleMessage.getCommand());
        return simpleMessage;
    }


    public String generateCommand(AgentCommands command, int agentId) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new SimpleMessage(command, agentId));
    }
}
