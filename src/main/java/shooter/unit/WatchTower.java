package shooter.unit;

import shooter.geom.Vector;
import shooter.goals.Goal;
import shooter.steering.Steering;

public class WatchTower extends MovingEntity {

    public WatchTower(int x, int y, Goal goal, Steering steering) {
        super(goal, steering);
        steering.setOwner(this);
        position = new Vector(x, y);
    }

    @Override
    public void update() {
        goal.process(this);
        Vector steeringForce = steering.calculate();
        heading = steeringForce.normalise();
        side = heading.perp();
    }

}
