package shooter.steering;

import shooter.geom.Vector;

public interface SteeringBehaviour {

    Vector calculate();

    void terminate();
}
