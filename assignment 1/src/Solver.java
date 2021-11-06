import java.util.*;

public class Solver {

    private HashSet<State> explored ;

    private int[][] board ;

    private int[][] possible_moves ;

    public Solver(){
        explored = new HashSet<>();
        board = new int[3][3];
        possible_moves = new int[][]{ { 0,0,-1,1  } , {-1,1,0,0}  };
    }


    public void solve_bfs ( int start_state ){
        Fringe fringe = new BFSFringe();
        fringe.add( new State( start_state , 0 , null) );
        solve(fringe);
    }

    public void solve_dfs(int start_state) {
        Fringe fringe = new DFSFringe();
        fringe.add( new State( start_state , 0 , null) );
        solve(fringe);
    }

    public void solve_manhattan(int start_state){
        Fringe fringe = new ManhattanFringe();
        fringe.add( new State( start_state , 0 , null) );
        solve(fringe);
    }

    public void solve_euclidean ( int start_state ){
        Fringe fringe = new EuclideanFringe();
        fringe.add( new State( start_state , 0 , null) );
        solve(fringe);
    }

    private void solve ( Fringe fringe ){

        long time = System.nanoTime();

        while ( ! fringe.isEmpty() ){

            State current_state = fringe.get() ;

            if ( ! explored.contains(current_state) ){

                explored.add(current_state);

                if ( goal_test( current_state.encoding ) ){
                    System.out.println("running time is " + ( System.nanoTime() - time ) / 1000 + " microseconds" );
                    print_sol(current_state);
                    explored = new HashSet<>();
                    return;
                }

                int[] zero_location = num_to_arr(current_state.encoding);

                int x = zero_location[0] , y = zero_location[1];

                for (int i = 0 ; i < 4 ; i++ ){

                    int new_x = x + possible_moves[0][i] ;

                    int new_y = y + possible_moves[1][i];

                    if ( new_x <= 2 && new_x >=0 && new_y <= 2 && new_y >=0 ){
                        swap(x,y,new_x,new_y);
                        State new_state = new State( arr_to_num() , current_state.depth+1 , current_state );
                        if ( ! explored.contains(new_state) )
                            fringe.add(new_state);
                        swap(x,y,new_x,new_y);
                    }
                }



            }

        }

        System.out.println("running time is " + ( System.nanoTime() - time ) / 1000 + " microseconds" );

        print_sol(null);

        explored = new HashSet<>();

    }


    private void swap (int x1 , int y1 , int x2 , int y2){
        int temp = board[x1][y1] ;
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp ;
    }

    private boolean goal_test ( int state ) {
        return state == 12345678 ;
    }

    private int[] num_to_arr(int state ) {

        int x = 0 ,y = 0 ;

        for (int i = 2 ; i >= 0 ; i-- ){
            for (int j = 2 ; j >= 0 ; j--){
                board[i][j] = state % 10 ;
                if ( board[i][j] == 0 ){
                    x = i ;
                    y = j ;
                }
                state /= 10 ;
            }
        }
        return new int[]{x,y};
    }

    private int arr_to_num( ) {

        int state = 0 ;

        for (int i = 0 ; i < 3 ; i++ ){
            for (int j = 0 ; j < 3 ; j++){
                state *= 10 ;
                state += board[i][j];
            }
        }

        return state ;

    }

    private void print_sol( State goal_state ){

        System.out.println("number of expanded nodes is "+explored.size());

        int max = 0 ;

        for (State w : explored){
            if ( w.depth > max )
                max = w.depth;
        }

        System.out.println("search depth is " + max);

        if ( goal_state == null ){
            System.out.println("No solution found !!");
            return;
        }

        Stack<State> path_to_goal = new Stack<>();

        path_to_goal.push(goal_state);

        State current = goal_state.parent ;

        while ( current != null ){
            path_to_goal.push(current);
            current = current.parent;
        }

        System.out.println("Path cost is "+ (path_to_goal.size()-1) +"\n");

        while (! path_to_goal.isEmpty())
            print_array(path_to_goal.pop().encoding);

    }

    private void print_array ( int state ){

        num_to_arr(state);

        System.out.println("|-----|-----|-----|");

        for (int i = 0 ; i < 3 ; i++) {
            System.out.println("|  " + board[i][0] + "  |  " + board[i][1] + "  |  " + board[i][2] + "  |");
            System.out.println("|-----|-----|-----|");
        }

        System.out.println("\n");
    }

    public static void main(String[] args) {

        Solver s = new Solver();

        s.solve_euclidean(125340678);

    }

}
