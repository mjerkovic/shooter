package shooter.states.miner;

import shooter.states.State;
import shooter.unit.Miner;

public class Collect implements State<Miner> {
    
    public void enter(Miner miner) {
    }

    public State<Miner> execute(Miner miner) {
        if (!miner.work() || miner.isFull()) {
            // go to next state
        }
        return null;
    }

    public void terminate(Miner miner) {
        miner.leaveMine();
    }

}
