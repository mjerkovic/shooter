package shooter.goals.miner;

import static shooter.goals.GoalState.ACTIVE;
import static shooter.goals.GoalState.COMPLETED;

import shooter.goals.GoalState;
import shooter.goals.SimpleGoal;
import shooter.unit.Miner;
import shooter.world.ShooterWorld;

public class GoToMine extends SimpleGoal<Miner> {

    private final ShooterWorld world;

    public GoToMine(ShooterWorld world) {
        this.world = world;
    }

    @Override
    protected void doActivation(Miner miner) {
        miner.goToMine(world.getClosestMine());
    }

    @Override
    protected GoalState doProcess(Miner miner) {
        return miner.atMine() ? COMPLETED : ACTIVE;
    }

    @Override
    protected void doTermination(Miner miner) {
        miner.arrivedAtMine();
    }

    @Override
    public String description() {
        return "Going to mine";
    }
    
}
