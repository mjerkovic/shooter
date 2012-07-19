package shooter.unit;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;

import shooter.ui.GameRenderer;
import shooter.unit.structure.Mine;

public class Army {

    private final Collection<Vehicle> vehicles = newArrayList();
    private final Collection<Miner> miners = newArrayList();
    private final Collection<Mine> mines = newArrayList();

    public void add(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void add(Miner miner) {
        miners.add(miner);
    }

    public void add(Mine mine) {
        mines.add(mine);
    }

    public void update() {
        for (Vehicle vehicle : vehicles) {
            vehicle.update();
        }
        for (Miner miner : miners) {
            miner.update();
        }
    }

    public void renderWith(GameRenderer renderer) {
        for (Mine mine : mines) {
            mine.renderWith(renderer);
        }
        for (Miner miner : miners) {
            miner.renderWith(renderer);
        }
        for (Vehicle vehicle : vehicles) {
            vehicle.renderWith(renderer);
        }
    }

    public Mine getClosestMine() {
        return mines.iterator().next();
    }

}
