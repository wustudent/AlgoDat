import java.util.Comparator;
import java.lang.Math;

/**
 * This class implements a heuristic based on the "Manhatten" distance metric (L1 Norm)
 * 
 **/
public class HeuristicManhattan implements Comparator<CellNode> {
	
		/**
		 * The goal node which the heuristic operates on:
		 */
		CellNode goal;
		
		/**
		 * 
		 * @param goal
		 * 		the target/goal node the heuristic should be computed with
		 */
		public HeuristicManhattan(CellNode goal) {
			this.goal = goal;
		}
		
		/**
		 * Computes an estimate of the remaining distance from node n to the goal node and
		 * updates the node attribute distanceRemainingEstimate
		 * 
		 *  This class implements the L1 norm ("Manhattan" distance)
		 *  as the permissible heuristic
		 *  
		 * @param n
		 * 		node to estimate the remaining distance from
		 */
		public void estimateDistanceToGoal(CellNode n) {
        	//Your implementation here

        	//Hint: you only need to estimate once for each node.
        	 
        	}

    		/*
    		 * compares two nodes based on the distance heuristic
    		 *  
    		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
    		 */		
            public int compare(CellNode n1, CellNode n2) {
            	//Your implementation here
            	
            	return 0;
            }		
}

