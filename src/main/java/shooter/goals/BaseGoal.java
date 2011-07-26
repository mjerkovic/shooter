package shooter.goals;

public abstract class BaseGoal<T> implements Goal<T> {

    private boolean active = false;

    public void process(T entity) {
        if (!active) {
            activate(entity);
        }
        processGoal(entity);
    }

    public void addGoal(Goal<T> tGoal) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean activated() {
        return active;
    }

    private void activate(T entity) {
        activateGoal(entity);
        active = true;
    }

    protected abstract void activateGoal(T entity);

    protected abstract void processGoal(T entity);

}
