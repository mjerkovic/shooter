package shooter.goals.miner;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.Miner;

import static shooter.goals.GoalState.ACTIVE;
import static shooter.goals.GoalState.COMPLETED;

public class GoToMine extends SimpleGoal<Miner> {

    @Override
    protected void doActivation(Miner miner) {
        miner.moveToClosestMine();
    }

    @Override
    protected GoalState doProcess(Miner miner) {
        return miner.atMine() ? COMPLETED : ACTIVE;
    }

    @Override
    public void terminate(Miner miner) {
        miner.steering().arriveOff();
        miner.stop();
    }

}
