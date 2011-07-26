package shooter.steering;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;
import shooter.world.ShooterWorld;

public class Steering {

    private final ShooterWorld world;
    private UserControlledBehaviour userControlledBehaviour = new UserControlledBehaviour();

    private boolean wander;
    private boolean pursuit;
    private boolean userControlled;
    private boolean offsetPursuit;

    private MovingEntity owner;
    private MovingEntity evader;
    private Direction direction;
    private MovingEntity leader;
    private Vector offset;

    public Steering(ShooterWorld world) {
        this.world = world;
    }

    public Vector calculate() {
        Vector steeringForce = Vector.ZERO;
        if (wander) {
            steeringForce = steeringForce.add(new Wander(owner).calculate());
        }
        if (pursuit) {
            steeringForce = steeringForce.add(new Pursuit(owner, evader).calculate());
        }
        if (offsetPursuit) {
            steeringForce = steeringForce.add(new OffsetPursuit(owner, leader, offset).calculate());
        }
        if (userControlled) {
            steeringForce = userControlledBehaviour.calculate(owner, direction);
        }
        return steeringForce;
    }

    public void wanderOn() {
        wander = true;
    }

    public void wanderOff() {
        wander = false;
    }

    public void pursuitOn(MovingEntity evader) {
        this.evader = evader;
        pursuit = true;
    }

    public void pursuitOff() {
        this.evader = null;
        pursuit = false;
    }

    public void setOwner(MovingEntity owner) {
        this.owner = owner;
    }

    public void directionOn(Direction direction) {
        this.direction = direction;
        userControlled = true;
    }

    public void directionOff() {
        userControlled = false;
    }

    public void offsetPursuitOn(MovingEntity owner, MovingEntity leader, Vector offset) {
        this.owner = owner;
        this.leader = leader;
        this.offset = offset;
        offsetPursuit = true;
    }

    public void offsetPursuitOff() {
        this.owner = null;
        this.leader = null;
        this.offset = null;
        offsetPursuit = false;
    }

}
