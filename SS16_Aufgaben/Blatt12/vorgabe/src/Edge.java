/**
 * Class to store an edge in a list of edges incident on a node.
 */
public class Edge implements Comparable<Edge> {

	/**
	 * End point of this edge.
	 * */
	public Node endnode;

	/**
	 * Start point of this edge.
	 */
	public Node startnode;

	/**
	 * The weight of this edge.
	 * */
	public int weight;
	
	/**
	 * Stores the status of this edge, which is either WHITE, GRAY, or BLACK.
	 */
	public int status;
	static final int WHITE = 0;
	static final int GRAY = 1;
	static final int BLACK = 2;

	/**
	 * Creates an edge
	 * 
	 * @param endnode
	 *            End point of this edge.
	 * @param weight
	 *            Weight of this edge.
	 */
	public Edge(Node startnode, Node endnode, int weight) {
		this.startnode = startnode;
		this.endnode = endnode;
		this.weight = weight;
	}

	/**
	 * Converts this node into string.
	 */
	@Override
	public String toString() {
		return "Edge(toNode="+endnode.getID()+", weight="+weight+")";
	}
	
	/**
	 * Two edges are equal if the got the same start and end node and the same
	 * weight
	 */
	public boolean equals(Object o) {
		if (o instanceof Edge) {
			Edge other = (Edge) o;
			if (this.startnode.equals(other.startnode)
					&& this.endnode.equals(other.endnode)
					&& this.weight == other.weight) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Compares the weight of two given edges
	 */
	@Override
	public int compareTo(Edge o) {

		return this.weight - o.weight;
	}
}


