package shooter;

import javax.swing.*;

import shooter.comms.BattlefieldMessageListener;
import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.ui.GameDisplay;
import shooter.ui.GameMediator;
import shooter.ui.GameRenderer;
import shooter.ui.InfoPanel;
import shooter.ui.MessageDisplay;
import shooter.ui.ShooterPanel;
import shooter.ui.map.MapDisplay;
import shooter.ui.map.WorldMap;
import shooter.world.ShooterWorld;

public class Shooter {

    public static void main(String[] args) {
        JTextArea messageArea = new JTextArea(20, 1);
        messageArea.setEditable(false);
        MessageDispatcher.addMessageListener(new BattlefieldMessageListener(messageArea));

        Vector worldArea = new Vector(1800, 1800);
        ShooterWorld world = new ShooterWorld(worldArea);

        new MessageDisplay(messageArea);
        new WorldMap(new MapDisplay(world));
        new GameDisplay(new ShooterPanel(new GameMediator(world, new GameRenderer(world))), new InfoPanel(world));
    }

}
