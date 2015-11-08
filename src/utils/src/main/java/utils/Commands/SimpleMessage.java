package utils.Commands;

import utils.AgentCommands;

public class SimpleMessage {
    private AgentCommands command;
    private int robotId;

    public SimpleMessage(AgentCommands command, int robotId) {
        this.command = command;
        this.robotId = robotId;
    }

    public int getRobotId() {
        return robotId;
    }

    public void setRobotId(int robotId) {
        this.robotId = robotId;
    }

    public AgentCommands getCommand() {
        return command;
    }

    public void setCommand(AgentCommands command) {
        this.command = command;
    }
}
