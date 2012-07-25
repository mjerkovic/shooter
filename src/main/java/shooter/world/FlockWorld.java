package shooter.world;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.Random;

import shooter.geom.Vector;
import shooter.goals.Roam;
import shooter.steering.Direction;
import shooter.steering.Steering;
import shooter.ui.Renderer;
import shooter.unit.Obstacle;
import shooter.unit.Vehicle;
import shooter.unit.Wall;

public class FlockWorld implements GameWorld {

    private final Collection<Wall> walls = newArrayList();
    private final Collection<Vehicle> vehicles = newArrayList();
    private final Collection<Obstacle> obstacles = newArrayList();

    public FlockWorld() {
        addWalls();
        addVehicles();
    }

    public void update() {
        for (Vehicle vehicle : vehicles) {
            vehicle.update();
        }
    }

    public void renderWith(Renderer renderer) {
        for (Wall wall : walls) {
            renderer.render(wall);
        }
        for (Vehicle vehicle : vehicles) {
            renderer.render(vehicle);
        }
    }

    public void moveVehicle(Direction direction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void moveTo(Vector position) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Collection<Obstacle> getObstacles() {
        return obstacles;
    }

    public Collection<Wall> getWalls() {
        return walls;
    }

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }

    private void addWalls() {
        walls.add(new Wall(new Vector(0, 0), new Vector(600, 0)));
        walls.add(new Wall(new Vector(600, 0), new Vector(600, 600)));
        walls.add(new Wall(new Vector(600, 600), new Vector(0, 600)));
        walls.add(new Wall(new Vector(0, 600), new Vector(0, 0)));
    }

    private void addVehicles() {
        Random rand = new Random();
        for (int i=0; i < 10; i++) {
            double headingX = rand.nextDouble() - rand.nextDouble();
            double headingY = rand.nextDouble() - rand.nextDouble();
            Vehicle v = new Vehicle(new Vector(rand.nextInt(600), rand.nextInt(600)), 10,
                    new Vector(headingX, headingY), 0.3, null, new Roam(), new Steering(this));
            v.steering().separationOn();
            v.steering().cohesionOn();
            v.steering().alignmentOn();
            v.steering().wallAvoidanceOn();
            vehicles.add(v);
        }
    }

}
