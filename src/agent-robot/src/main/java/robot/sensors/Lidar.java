package robot.sensors;


import robot.data.CloudOfPoints;

public class Lidar extends AbstractSensor {


    public CloudOfPoints getData() {
        return null;
    }

    public void on() {
        setOn(true);
        System.out.println("Lidar is on");
    }

    public void off() {
        setOn(false);
        System.out.println("Lidar is off");
    }

    public void status() {
        System.out.println("Lidar enabled status is" + isOn());

    }
}
