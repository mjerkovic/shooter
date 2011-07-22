package shooter.world;

import org.testng.collections.Lists;
import shooter.geom.Vector;
import shooter.steering.SteeringBehaviour;
import shooter.steering.SteeringBehaviours;
import shooter.ui.GameRenderer;
import shooter.unit.Vehicle;

public class ShooterWorld implements GameWorld {

    private int x =  0;
    private int y = 0;

    private Vehicle vehicle = new Vehicle(new SteeringBehaviours(Lists.<SteeringBehaviour>newArrayList()));

    public void update(double timeDiff) {
        vehicle.update(timeDiff);
    }

    public void moveVehicleTo(Vector position) {
        vehicle.move(position);
    }

    public void renderVehiclesWith(GameRenderer renderer) {
        renderer.render(vehicle);
    }

}
