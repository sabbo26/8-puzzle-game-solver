import java.util.LinkedList;
import java.util.Queue;

public class BFSFringe implements Fringe{

    private Queue<State> qu ;

    public BFSFringe() {
        this.qu = new LinkedList<>() ;
    }
    @Override
    public void add(State s) {
        qu.add(s);
    }
    @Override
    public State get() {
        return qu.poll()  ;
    }

    @Override
    public boolean isEmpty(){
        return qu.isEmpty();
    }
}
