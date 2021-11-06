import java.util.Comparator;
import java.util.PriorityQueue;

public class EuclideanFringe implements Fringe {

    class Wrapper{

        State x ;

        double cost ;

        public Wrapper(State x, double cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    PriorityQueue<Wrapper> qu ;

    public EuclideanFringe() {
        qu = new PriorityQueue<>(new Comparator<Wrapper>() {
            @Override
            public int compare(Wrapper o1, Wrapper o2) {
                if ( o1.cost > o2.cost )
                    return 1 ;
                else if ( o1.cost < o2.cost )
                    return -1 ;
                return 0 ;
            }
        });
    }

    @Override
    public void add(State s) {
        qu.add( new Wrapper( s , s.depth + euclidean_distance(s) ) );
    }

    @Override
    public State get() {
        return qu.poll().x ;
    }

    @Override
    public boolean isEmpty() {
        return qu.isEmpty();
    }

    private double euclidean_distance ( State x ){

        double cost = 0 ;

        int encoding = x.encoding ;

        for (int i = 0 ; i < 9 ; i++ ){
            int w = encoding / 10 ;
            int goal_x = w / 3 ;
            int goal_y = w % 3 ;
            int current_x = (8 - i ) / 3 ;
            int current_y = (8-i) % 3 ;
            cost += Math.sqrt( Math.pow( current_x - goal_x , 2 ) + Math.pow( current_y - goal_y  , 2 ) );
        }

        return cost ;
    }

}
