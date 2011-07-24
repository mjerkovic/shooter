package shooter.steering;

import shooter.geom.Vector;
import shooter.unit.MovingEntity;
import shooter.world.ShooterWorld;

public class Steering {

    private final ShooterWorld world;
    private final Fire fireBehaviour = new Fire();
    private UserControlledBehaviour userControlledBehaviour = new UserControlledBehaviour();

    private boolean wander;
    private boolean pursuit;
    private boolean fire;
    private boolean userControlled;

    private MovingEntity owner;
    private MovingEntity evader;
    private MovingEntity target;
    private Direction direction;

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
        if (fire) {
            fireBehaviour.calculate(owner, target, world);
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

    public void fireOn(MovingEntity target) {
        this.target = target;
        fire = true;
    }

    public void fireOff() {
        fire = false;
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

}
