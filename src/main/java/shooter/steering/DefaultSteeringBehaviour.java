package shooter.steering;

import shooter.geom.Vector;

public class DefaultSteeringBehaviour implements SteeringBehaviour {

    public Vector calculate() {
        return Vector.ZERO;
    }

}
