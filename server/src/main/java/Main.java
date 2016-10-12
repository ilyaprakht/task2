import controller.ConnectionController;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {

    private static final int PORT = 5555;
    private static final String LOGER_PATH = "server/src/main/resources/log4j.properties";

    public static void main(String[] args) {
        try {
            Properties property = new Properties();
            property.load(new FileInputStream(LOGER_PATH));
            PropertyConfigurator.configure(property);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConnectionController connectionController = new ConnectionController(PORT);
        Thread thread = new Thread(connectionController);
        thread.start();
    }
}