package shooter.states;

import shooter.unit.WatchTower;

public class WatchTowerStateMachine extends StateMachine<WatchTower> {

    public WatchTowerStateMachine(WatchTower entity) {
        super(entity);
        currentState = new Scan(this);
    }

}
