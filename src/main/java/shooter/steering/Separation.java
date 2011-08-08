package shooter.steering;

import java.util.Collection;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;
import shooter.unit.Vehicle;

public class Separation implements SteeringBehaviour {

    private final MovingEntity entity;
    private final Collection<Vehicle> neighbours;

    public Separation(MovingEntity entity, Collection<Vehicle> neighbours) {
        this.entity = entity;
        this.neighbours = neighbours;
    }

    public Vector calculate() {
        Vector steeringForce = Vector.ZERO;
        for (Vehicle neighbour : neighbours) {
            Vector to = entity.position().subtract(neighbour.position());
            steeringForce.add(to.normalise().dividedBy(to.length()));
        }
        return steeringForce;
    }

}
