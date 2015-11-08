package robot;


import com.fasterxml.jackson.core.JsonProcessingException;

public class Starter {

    public static void main(String[] args) throws JsonProcessingException {
        Robot robot = new RobotWithLidar();
        robot.on();
        robot.authInSystem();
    }

}
