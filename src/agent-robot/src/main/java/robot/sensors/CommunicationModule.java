package robot.sensors;


import robot.utils.GlobalSettings;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class CommunicationModule extends AbstractSensor<InputStream> {

    @Override
    public void on() {
        setOn(true);
        start();
        System.out.println("Communication module is on");
    }

    @Override
    public void run() {
        String hostName = GlobalSettings.SERVER_URL;
        int portNumber = 8080;
        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
        }

    }

    @Override
    public void off() {
        setOn(false);
        System.out.println("Communication module is off");
    }

    @Override
    public void status() {
        System.out.println("Communication module enabled status is" + isOn());
    }


    @Override
    public InputStream getData() {
        return null;
    }
}
