package shooter.goals.watchtower;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.WatchTower;

public class Scan extends SimpleGoal<WatchTower> {

    @Override
    protected void doActivation(WatchTower tower) {
    }

    @Override
    protected GoalState doProcess(WatchTower tower) {
         return (tower.targetAcquired() || tower.acquireTarget()) ? GoalState.COMPLETED : GoalState.ACTIVE;
    }


    public void terminate(WatchTower entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
