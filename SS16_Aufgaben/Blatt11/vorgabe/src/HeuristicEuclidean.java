import java.util.Comparator;

/**
 * This class implements a heuristic based on the "Euclidean" distance metric (L2 Norm)
 * 
 **/
 public class HeuristicEuclidean implements Comparator<CellNode> {
	
	    /*
	    This is the goal node the heuristic uses to estimate the remaining distance
	    */
		CellNode goal;
		
		public HeuristicEuclidean(CellNode goal) {
			this.goal = goal;
		}
		
		/**
		 * Computes an estimate of the remaining distance from node n to the goal node and
		 * updates the node attribute distanceRemainingEstimate
		 * 
		 *  This class implements the L2 norm (euclidean distance)
		 *  as the permissible heuristic
		 *  
		 * @param n
		 * 		node to estimate the remaining distance from
		 */
		public void estimateDistanceToGoal(CellNode n) {
        	//Your implementation here
			//TODO
			n.distanceToGoalEstimate = 0.0; //Setting this to 0.0 effectively turns A* into Dijkstra
        	//Hint: you only need to compute the estimate once for each node.
        	double delta_x=n.i-this.goal.i;
        	double delta_y=n.j-this.goal.j;
        	n.distanceToGoalEstimate=Math.sqrt(delta_x*delta_x+delta_y*delta_y);
        	return;
		}
		
		
		
		/*
		 * compares two nodes based on an estimate of the path length to goal
		 * Computes the function cost(n) = d(start, n)+h(n, goal), 
		 * where d(start, n) is the distance from the start to n \
		 * and h(n, goal) is an estimate of the distance from n to the goal based on the Euclidean distance.
		 *  
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */		
        public int compare(CellNode n1, CellNode n2) {
        	//Your implementation here
        	//TODO
        	this.estimateDistanceToGoal(n1);
        	this.estimateDistanceToGoal(n2);
        	//double distance_n1=n1.distanceToGoalEstimate;
        	if(n1.distance+n1.distanceToGoalEstimate<n2.distance+n2.distanceToGoalEstimate)
        		return -1;
        	else if(n1.distance+n1.distanceToGoalEstimate==n1.distance+n2.distanceToGoalEstimate)
        		return 0;
        	else
        		return 1;
        }	
}

