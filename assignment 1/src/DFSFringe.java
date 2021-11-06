import java.util.Stack;

public class DFSFringe implements Fringe{

    private Stack<State> stk ;

    public DFSFringe() {
        stk = new Stack<>();
    }

    @Override
    public void add(State s) {
        stk.push(s);
    }

    @Override
    public State get() {
        return stk.pop() ;
    }

    @Override
    public boolean isEmpty() {
        return stk.isEmpty() ;
    }
}
