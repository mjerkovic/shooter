package shooter.goals.watchtower;

import shooter.goals.CompositeGoal;
import shooter.goals.GoalState;
import shooter.unit.TargetingSystem;
import shooter.world.ShooterWorld;

import static shooter.goals.GoalState.ACTIVE;

public class WatchtowerDuty extends CompositeGoal<TargetingSystem> {

    private final ShooterWorld world;

    public WatchtowerDuty(ShooterWorld world) {
        this.world = world;
    }

    @Override
    public void activate(TargetingSystem targetingSystem) {
        addSubGoal(new Track());
        addSubGoal(new Scan(world));
        super.activate(targetingSystem);
    }

    @Override
    public GoalState process(TargetingSystem targetingSystem) {
        GoalState goalState = super.process(targetingSystem);
        if (subGoals.isEmpty()) {
            activate(targetingSystem);
            return ACTIVE;
        } else {
            return goalState;
        }
    }

    @Override
    public void terminate(TargetingSystem targetingSystem) {
        activate(targetingSystem);
    }

}
