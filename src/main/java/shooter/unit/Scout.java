package shooter.unit;

import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.goals.Scouting;
import shooter.steering.Steering;
import shooter.ui.Renderer;
import shooter.world.ShooterWorld;

public class Scout extends Vehicle implements Armed {

    private Weapon weapon;

    public Scout(Vector position, double radius, Vector heading, double maxTurnRate, MessageDispatcher radio,
                 Steering steering, ShooterWorld world) {
        super(position, radius, heading, maxTurnRate, radio, new Scouting(), steering);
    }

    public void armWith(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void update() {
        super.update();
        weapon.update();
    }

    @Override
    public void renderWith(Renderer renderer) {
        super.renderWith(renderer);
        renderWeaponWith(renderer);
    }

    private void renderWeaponWith(Renderer renderer) {
        if (weapon != null) {
            weapon.renderWith(renderer);
        }
    }

}
