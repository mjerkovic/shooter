package shooter.steering;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;
import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class SteeringBehaviours {

    private List<SteeringBehaviour> steeringBehaviours;

    public SteeringBehaviours(List<SteeringBehaviour> steeringBehaviours) {
        this.steeringBehaviours = Lists.newArrayList(steeringBehaviours);
    }

    public Vector calculateForce() {
        Vector force = Vector.ZERO;
        Iterator<SteeringBehaviour> behaviourIterator = steeringBehaviours.iterator();
        while (behaviourIterator.hasNext()) {
            SteeringBehaviour steeringBehaviour = behaviourIterator.next();
            Vector steeringForce = steeringBehaviour.calculate();
            force = force.add(steeringForce);
            if (steeringForce.isZero()) {
                steeringBehaviour.terminate();
                behaviourIterator.remove();
            }
        }
        return force;
    }

    public void move(MovingEntity entity, Vector toPosition) {
        steeringBehaviours.add(new ArriveBehaviour(entity, toPosition));
    }

}
