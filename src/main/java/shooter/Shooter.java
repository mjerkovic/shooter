package shooter;

import shooter.ui.GameDisplay;
import shooter.ui.GameMediator;
import shooter.ui.ShooterPanel;
import shooter.world.ShooterWorld;

public class Shooter {

    public static void main(String[] args) {
        new GameDisplay(new ShooterPanel(new GameMediator(new ShooterWorld())));
    }

}
