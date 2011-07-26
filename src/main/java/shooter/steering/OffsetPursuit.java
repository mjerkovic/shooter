package shooter.steering;

import static shooter.geom.Transformations.pointToWorldSpace;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;

public class OffsetPursuit implements SteeringBehaviour {

    private final MovingEntity entity;
    private final MovingEntity leader;
    private final Vector offset;

    public OffsetPursuit(MovingEntity entity, MovingEntity leader, Vector offset) {
        this.entity = entity;
        this.leader = leader;
        this.offset = offset;
    }

    public Vector calculate() {
        Vector worldOffsetPos = pointToWorldSpace(offset, leader.heading(), leader.side(), leader.position());
        Vector toOffset = worldOffsetPos.subtract(entity.position());
        double lookAheadTime = toOffset.length() / (entity.getMaxSpeed() + leader.velocity().length());
        return new Arrive(entity, worldOffsetPos.add(leader.velocity().scale(lookAheadTime))).calculate();
    }

}
