package shooter.world;

import com.google.common.collect.Lists;
import shooter.comms.MessageDispatcher;
import shooter.comms.MessageListener;
import shooter.geom.Vector;
import shooter.goals.Scouting;
import shooter.goals.UserControl;
import shooter.goals.miner.MineForEnergy;
import shooter.steering.Direction;
import shooter.steering.Steering;
import shooter.ui.GameRenderer;
import shooter.unit.*;
import shooter.unit.structure.BaseCamp;
import shooter.unit.structure.Mine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ShooterWorld implements GameWorld {

    private Vehicle vehicle;
    private Signpost signpost;
    private WatchTower watchTower;
    private List<Bullet> bullets = new ArrayList<Bullet>();
    private final Collection<Entity> entities;
    private final Collection<Obstacle> obstacles;
    private final Collection<Wall> displayableWalls = newArrayList();
    private final Collection<Wall> allWalls = newArrayList();
    private final Collection<Vehicle> vehicles;
    private final Collection<Miner> miners;
    private final Collection<Mine> mines;
    private final BaseCamp baseCamp;
    private final Miner miner;
    private final Mine mine;
    private final Vector worldArea;

    public ShooterWorld(Vector worldArea, MessageListener messageListener) {
        this.worldArea = worldArea;
        MessageDispatcher radio = new MessageDispatcher(Lists.<MessageListener>newArrayList(messageListener));
        vehicle = new Vehicle(new Vector(100, 100), 10, new Vector(1, 0), 0.1, radio, new UserControl(), new Steering(this));
        vehicle.steering().obstacleAvoidanceOn();
        //Vehicle wanderer = new Vehicle(army, new Vector(300, 300), new Vector(1, 0), 0.3, new Roam(), new Steering(this));
        vehicles = newArrayList(vehicle); //, wanderer);
        signpost = new Signpost(new Vector(10, 250), 10, radio, "Sign");
        watchTower = new WatchTower(new Vector(300, 500), 10, radio, this, new Steering(this));
        obstacles = newArrayList();
        addObstacles();
        addVehicles();
        addWalls();
        miner = (new Miner(new Vector(20, 50), 10, new Vector(1, 0), 0.1, radio, new MineForEnergy(this),
                  new Steering(this), 100));
        miners = newArrayList(miner);
        mine = new Mine(new Vector(450, 60), 50, radio, 3000);
        mines = newArrayList(mine);
        baseCamp = new BaseCamp(new Vector(30, 30), 15, radio);
        entities = Lists.<Entity>newArrayList(vehicles);
        entities.add(signpost);
        entities.add(watchTower);
        entities.add(miner);
        entities.add(mine);
        Scout scout = new Scout(new Vector(Math.random() * worldArea.x(), Math.random() * worldArea.y()),
                10, new Vector(0, -1), 0.1, radio, new Steering(this));
        scout.steering().obstacleAvoidanceOn();
        vehicles.add(scout);
        entities.add(scout);

    }

    private void addVehicles() {
/*
        Random rand = new Random();
        double headingX = rand.nextDouble() - rand.nextDouble();
        double headingY = rand.nextDouble() - rand.nextDouble();
        for (int i=0; i < 1; i++) {
            Vehicle v = new Vehicle(new Vector(rand.nextInt(600), rand.nextInt(600)), new Vector(headingX, headingY), 0.3, new Roam(), new Steering(this));
            v.steering().obstacleAvoidanceOn();
            vehicles.add(v);
        }
*/
    }

    private void addObstacles() {
/*
        Random rand = new Random();
        for (int i=0; i < 5; i++) {
            obstacles.add(new Obstacle(rand.nextInt(600), rand.nextInt(600), rand.nextDouble() * 50));
        }
*/
    }

    private void addWalls() {
        double maxX = worldArea.x();
        double maxY = worldArea.y();
        allWalls.add(new Wall(new Vector(0, 0), new Vector(maxX, 0)));
        allWalls.add(new Wall(new Vector(maxX, 0), new Vector(maxX, maxY)));
        allWalls.add(new Wall(new Vector(maxX, maxY), new Vector(0, maxY)));
        allWalls.add(new Wall(new Vector(0, maxY), new Vector(0, 0)));
    }

    public void update() {
        for (Vehicle vehicle : vehicles) {
            vehicle.update();
        }
        for (Miner miner : miners) {
            miner.update();
        }
        watchTower.update();
        for (Bullet bullet : bullets) {
            bullet.update();
        }
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            if (bulletIterator.next().outOfRange()) {
                bulletIterator.remove();
            }
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
        baseCamp.renderWith(renderer);
        renderer.render(signpost);
        renderer.render(watchTower);
        for (Bullet bullet : bullets) {
            renderer.render(bullet);
        }
        for (Obstacle obstacle : obstacles) {
            renderer.render(obstacle);
        }
        for (Wall wall : displayableWalls) {
            renderer.render(wall);
        }
    }

    public void moveVehicle(Direction direction) {
        vehicle.steering().directionOn(direction);
    }

    public void moveTo(Vector position) {
        vehicle.steering().directionOff();
        vehicle.stop();
        vehicle.steering().arriveOn(position);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }

    public Collection<Entity> getEntities() {
        return entities;
    }

    public void shotFired(MovingEntity shooter, MovingEntity target) {
        addBullet(new Bullet(shooter, shooter.heading(), target));
    }

    private void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public Collection<Obstacle> getObstacles() {
        return obstacles;
    }

    public Collection<Wall> getWalls() {
        return allWalls;
    }

    public Mine getClosestMine() {
        return mines.iterator().next();
    }

    public BaseCamp getBase() {
        return baseCamp;
    }

    public Vector getWorldArea() {
        return worldArea;
    }

}
