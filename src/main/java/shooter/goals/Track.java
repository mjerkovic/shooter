package shooter.goals;

import shooter.unit.MovingEntity;
import shooter.world.ShooterWorld;

public class Track extends BaseGoal {

    private final ShooterWorld world;

    private MovingEntity target;

    public Track(ShooterWorld world) {
        this.world = world;
    }

    @Override
    protected void activateGoal(MovingEntity entity) {
        target = world.getWanderer();
        entity.steering().pursuitOn(target);
        entity.steering().fireOn(target);
    }

    @Override
    protected void processGoal(MovingEntity entity) {
    }

}
