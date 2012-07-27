package shooter.unit;

import shooter.goals.Goal;

public class Scout extends Vehicle {

    public Scout(Orientation orientation, Goal<MovingEntity> brain, Movement movement) {
        super(orientation, brain, movement);
    }

}
