package service;

import model.hub.HubEvent;
import model.hub.HubEventType;

public interface HubEventHandler {
    HubEventType getMessageType();

    void handle(HubEvent event);
}
