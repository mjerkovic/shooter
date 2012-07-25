package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.steering.Steering;
import shooter.ui.Renderer;
import shooter.world.ShooterWorld;

public class WatchTower extends Entity implements Armed {

    private Weapon weapon;

    public WatchTower(Vector position, double radius, MessageDispatcher radio, ShooterWorld world, Steering steering) {
        super(position, radius, radio);
    }

    public void armWith(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void update() {
        if (weapon != null) {
            weapon.update();
        }
    }

    public void renderWith(Renderer renderer) {
        renderer.render(this);
        if (weapon != null) {
            weapon.renderWith(renderer);
        }
    }

}
