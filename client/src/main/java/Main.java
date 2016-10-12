import controller.ClientController;
import controller.NetworkController;
import controller.ViewController;
import view.impl.ConsoleView;


public class Main {

    private static final int PORT = 5555;
    private static final String ADDRESS = "127.0.0.1";

    public static void main(String[] args) {
        ViewController viewController = new ViewController(new ConsoleView());
        NetworkController networkController = new NetworkController(ADDRESS, PORT);

        ClientController controller = new ClientController(viewController, networkController);
        Thread thread = new Thread(controller);
        thread.start();
    }
}
