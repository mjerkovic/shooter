package shooter.goals.miner;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.Miner;
import shooter.unit.structure.Mine;
import shooter.world.ShooterWorld;

import static shooter.goals.GoalState.ACTIVE;
import static shooter.goals.GoalState.COMPLETED;

public class GoToMine extends SimpleGoal<Miner> {

    private final Mine mine;

    public GoToMine(Mine mine) {
        this.mine = mine;
    }

    @Override
    protected void doActivation(Miner miner) {
        miner.goToMine(mine);
    }

    @Override
    protected GoalState doProcess(Miner miner) {
        return miner.intersects(mine) ? COMPLETED : ACTIVE;
    }

    @Override
    public void terminate(Miner miner) {
        miner.arrivedAt(mine);
    }

}
