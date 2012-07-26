package shooter.goals.watchtower;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.TargetingSystem;
import shooter.world.ShooterWorld;

public class Scan extends SimpleGoal<TargetingSystem> {

    private final ShooterWorld world;

    public Scan(ShooterWorld world) {
        this.world = world;
    }

    @Override
    protected void doActivation(TargetingSystem targetingSystem) {
    }

    @Override
    protected GoalState doProcess(TargetingSystem targetingSystem) {
         return (targetingSystem.hasTarget() ||
                 targetingSystem.acquireTarget(world.getVehicles())) ? GoalState.COMPLETED : GoalState.ACTIVE;
    }


    public void terminate(TargetingSystem targetingSystem) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
