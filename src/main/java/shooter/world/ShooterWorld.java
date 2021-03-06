package shooter.world;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;
import shooter.comms.EventListener;
import shooter.comms.MessageDispatcher;
import shooter.geom.Vector;
import shooter.goals.Roam;
import shooter.goals.Think;
import shooter.goals.UserControl;
import shooter.goals.miner.MineForEnergy;
import shooter.goals.watchtower.Tracking;
import shooter.steering.Direction;
import shooter.steering.Steering;
import shooter.ui.Renderer;
import shooter.unit.Bullet;
import shooter.unit.Cannon;
import shooter.unit.Entity;
import shooter.unit.Miner;
import shooter.unit.Movement;
import shooter.unit.Obstacle;
import shooter.unit.Orientation;
import shooter.unit.Scout;
import shooter.unit.Signpost;
import shooter.unit.TargetingSystem;
import shooter.unit.Vehicle;
import shooter.unit.Wall;
import shooter.unit.WatchTower;
import shooter.unit.structure.BaseCamp;
import shooter.unit.structure.Mine;

public class ShooterWorld implements GameWorld, EventListener {

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

    public ShooterWorld(Vector worldArea) {
        this.worldArea = worldArea;
        MessageDispatcher.addEventListener(this);
        vehicle = new Vehicle(new Orientation(new Vector(100, 100), new Vector(1, 0), 10), new UserControl(),
                new Movement(new Steering(this), 0.1));
        vehicle.steering().obstacleAvoidanceOn();
        vehicles = newArrayList(vehicle); //, wanderer);
        signpost = new Signpost(new Orientation(new Vector(10, 250), 10), "Sign");
        watchTower = new WatchTower(new Orientation(new Vector(300, 500), new Vector(0, -1), 5));
        obstacles = newArrayList();
        addObstacles();
        addVehicles();
        addWalls();
        miner = (new Miner(new Orientation(new Vector(20, 50), new Vector(1, 0), 10, 50),
                new Think<Miner>(new MineForEnergy(this)), new Movement(new Steering(this), 0.1, 2.0), 1));
        miners = newArrayList(miner);
        mine = new Mine(new Orientation(new Vector(450, 60), 50), 3000);
        mines = newArrayList(mine);
        baseCamp = new BaseCamp(new Orientation(new Vector(30, 30), 15));
        entities = Lists.<Entity>newArrayList(vehicles);
        entities.add(signpost);
        entities.add(watchTower);
        entities.add(miner);
        entities.add(mine);
        Vector scoutPos = new Vector(Math.random() * worldArea.x(), Math.random() * worldArea.y());
        Scout scout = new Scout(new Orientation(scoutPos, new Vector(0, -1), 10), new Roam(),
                new Movement(new Steering(this), 0.1));
        scout.steering().obstacleAvoidanceOn();
        scout.steering().wallAvoidanceOn();
        vehicles.add(scout);
        entities.add(scout);
        TargetingSystem scoutWeapon = new TargetingSystem(scout, 5, new Think<TargetingSystem>(new Tracking(this)),
                new Movement(new Steering(this), 0.1), 40000, new Cannon(20000, 1000));
        entities.add(scoutWeapon);
        TargetingSystem watchtowerWeapon = new TargetingSystem(watchTower, 5,
                new Think<TargetingSystem>(new Tracking(this)), new Movement(new Steering(this), 0.1), 40000,
                new Cannon(20000, 1000));
        entities.add(watchtowerWeapon);
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
        for (Entity entity : entities) {
            entity.update();
        }
/*
        for (Miner miner : miners) {
            miner.update();
        }
        watchTower.update();
*/
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

    public void renderWith(Renderer renderer) {
        for (Entity entity : entities) {
            entity.renderWith(renderer);
        }
/*
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
*/
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

    public void shotFired(Entity shooter, Entity target, Vector heading) {
        addBullet(new Bullet(shooter, target, heading));
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

    @Override
    public void shotFired(Entity shooter, Vector heading, Entity target) {
        addBullet(new Bullet(shooter, target, heading));
    }

}
