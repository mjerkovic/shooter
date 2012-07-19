package shooter.states.miner;

import shooter.states.State;
import shooter.unit.Miner;

public class GoToMine implements State<Miner> {

    private final Collect nextState;

    public GoToMine(Collect nextState) {
        this.nextState = nextState;
    }

    public void enter(Miner miner) {
        miner.moveToClosestMine();
    }

    public State<Miner> execute(Miner miner) {
        return miner.atMine() ? nextState : null;
    }

    public void terminate(Miner entity) {
        entity.steering().arriveOff();
        entity.stop();
    }

}
