package shooter.comms;

import java.util.ArrayList;
import java.util.Collection;

import shooter.geom.Vector;
import shooter.unit.Entity;

public class MessageDispatcher {

    private static final Collection<MessageListener> messageListeners = new ArrayList<MessageListener>();
    private static final Collection<EventListener> eventListeners = new ArrayList<EventListener>();

    public static void addMessageListener(MessageListener messageListener) {
        messageListeners.add(messageListener);
    }

    public static void addEventListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    public static void broadcast(Entity sender, String message) {
        for (MessageListener listener : messageListeners) {
            listener.onMessage(sender, message);
        }
    }

    public static void shotFired(Entity shooter, Vector heading, Entity target) {
        for (EventListener eventListener : eventListeners) {
            eventListener.shotFired(shooter, heading, target);
        }
    }

}
