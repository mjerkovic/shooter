package shooter.states;

public class StateMachine<T> {

    private final State<T> initialState;
    protected State<T> currentState;

    public StateMachine(State<T> initialState) {
        this.initialState = initialState;
    }

    public void update(T entity) {
        if (currentState == null) {
            currentState = initialState;
            currentState.enter(entity);
        }
        State<T> nextState = currentState.execute(entity);
        if (nextState != currentState) {
            changeStateTo(entity, nextState);
        }
    }

    public void changeStateTo(T entity, State<T> nextState) {
        currentState.terminate(entity);
        currentState = nextState;
        nextState.enter(entity);
    }

}
