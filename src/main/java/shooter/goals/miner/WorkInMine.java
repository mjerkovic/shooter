package shooter.goals.miner;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.Miner;
import shooter.unit.structure.Mine;

import static shooter.goals.GoalState.ACTIVE;
import static shooter.goals.GoalState.COMPLETED;

public class WorkInMine extends SimpleGoal<Miner> {

    private final Mine mine;

    public WorkInMine(Mine mine) {
        this.mine = mine;
    }

    @Override
    protected void doActivation(Miner miner) {
        miner.arrivedAt(mine);
    }

    @Override
    protected GoalState doProcess(Miner miner) {
        miner.work();
        return miner.finishedWork() ? COMPLETED : ACTIVE;
    }

    @Override
    public void terminate(Miner miner) {
        miner.leaveMine();
    }

}
