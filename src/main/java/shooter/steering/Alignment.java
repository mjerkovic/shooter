package shooter.steering;

import java.util.Collection;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;
import shooter.unit.Vehicle;

public class Alignment implements SteeringBehaviour {

    private final MovingEntity entity;
    private final Collection<Vehicle> neighbours;

    public Alignment(MovingEntity entity, Collection<Vehicle> neighbours) {
        this.entity = entity;
        this.neighbours = neighbours;
    }

    public Vector calculate() {
        Vector averageHeading = Vector.ZERO;
        for (Vehicle neighbour : neighbours) {
            averageHeading.add(neighbour.heading());
        }
        if (neighbours.size() > 0) {
            averageHeading = averageHeading.dividedBy((double) neighbours.size());
            averageHeading = averageHeading.subtract(entity.heading());
        }
        return averageHeading;
    }
    
}
