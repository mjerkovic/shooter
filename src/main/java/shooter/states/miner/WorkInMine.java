package shooter.states.miner;

import shooter.states.State;
import shooter.unit.Miner;

public class WorkInMine implements State<Miner> {
    
    public void enter(Miner miner) {
    }

    public State<Miner> execute(Miner miner) {
        miner.work();
        if (miner.finishedWork()) {
            // go to next state
        }
        return this;
    }

    public void terminate(Miner miner) {
        miner.leaveMine();
    }

}
