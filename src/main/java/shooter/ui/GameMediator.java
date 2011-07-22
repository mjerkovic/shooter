package shooter.ui;

import java.awt.*;

import shooter.geom.Vector;
import shooter.unit.Vehicle;
import shooter.world.GameWorld;
import sun.text.normalizer.IntTrie;

public class GameMediator {

    private final GameWorld gameWorld;

    public GameMediator(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void update(double timeDiff) {
        if (timeDiff > 0) {
            gameWorld.update(timeDiff);
        } else {
            System.out.println("Skipped");
        }
    }

    public void renderUsing(GameRenderer renderer) {
        gameWorld.renderVehiclesWith(renderer);
    }

    public void clickRecordedAt(double x, double y) {
        gameWorld.moveVehicleTo(new Vector(x, y));
    }
}
