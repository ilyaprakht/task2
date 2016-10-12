import controller.ConnectionController;

public class Main {

    private static final int PORT = 5555;

    public static void main(String[] args) {
        ConnectionController connectionController = new ConnectionController(PORT);
        Thread thread = new Thread(connectionController);
        thread.start();
    }
}