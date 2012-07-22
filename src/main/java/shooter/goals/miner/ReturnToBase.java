package shooter.goals.miner;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.Miner;
import shooter.world.ShooterWorld;

import static shooter.goals.GoalState.ACTIVE;
import static shooter.goals.GoalState.COMPLETED;

public class ReturnToBase extends SimpleGoal<Miner> {

    private final ShooterWorld world;

    public ReturnToBase(ShooterWorld world) {
        this.world = world;
    }

    @Override
    protected void doActivation(Miner miner) {
        miner.leaveMine();
        miner.returnToBase(world.getBase().position());
    }

    @Override
    protected GoalState doProcess(Miner miner) {
        return miner.intersects(world.getBase()) ? COMPLETED : ACTIVE;
    }

    @Override
    public void terminate(Miner miner) {
    }

}
