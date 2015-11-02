package robot.sensors;

import robot.Pluggable;

/**
 * Created by svyatoslav_yakovlev on 11/1/2015.
 */
public abstract class AbstractSensor<T> extends Thread implements Pluggable {

    public abstract T getData();

    private boolean on;

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
