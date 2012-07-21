package shooter.goals;

import static shooter.goals.GoalState.ACTIVE;

public abstract class SimpleGoal<T> implements Goal<T> {

    protected GoalState state = GoalState.INACTIVE;

    public void addSubGoal(Goal<T> tGoal) {
        throw new UnsupportedOperationException("A SimpleGoal cannot have sub-goals");
    }

    public void activate(T entity) {
        if (state.equals(GoalState.INACTIVE)) {
            doActivation(entity);
            state = ACTIVE;
        }
    }

    @Override
    public GoalState process(T entity) {
        activate(entity);
        return doProcess(entity);
    }

    protected abstract void doActivation(T entity);

    protected abstract GoalState doProcess(T entity);

}
