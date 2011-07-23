package shooter.steering;

import org.testng.annotations.Test;
import shooter.geom.Vector;
import shooter.unit.Vehicle;

public class ArriveTest {

    @Test
    public void testCalculate() {
        Vehicle vehicle = new Vehicle(0, 0, null, null);
        Arrive arrive = new Arrive(vehicle, new Vector(100, 100));
/*
        Vector result;
        do{
            result = arrive.calculate();
            vehicle.velocity = vehicle.velocity().add(result);
            vehicle.position = vehicle.position.add(vehicle.velocity);
            System.out.println("arrive.calculate() = " + result);
        } while (vehicle.position.X() > 0 && vehicle.position.Y() > 0);
*/
    }
}
