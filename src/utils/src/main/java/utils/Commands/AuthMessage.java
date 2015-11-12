package utils.Commands;

import utils.AgentCommands;

public class AuthMessage extends AbstractMessage {

    public AuthMessage(AgentCommands command, int robotId) {
        this.command = command;
        this.robotId = robotId;
    }

    public AuthMessage(int robotId) {
        new AuthMessage(AgentCommands.AUTHENTICATE, robotId);
    }
}
