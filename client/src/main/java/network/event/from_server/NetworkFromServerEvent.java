package network.event.from_server;

import network.enums.NetworkFromServerEventType;

import java.io.Serializable;


public class NetworkFromServerEvent implements Serializable {

    private NetworkFromServerEventType eventType;

    public NetworkFromServerEvent(NetworkFromServerEventType eventType) {
        this.eventType = eventType;
    }

    public NetworkFromServerEventType getEventType() {
        return eventType;
    }
}
