import controller.ClientController;
import controller.NetworkController;
import controller.ViewController;
import org.apache.log4j.PropertyConfigurator;
import view.impl.ConsoleView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Main {

    private static final int PORT = 5555;
    private static final String ADDRESS = "127.0.0.1";
    private static final String LOGER_PATH = "client/src/main/resources/log4j.properties";

    public static void main(String[] args) {
        try {
            Properties property = new Properties();
            property.load(new FileInputStream(LOGER_PATH));
            PropertyConfigurator.configure(property);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ViewController viewController = new ViewController(new ConsoleView());
        NetworkController networkController = new NetworkController(ADDRESS, PORT);

        ClientController controller = new ClientController(viewController, networkController);
        Thread thread = new Thread(controller);
        thread.start();
    }
}
