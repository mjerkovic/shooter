package shooter.goals;

import static shooter.goals.GoalState.ACTIVE;
import static shooter.goals.GoalState.INACTIVE;

public abstract class SimpleGoal<T> implements Goal<T> {

    protected GoalState state = INACTIVE;

    public void activate(T entity) {
        if (state.equals(INACTIVE)) {
            doActivation(entity);
            state = ACTIVE;
        }
    }

    @Override
    public GoalState process(T entity) {
        activate(entity);
        state = doProcess(entity);
        return state;
    }

    @Override
    public void terminate(T entity) {
        doTermination(entity);
        if (state == ACTIVE) {
            state = INACTIVE;
        }
    }

    public void addSubGoalToFront(Goal<T> tGoal) {
        throw new UnsupportedOperationException("A SimpleGoal cannot have sub-goals");
    }

    @Override
    public void addSubGoalToEnd(Goal<T> tGoal) {
        throw new UnsupportedOperationException("A SimpleGoal cannot have sub-goals");
    }

    protected abstract void doActivation(T entity);

    protected abstract GoalState doProcess(T entity);

    protected abstract void doTermination(T entity);

}
