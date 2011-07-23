package shooter.goals;

import shooter.unit.MovingEntity;

public abstract class BaseGoal implements Goal {

    private boolean active = false;

    public void process(MovingEntity entity) {
        if (!active) {
            activate(entity);
        }
        processGoal(entity);
    }

    public boolean activated() {
        return active;
    }

    private void activate(MovingEntity entity) {
        activateGoal(entity);
        active = true;
    }

    protected abstract void activateGoal(MovingEntity entity);

    protected abstract void processGoal(MovingEntity entity);

}
