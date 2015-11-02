package hello;


import java.io.Serializable;

public class SomeClass implements Serializable {
    String string;
    int anInt;
    boolean aBoolean;

    public SomeClass(String s, int anInt) {
        this.string = s;
        this.anInt = anInt;
        aBoolean = true;
    }
}
