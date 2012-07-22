package shooter.comms;

import shooter.unit.Entity;

public interface MessageListener {

    void onMessage(Entity sender, String message);

}
