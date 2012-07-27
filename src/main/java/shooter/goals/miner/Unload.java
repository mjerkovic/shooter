package shooter.goals.miner;

import static shooter.goals.GoalState.ACTIVE;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.Miner;
import shooter.world.ShooterWorld;

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
    protected void doTermination(Miner miner) {
        world.getBase().unloadingCompleted(miner);
    }

    @Override
    public String description() {
        return "Unloading";
    }
}
