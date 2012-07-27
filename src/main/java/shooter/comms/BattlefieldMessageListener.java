package shooter.comms;

import javax.swing.*;

import shooter.unit.Entity;

public class BattlefieldMessageListener implements MessageListener {

    private final JTextArea messageArea;

    public BattlefieldMessageListener(JTextArea messageArea) {
        this.messageArea = messageArea;
    }

    @Override
    public void onMessage(Entity sender, String message) {
        messageArea.append(sender.getClass().getSimpleName() + ": " +  message + "\n");
    }

}
