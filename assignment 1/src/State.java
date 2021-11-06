import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class State {
    int encoding ;
    int depth ;
    State parent ;

    public State(int encoding, int depth, State parent) {
        this.encoding = encoding;
        this.depth = depth;
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        return this.encoding;
    }

    @Override
    public boolean equals(Object obj) {
        if ( obj == this )
            return true ;

        else if ( obj.getClass() == State.class ){
            State temp = (State) obj ;
            return temp.encoding == this.encoding ;
        }
        return false ;
    }

}
