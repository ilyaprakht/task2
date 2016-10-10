package network.event.from_client;


import network.enums.NetworkFromClientEventType;

import java.io.Serializable;

public class NetworkFromClientEvent implements Serializable {

    private NetworkFromClientEventType eventType;

    public NetworkFromClientEvent(NetworkFromClientEventType eventType) {
        this.eventType = eventType;
    }

    public NetworkFromClientEventType getEventType() {
        return eventType;
    }
}
