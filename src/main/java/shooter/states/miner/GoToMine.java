package shooter.states.miner;

import shooter.states.State;
import shooter.unit.Miner;

public class GoToMine implements State<Miner> {

    private final WorkInMine nextState;

    public GoToMine(WorkInMine nextState) {
        this.nextState = nextState;
    }

    public void enter(Miner miner) {
        miner.moveToClosestMine();
    }

    public State<Miner> execute(Miner miner) {
        return miner.atMine() ? nextState : this;
    }

    public void terminate(Miner entity) {
        entity.steering().arriveOff();
        entity.stop();
    }

}
