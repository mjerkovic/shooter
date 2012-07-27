package shooter.goals;

import shooter.unit.MovingEntity;

public class Scouting extends CompositeGoal<MovingEntity> {

    @Override
    public void activate(MovingEntity entity) {
        super.activate(entity);
        addSubGoalToFront(new Roam());
    }

    @Override
    public void terminate(MovingEntity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
