package shooter.comms;

import shooter.unit.Entity;

import javax.swing.*;

public class BattlefieldMessageListener implements MessageListener {

    private final JTextArea messageArea;

    public BattlefieldMessageListener(JTextArea messageArea) {
        this.messageArea = messageArea;
    }

    @Override
    public void onMessage(Entity sender, String message) {
        messageArea.append(sender.getClass().getSimpleName() + ": " +  message);
    }

}
