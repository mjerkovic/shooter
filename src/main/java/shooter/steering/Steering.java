package shooter.steering;

import shooter.geom.Rotation;
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
    private boolean obstacleAvoidance;
    private boolean wallAvoidance;
    private boolean arrive;

    private MovingEntity owner;
    private MovingEntity evader;
    private Direction direction;
    private Rotation rotation;
    private MovingEntity leader;
    private Vector offset;
    private Vector arrivalPosition;

    public Steering(ShooterWorld world) {
        this.world = world;
    }

    public Vector calculate() {
        Vector steeringForce = Vector.ZERO;
        if (arrive) {
            steeringForce = steeringForce.add(new Arrive(owner, arrivalPosition).calculate());
        }
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
            steeringForce = userControlledBehaviour.calculate(owner, direction, rotation);
        }
        if (obstacleAvoidance) {
            steeringForce = steeringForce.add(new ObstacleAvoidance(owner, world.getObstacles()).calculate());
        }
        if (wallAvoidance) {
            steeringForce = steeringForce.add(new WallAvoidance(owner, world.getWalls()).calculate());
        }
        return steeringForce;
    }

    public Steering wanderOn() {
        wander = true;
        return this;
    }

    public Steering wanderOff() {
        wander = false;
        return this;
    }

    public Steering pursuitOn(MovingEntity evader) {
        this.evader = evader;
        pursuit = true;
        return this;
    }

    public Steering pursuitOff() {
        this.evader = null;
        pursuit = false;
        return this;
    }

    public void setOwner(MovingEntity owner) {
        this.owner = owner;
    }

    public Steering directionOn(Direction direction) {
        this.direction = direction;
        this.rotation = owner.heading().rotationTo(direction.heading());
        userControlled = true;
        return this;
    }

    public Steering directionOff() {
        userControlled = false;
        return this;
    }

    public Steering offsetPursuitOn(MovingEntity leader, Vector offset) {
        this.leader = leader;
        this.offset = offset;
        offsetPursuit = true;
        return this;
    }

    public Steering offsetPursuitOff() {
        this.leader = null;
        this.offset = null;
        offsetPursuit = false;
        return this;
    }

    public Steering obstacleAvoidanceOn() {
        obstacleAvoidance = true;
        return this;
    }

    public Steering obstacleAvoidanceOff() {
        obstacleAvoidance = false;
        return this;
    }

    public Steering wallAvoidanceOn() {
        wallAvoidance = true;
        return this;
    }

    public Steering wallAvoidanceOff() {
        wallAvoidance = false;
        return this;
    }

    public void arriveOn(Vector position) {
        arrivalPosition = position;
        arrive  = true;
    }

    public void arriveOff() {
        arrive = false;
    }
}
