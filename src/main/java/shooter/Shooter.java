package shooter;

import shooter.ui.GameDisplay;
import shooter.ui.GameMediator;
import shooter.ui.GameRenderer;
import shooter.ui.ShooterPanel;
import shooter.world.ShooterWorld;

public class Shooter {
    
    private static ShooterPanel panel;

    public static void main(String[] args) {
        ShooterWorld gameWorld = new ShooterWorld();
        GameMediator mediator = new GameMediator(gameWorld);
        panel = new ShooterPanel(mediator);
        GameDisplay display = new GameDisplay(panel);
    }

}
