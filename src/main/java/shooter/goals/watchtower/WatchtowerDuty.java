package shooter.goals.watchtower;

import shooter.goals.CompositeGoal;
import shooter.goals.GoalState;
import shooter.unit.WatchTower;
import shooter.unit.Weapon;

import static shooter.goals.GoalState.ACTIVE;
import static shooter.goals.GoalState.COMPLETED;

public class WatchtowerDuty extends CompositeGoal<Weapon> {

    @Override
    public void activate(Weapon weapon) {
        addSubGoal(new Track());
        addSubGoal(new Scan());
        super.activate(weapon);
    }

    @Override
    public GoalState process(Weapon weapon) {
        GoalState goalState = super.process(weapon);
        if (goalState == COMPLETED) {
            activate(weapon);
            return ACTIVE;
        } else {
            return goalState;
        }
    }

    @Override
    public void terminate(Weapon weapon) {
        activate(weapon);
    }

}
