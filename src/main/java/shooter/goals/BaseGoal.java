package shooter.goals;

public abstract class BaseGoal<T> implements Goal<T> {

    private boolean active = false;

    public GoalState process(T entity) {
        if (!active) {
            activate(entity);
        }
        return processGoal(entity);
    }

    public void addSubGoal(Goal<T> tGoal) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean activated() {
        return active;
    }

    public void activate(T entity) {
        activateGoal(entity);
        active = true;
    }

    protected abstract void activateGoal(T entity);

    protected abstract GoalState processGoal(T entity);

}
