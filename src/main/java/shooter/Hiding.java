package shooter;

import shooter.ui.GameDisplay;
import shooter.ui.GameMediator;
import shooter.ui.GamePanel;
import shooter.ui.GameRenderer;
import shooter.world.HideWorld;

public class Hiding {

    public static void main(String[] args) {
        HideWorld world = new HideWorld();
        new GameDisplay(new GamePanel(new GameMediator(world, new GameRenderer(world))), null);
    }

}
