package shooter;

import shooter.comms.BattlefieldMessageListener;
import shooter.comms.MessageListener;
import shooter.ui.*;
import shooter.world.ShooterWorld;

import javax.swing.*;

public class Shooter {

    public static void main(String[] args) {
        JTextArea messageArea = new JTextArea(20, 1);
        messageArea.setEditable(false);
        MessageListener battleFieldMessageListener = new BattlefieldMessageListener(messageArea);

        new MessageDisplay(messageArea);

        ShooterWorld world = new ShooterWorld(battleFieldMessageListener);
        new GameDisplay(new ShooterPanel(new GameMediator(world)), new InfoPanel(world));
    }

}
