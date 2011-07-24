package shooter;

import shooter.ui.GameDisplay;
import shooter.ui.GameMediator;
import shooter.ui.InfoPanel;
import shooter.ui.ShooterPanel;
import shooter.world.ShooterWorld;

public class Shooter {

    public static void main(String[] args) {
        ShooterWorld world = new ShooterWorld();
        new GameDisplay(new ShooterPanel(new GameMediator(world)), new InfoPanel(world));
    }

}
