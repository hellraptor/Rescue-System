package robot;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import robot.sensors.CommunicationModule;
import utils.AgentCommands;
import utils.AgentStatus;
import utils.Commands.SimpleMessage;

public class Robot implements Pluggable {

    protected CommunicationModule communicationModule;

    /**
     * if id ==-1 then robot not authorized in system
     */
    private int id = -1;

    private AgentStatus status;

    public Robot() {
        communicationModule = new CommunicationModule();
    }

    public AgentStatus getStatus() {
        return status;
    }

    public void setStatus(AgentStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CommunicationModule getCommunicationModule() {
        return communicationModule;
    }

    public void setCommunicationModule(CommunicationModule communicationModule) {
        this.communicationModule = communicationModule;
    }

    public void on() {
        System.out.println(String.format("Robot %1d on", getId()));
    }

    public void off() {
        System.out.println(String.format("Robot %1d off", getId()));
        System.exit(0);

    }

    public void status() {

    }

    public void authInSystem() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleMessage message = new SimpleMessage(AgentCommands.START, getId());
        communicationModule.sendMessage(mapper.writeValueAsString(message));
    }
}
