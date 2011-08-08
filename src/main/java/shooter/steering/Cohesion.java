package shooter.steering;

import java.util.Collection;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;
import shooter.unit.Vehicle;

public class Cohesion implements SteeringBehaviour {

    private final MovingEntity entity;
    private final Collection<Vehicle> neighbours;

    public Cohesion(MovingEntity entity, Collection<Vehicle> neighbours) {
        this.entity = entity;
        this.neighbours = neighbours;
    }

    public Vector calculate() {
        Vector steeringForce = Vector.ZERO;
        Vector centreOfMass = Vector.ZERO;
        for (Vehicle neighbour : neighbours) {
            centreOfMass.add(neighbour.position());
        }
        if (neighbours.size() > 0) {
            centreOfMass = centreOfMass.dividedBy(neighbours.size());
            steeringForce = new SeekBehaviour().calculate(entity, centreOfMass);
        }
        return steeringForce;
    }

}
