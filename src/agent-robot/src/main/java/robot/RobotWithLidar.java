package robot;

import robot.sensors.Lidar;
import robot.utils.RoboStatus;

public class RobotWithLidar extends Robot {

    protected Lidar lidar;

    public RobotWithLidar() {
        super();
        lidar = new Lidar();
    }


    public Lidar getLidar() {
        return lidar;
    }

    public void setLidar(Lidar lidar) {
        this.lidar = lidar;
    }

    @Override
    public void on() {
        System.out.println("Initializing...");
        setRoboStatus(RoboStatus.ON);
        getCommunicationModule().on();
        getLidar().on();
        System.out.println(this.getClass().getName().concat(" is on"));
    }


}
