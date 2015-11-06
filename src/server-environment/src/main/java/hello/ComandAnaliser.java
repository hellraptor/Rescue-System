package hello;


import org.springframework.stereotype.Service;

@Service
public class ComandAnaliser {

    private static ComandAnaliser instance;

    public static synchronized ComandAnaliser getInstance() {
        if (instance == null) {
            instance = new ComandAnaliser();
        }
        return instance;
    }

    public void methodOne() {

        System.out.println("method one");
    }


}
