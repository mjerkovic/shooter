package shooter.goals.miner;

import shooter.goals.CompositeGoal;
import shooter.goals.GoalState;
import shooter.unit.Miner;

import static shooter.goals.GoalState.ACTIVE;

public class MineForEnergy extends CompositeGoal<Miner> {

    @Override
    public void activate(Miner miner) {
        addSubGoal(new WorkInMine());
        addSubGoal(new GoToMine());
        super.activate(miner);
    }

    @Override
    public void terminate(Miner miner) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
