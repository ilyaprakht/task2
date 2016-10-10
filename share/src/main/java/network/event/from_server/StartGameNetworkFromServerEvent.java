package network.event.from_server;


import model.Player;
import network.enums.NetworkFromServerEventType;

public class StartGameNetworkFromServerEvent extends NetworkFromServerEvent {

    private Player player1;
    private Player player2;

    public StartGameNetworkFromServerEvent(Player player1, Player player2) {
        super(NetworkFromServerEventType.START_GAME);
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

}
