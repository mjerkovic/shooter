package shooter.goals;

import shooter.unit.WatchTower;

public class Scan extends CompositeGoal<WatchTower> {

    public void activate(WatchTower tower) {
        if (!tower.targetAcquired() && tower.acquireTarget()) {
            addSubGoal(new Track());
        }
    }

    public void terminate(WatchTower entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
