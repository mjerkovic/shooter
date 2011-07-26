package shooter.states;

public abstract class StateMachine<T> {

    private final T entity;
    protected State<T> currentState;

    public StateMachine(T entity) {
        this.entity = entity;
    }

    public void update() {
        currentState.execute(entity);
    }

    public void changeStateTo(State<T> state) {
        currentState.terminate(entity);
        currentState = state;
        state.enter(entity);
    }

}
