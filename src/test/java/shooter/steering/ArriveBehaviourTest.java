package shooter.steering;

import javax.xml.transform.Result;

import org.testng.annotations.Test;
import shooter.geom.Vector;
import shooter.unit.Vehicle;

public class ArriveBehaviourTest {

    @Test
    public void testCalculate() {
        Vehicle vehicle = new Vehicle(null);
        ArriveBehaviour arrive = new ArriveBehaviour(vehicle, new Vector(100, 100));
        Vector result;
        do{
            result = arrive.calculate();
            vehicle.velocity = vehicle.getVelocity().add(result);
            vehicle.position = vehicle.position.add(vehicle.velocity);
            System.out.println("arrive.calculate() = " + result);
        } while (vehicle.position.X() > 0 && vehicle.position.Y() > 0);
    }
}
