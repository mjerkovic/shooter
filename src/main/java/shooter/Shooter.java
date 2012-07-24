package shooter;

import javax.swing.JTextArea;

import shooter.comms.BattlefieldMessageListener;
import shooter.comms.MessageListener;
import shooter.geom.Vector;
import shooter.ui.GameDisplay;
import shooter.ui.GameMediator;
import shooter.ui.InfoPanel;
import shooter.ui.map.MapDisplay;
import shooter.ui.MessageDisplay;
import shooter.ui.ShooterPanel;
import shooter.ui.map.WorldMap;
import shooter.world.ShooterWorld;

public class Shooter {

    public static void main(String[] args) {
        JTextArea messageArea = new JTextArea(20, 1);
        messageArea.setEditable(false);
        MessageListener battleFieldMessageListener = new BattlefieldMessageListener(messageArea);

        Vector worldArea = new Vector(1800, 1800);
        ShooterWorld world = new ShooterWorld(worldArea, battleFieldMessageListener);

        new MessageDisplay(messageArea);
        new WorldMap(new MapDisplay(world));
        new GameDisplay(new ShooterPanel(new GameMediator(world)), new InfoPanel(world));
    }

}
