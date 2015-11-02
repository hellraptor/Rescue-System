package robot;


import robot.sensors.CommunicationModule;
import robot.utils.RoboStatus;

public class Robot implements Pluggable {

    protected CommunicationModule communicationModule;

    /**
     * if id ==-1 then robot not authorized in system
     */
    private int id;

    private RoboStatus roboStatus;

    public Robot() {
        communicationModule = new CommunicationModule();
        id = -1;
    }

    public RoboStatus getRoboStatus() {
        return roboStatus;
    }

    public void setRoboStatus(RoboStatus roboStatus) {
        this.roboStatus = roboStatus;
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

    @Override
    public void on() {
        System.out.println(String.format("Robot %1d on", getId()));
    }

    @Override
    public void off() {
        System.out.println(String.format("Robot %1d off", getId()));
        System.exit(0);

    }

    @Override
    public void status() {

    }
}
