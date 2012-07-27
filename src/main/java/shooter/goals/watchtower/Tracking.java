package shooter.goals.watchtower;

import static shooter.goals.GoalState.ACTIVE;

import shooter.goals.CompositeGoal;
import shooter.goals.GoalState;
import shooter.unit.TargetingSystem;
import shooter.world.ShooterWorld;

public class Tracking extends CompositeGoal<TargetingSystem> {

    private final ShooterWorld world;

    public Tracking(ShooterWorld world) {
        super("TargetingSystem");
        this.world = world;
    }

    @Override
    public void activate(TargetingSystem targetingSystem) {
        addSubGoalToEnd(new Scan(world));
        addSubGoalToEnd(new Track());
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
