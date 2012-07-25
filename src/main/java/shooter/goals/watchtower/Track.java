package shooter.goals.watchtower;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.WatchTower;
import shooter.unit.Weapon;

import static shooter.goals.GoalState.COMPLETED;

public class Track extends SimpleGoal<Weapon> {

    @Override
    protected void doActivation(Weapon weapon) {
        weapon.startTracking();
    }

    @Override
    protected GoalState doProcess(Weapon weapon) {
        weapon.fire();
        return weapon.targetInRange() ? GoalState.ACTIVE : COMPLETED;
    }

    public void terminate(Weapon weapon) {
        weapon.stopTracking();
    }

}
