import controller.ClientController;
import controller.NetworkController;
import controller.ViewController;
import org.apache.log4j.PropertyConfigurator;
import view.impl.ConsoleView;

import java.io.FileInputStream;
import java.util.Properties;


public class Main {

    private static final int PORT = 5005;
    private static final String ADDRESS = "localhost";
    private static final String LOGER_PATH = "client/src/main/resources/log4j.properties";

    public static void main(String[] args) {
        try {
            Properties property = new Properties();
            property.load(new FileInputStream(LOGER_PATH));
            PropertyConfigurator.configure(property);
        } catch (Exception e) {
            System.err.println("Cannot init loger: " + e.getMessage());
        }

        ViewController viewController = new ViewController(new ConsoleView());
        NetworkController networkController = new NetworkController(ADDRESS, PORT);

        ClientController controller = new ClientController(viewController, networkController);
        Thread thread = new Thread(controller);
        thread.start();
    }
}
