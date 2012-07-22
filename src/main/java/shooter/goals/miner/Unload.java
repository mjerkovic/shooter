package shooter.goals.miner;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.Miner;
import shooter.world.ShooterWorld;

import static shooter.goals.GoalState.ACTIVE;

public class Unload extends SimpleGoal<Miner> {

    private final ShooterWorld world;

    public Unload(ShooterWorld world) {
        this.world = world;
    }

    @Override
    protected void doActivation(Miner miner) {
        world.getBase().startUnloading(miner);
    }

    @Override
    protected GoalState doProcess(Miner miner) {
        world.getBase().unload(miner);
        return miner.isEmpty() ? GoalState.COMPLETED : ACTIVE;
    }

    @Override
    public void terminate(Miner miner) {
        world.getBase().unloadingCompleted(miner);
    }

}
