package shooter.goals.miner;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.Miner;

import static shooter.goals.GoalState.ACTIVE;
import static shooter.goals.GoalState.COMPLETED;

public class WorkInMine extends SimpleGoal<Miner> {

    @Override
    protected void doActivation(Miner miner) {
    }

    @Override
    protected GoalState doProcess(Miner miner) {
        miner.work();
        return miner.finishedWork() ? COMPLETED : ACTIVE;
    }

    @Override
    public void terminate(Miner miner) {
    }

}