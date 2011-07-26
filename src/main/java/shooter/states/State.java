package shooter.states;

public interface State<T> {

    void enter(T entity);

    void execute(T entity);

    void terminate(T entity);

}
