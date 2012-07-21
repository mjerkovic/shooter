package shooter.goals.miner;

import shooter.goals.CompositeGoal;
import shooter.unit.Miner;
import shooter.unit.structure.Mine;
import shooter.world.ShooterWorld;

public class MineForEnergy extends CompositeGoal<Miner> {

    private final ShooterWorld world;
    private Mine mine;

    public MineForEnergy(ShooterWorld world) {
        this.world = world;
    }

    @Override
    public void activate(Miner miner) {
        if (mine == null && miner.isEmpty()) {
            findAMineToWork();
        }
        super.activate(miner);
    }

    private void findAMineToWork() {
        clearSubGoals();
        mine = world.getClosestMine();
        addSubGoal(new WorkInMine(mine));
        addSubGoal(new GoToMine(mine));
    }

    @Override
    public void terminate(Miner miner) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
