package shooter.goals.watchtower;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.WatchTower;
import shooter.unit.Weapon;

public class Scan extends SimpleGoal<Weapon> {

    @Override
    protected void doActivation(Weapon weapon) {
    }

    @Override
    protected GoalState doProcess(Weapon weapon) {
         return (weapon.targetAcquired() || weapon.acquireTarget()) ? GoalState.COMPLETED : GoalState.ACTIVE;
    }


    public void terminate(Weapon weapon) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
