package shooter.unit;

import shooter.geom.Vector;
import shooter.steering.Steering;

public class WatchTower extends MovingEntity {

    public WatchTower(int x, int y, Steering steering) {
        super(steering);
        steering.setOwner(this);
        position = new Vector(x, y);
    }

    @Override
    public void update() {
        Vector steeringForce = steering.calculate();
        heading = steeringForce.normalise();
        side = heading.perp();
    }

    public void track(MovingEntity evader) {
        steering.pursuitOn(evader);
    }

}
