package robot.sensors;


import robot.data.CloudOfPoints;

public class Lidar extends AbstractSensor<CloudOfPoints>  {


    @Override
    public CloudOfPoints getData() {
        return null;
    }

    @Override
    public void on() {
        setOn(true);
        System.out.println("Lidar is on");
    }

    @Override
    public void off() {
        setOn(false);
        System.out.println("Lidar is off");
    }

    @Override
    public void status() {
        System.out.println("Lidar enabled status is" + isOn());

    }
}
