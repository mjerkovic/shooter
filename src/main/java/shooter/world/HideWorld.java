package shooter.world;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;

import shooter.geom.Vector;
import shooter.steering.Direction;
import shooter.ui.Renderer;
import shooter.unit.Obstacle;
import shooter.unit.Vehicle;
import shooter.unit.Wall;

public class HideWorld implements GameWorld {

    private Vehicle hunter;
    private Vehicle prey;
    private final Collection<Obstacle> obstacles = newArrayList();
    private final Collection<Wall> walls = newArrayList();

    public HideWorld() {
        addObstacles();
        addWalls();
/*
        hunter = new Vehicle(new Vector(500, 500), new Vector(0, -1), 10, 0.3, null, new Roam(), new Steering(this));
        hunter.steering().wallAvoidanceOn();
        hunter.steering().obstacleAvoidanceOn();
        hunter.steering().wanderOn();
        prey = new Vehicle(new Vector(100, 300), new Vector(-1, 0), 10, 0.3, null, new UserControl(), new Steering(this));
        prey.steering().wallAvoidanceOn();
        hunter.steering().obstacleAvoidanceOn();
        prey.steering().hideOn(hunter);
*/
    }

    public void update() {
        prey.update();
        hunter.update();
    }

    public void renderWith(Renderer renderer) {
        for (Wall wall : walls) {
            renderer.render(wall);
        }
        for (Obstacle obstacle : obstacles) {
            renderer.render(obstacle);
        }
        renderer.render(prey);
        renderer.render(hunter);
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void addObstacles() {
/*
        obstacles.add(new Obstacle(new Vector(200, 200), null, 50));
        obstacles.add(new Obstacle(new Vector(400, 400), null, 50));
*/
    }

    private void addWalls() {
        walls.add(new Wall(new Vector(0, 0), new Vector(600, 0)));
        walls.add(new Wall(new Vector(600, 0), new Vector(600, 600)));
        walls.add(new Wall(new Vector(600, 600), new Vector(0, 600)));
        walls.add(new Wall(new Vector(0, 600), new Vector(0, 0)));
    }

}
