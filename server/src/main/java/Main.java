import controller.ConnectionController;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.util.Properties;

public class Main {

    private static final int PORT = 5005;
    private static final String LOGER_PATH = "server/src/main/resources/log4j.properties";

    public static void main(String[] args) {
        try {
            Properties property = new Properties();
            property.load(new FileInputStream(LOGER_PATH));
            PropertyConfigurator.configure(property);
        } catch (Exception e) {
            System.err.println("Cannot init loger: " + e.getMessage());
        }

        ConnectionController connectionController = new ConnectionController(PORT);
        Thread thread = new Thread(connectionController);
        thread.start();
    }
}