package network.event.from_client;


import model.Player;
import network.enums.NetworkFromClientEventType;

public class ReadyToPlayNetworkFromClientEvent extends NetworkFromClientEvent {

    private Player player;

    public ReadyToPlayNetworkFromClientEvent(Player player) {
        super(NetworkFromClientEventType.READY_TO_PLAY);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
