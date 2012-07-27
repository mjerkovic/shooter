package shooter.comms;

import java.util.Collection;

import com.google.common.collect.Lists;
import shooter.geom.Vector;
import shooter.unit.Entity;

public class MessageDispatcher {

    private final Collection<MessageListener> messageListeners;
    private final Collection<EventListener> eventListeners;

    public MessageDispatcher(Collection<MessageListener> messageListeners, Collection<EventListener> eventListeners) {
        this.eventListeners = eventListeners;
        this.messageListeners = Lists.newArrayList(messageListeners);
    }

    public void broadcast(Entity sender, String message) {
        for (MessageListener listener : messageListeners) {
            listener.onMessage(sender, message);
        }
    }

    public void shotFired(Entity shooter, Vector heading, Entity target) {
        for (EventListener eventListener : eventListeners) {
            eventListener.shotFired(shooter, heading, target);
        }
    }

}
