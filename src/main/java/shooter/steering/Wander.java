package shooter.steering;

import static java.lang.Math.random;
import static shooter.geom.Transformations.pointToWorldSpace;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class Wander implements SteeringBehaviour {

    private static final double WANDER_RADIUS = 2.0;
    private static final double WANDER_DISTANCE = 4.0;
    private static final double WANDER_JITTER = 80.0;

    private final MovingEntity entity;

    public Wander(MovingEntity entity) {
        this.entity = entity;
    }

    public Vector calculate() {
        Vector wanderTarget = new Vector(randomNumber(), randomNumber()).normalise().scale(WANDER_RADIUS);
        Vector targetLocal = wanderTarget.add(new Vector(WANDER_DISTANCE, 0));
        Vector targetWorld = pointToWorldSpace(targetLocal, entity.heading(), entity.side(),
                entity.position());
        return targetWorld.subtract(entity.position());
    }

    private double randomNumber() {
        return (random() - random()) * WANDER_JITTER;
    }

}
