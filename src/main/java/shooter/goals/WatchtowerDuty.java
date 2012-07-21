package shooter.goals;

import shooter.unit.WatchTower;

import static shooter.goals.GoalState.ACTIVE;
import static shooter.goals.GoalState.COMPLETED;

public class WatchtowerDuty extends CompositeGoal<WatchTower> {

    @Override
    public void activate(WatchTower tower) {
        addSubGoal(new Track());
        addSubGoal(new Scan());
        super.activate(tower);
    }

    @Override
    public GoalState process(WatchTower tower) {
        GoalState goalState = super.process(tower);
        if (goalState == COMPLETED) {
            activate(tower);
            return ACTIVE;
        } else {
            return goalState;
        }
    }

    @Override
    public void terminate(WatchTower tower) {
        activate(tower);
    }

}
