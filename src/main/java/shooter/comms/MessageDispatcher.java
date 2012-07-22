package shooter.comms;

import com.google.common.collect.Lists;
import shooter.unit.Entity;

import java.util.Collection;

public class MessageDispatcher {

    private final Collection<MessageListener> messageListeners;

    public MessageDispatcher(Collection<MessageListener> messageListeners) {
        this.messageListeners = Lists.newArrayList(messageListeners);
    }

    public void broadcast(Entity sender, String message) {
        for (MessageListener listener : messageListeners) {
            listener.onMessage(sender, message);
        }
    }

}
