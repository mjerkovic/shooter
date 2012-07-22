package shooter.comms;

import shooter.unit.Entity;

public class ConsoleListener implements MessageListener {

    @Override
    public void onMessage(Entity sender, String message) {
        System.out.println(sender.getClass().getSimpleName() + ": " + message);
    }

}
