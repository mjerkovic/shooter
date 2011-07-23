package shooter.steering;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class Steering {

    private boolean wander;

    private MovingEntity owner;

    public Vector calculate() {
        Vector steeringForce = Vector.ZERO;
        if (wander) {
            steeringForce = steeringForce.add(new Wander(owner).calculate());
        }
        return steeringForce;
    }

    public void wanderOn() {
        wander = true;
    }

    public void wanderOff() {
        wander = false;
    }

    public void setOwner(MovingEntity owner) {
        this.owner = owner;
    }

}
