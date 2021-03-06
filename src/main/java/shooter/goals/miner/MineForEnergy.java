package shooter.goals.miner;

import static shooter.goals.GoalState.ACTIVE;
import static shooter.goals.GoalState.COMPLETED;

import shooter.goals.CompositeGoal;
import shooter.goals.GoalState;
import shooter.unit.Miner;
import shooter.world.ShooterWorld;

public class MineForEnergy extends CompositeGoal<Miner> {

    private final ShooterWorld world;

    public MineForEnergy(ShooterWorld world) {
        super("Mining");
        this.world = world;
    }

    @Override
    public void activate(Miner miner) {
        if (!miner.assignedToMine() && miner.isEmpty()) {
            findAMineToWork();
        } else if (miner.assignedToMine() && miner.atMine() && miner.isEmpty()) {
            startMining();
        } else if (miner.assignedToMine() && (!miner.isEmpty() || miner.nothingToMine())) {
            returnToBase();
        } else {
            unload();
        }
        super.activate(miner);
    }

    private void unload() {
        clearSubGoals();
        addSubGoalToFront(new Unload(world));
    }

    @Override
    public GoalState process(Miner miner) {
        GoalState goalState = super.process(miner);
        if (goalState == COMPLETED) {
            activate(miner);
            return ACTIVE;
        } else {
            return goalState;
        }
    }

    private void returnToBase() {
        clearSubGoals();
        addSubGoalToFront(new ReturnToBase(world));
    }

    private void startMining() {
        clearSubGoals();
        addSubGoalToFront(new WorkInMine());
    }

    private void findAMineToWork() {
        clearSubGoals();
        addSubGoalToFront(new GoToMine(world));
    }

    @Override
    public void terminate(Miner miner) {
    }

}
