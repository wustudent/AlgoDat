import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Uwe + Damien
 * 
 */
public class Network implements INetwork {
	// -- attributes --
	private HashMap<Integer, Node> nodes;

	// -- constructor --
	public Network() {
		nodes = new HashMap<Integer, Node>();
	}

	// -- node functions --
	/**
	 * returns the nodes
	 */
	@Override
	public LinkedList<Node> getNodes() {
		return (LinkedList<Node>) nodes.values();
	}

	@Override
	public Node addNode() {
		int newId = nodes.size();
		Node newNode = new Node(newId);
		nodes.put(newId, newNode);
		return newNode;
	}

	// -- edge functions --
	public void addEdge(Node startNode, Node endNode, int capacity) {
		if (!(testEdgeNodes(startNode, endNode)))
			inputError();
		startNode.addEdge(startNode, endNode, capacity);
	}

	public void addEdge(int startnode, int endnode, int capacity) {
		addEdge(nodes.get(startnode), nodes.get(endnode), capacity);
	}

	/**
	 * Returns graph edge specified by source and destination indices.
	 * 
	 * @param startNodeInd
	 *            index of start node
	 * @param targetNode
	 *            index of target node
	 */
	public Edge getEdge(int startNodeInd, int targetNodeint) {
		Node n = nodes.get(startNodeInd);
		for (Edge e : n.getIncidentEdges())
			if (e.endNode.id == targetNodeint)
				return e;
		return null;
	}

	public boolean testEdgeNodes(Node startNode, Node endNode) {
		return (startNode != null) && (endNode != null)
				&& nodes.values().contains(startNode)
				&& nodes.values().contains(startNode);
	}

	// -- state reset functions --
	/**
	 * resets the state of all nodes and edges to white
	 */
	public void clearMarksAll() {
		clearMarksNodes();
		for (Node currentNode : nodes.values())
			for (Edge currentEdge : currentNode.getIncidentEdges())
				currentEdge.status = Edge.WHITE;
	}

	/**
	 * help function to reset the state of all nodes to white
	 */
	public void clearMarksNodes() {
		for (Node n : nodes.values())
			n.status = Node.WHITE;
	}

	public boolean isAdjacent(Node startNode, Node endNode) {
		if (!(testEdgeNodes(startNode, endNode)))
			inputError();
		return startNode.hasEdgeTo(endNode);
	}

	/**
	 * Searches for sources in the graph
	 * 
	 * @return All sources found in the graph
	 */
	public Node findSource() {
		LinkedList<Node> sources = new LinkedList<Node>();
		boolean isSource = true;
		// source <-> no incoming edges
		for (Node n : nodes.values()) {
			isSource = true;
			for (Node m : nodes.values()) {
				if (!m.equals(n) && isAdjacent(m, n)) {
					isSource = false;
					break;
				}
			}
			if (isSource)
				sources.add(n);
		}
		// error handling
		testSingle(sources);
		return sources.getFirst();
	}

	/**
	 * Searches the graph for sinks.
	 * 
	 * @return All sinks found in the graph
	 */
	public Node findSink() {
		LinkedList<Node> sinks = new LinkedList<Node>();
		// sink <-> no incoming edges
		for (Node n : nodes.values()) {
			if (n.getIncidentEdges().isEmpty())
				sinks.add(n);
		}
		// error handling
		testSingle(sinks);
		return sinks.getFirst();
	}

	public void testSingle(LinkedList<Node> nodes) {
		if (nodes.size() == 0 || nodes.size() > 1)
			inputError();
	}

	/**
	 * Finds an augmenting path from start to end in the graph A path is
	 * augmenting if all it's edges have residual capacity > 0 (You can choose
	 * from several algorithms to find a path)
	 * 
	 * @param startNodeId
	 *            the id of the start node from where we should start the search
	 * @param endNodeId
	 *            the id of the end node which we want to reach
	 * 
	 * @return the path from start to end or an empty list if there is none
	 */
	public LinkedList<Node> findAugmentingPath(int startNodeId, int endNodeId) {
		// TODO: Your implementation here
		Node start=this.nodes.get(startNodeId);
		Node end=this.nodes.get(endNodeId);
		LinkedList<Node> path=new LinkedList<Node>();
		if (start == null || !nodes.values().contains(start)) 
			throw new RuntimeException("Start Node does not exist");
		else if (end == null || !nodes.values().contains(end)) 
			throw new RuntimeException("End Node does not exist");
		else{
			clearMarksNodes();

			LinkedList<Node> queue = new LinkedList<Node>();
			start.status = Node.GRAY;
			queue.addLast(start);

			while (!queue.isEmpty()) {
				
				Node current = queue.removeFirst();
				current.status = Node.GRAY;
				if(!path.contains(current)){
					path.addLast(current);
				}
				//else{
				//	queue.removeFirst();
				//	continue;
				//}
				
				if(current.equals(end))
					break;
				else{
					for (Node neighbor : current.getSuccessorNodes()) {
						if (neighbor.status == Node.WHITE&&this.getEdge(current.id, neighbor.id).capacity>0) {
								queue.addLast(neighbor);
								break;
						}
						else{
							boolean isBlack=true;
							for(Node t:neighbor.getSuccessorNodes()){
								if(!t.equals(neighbor)){
									if(t.status!=Node.BLACK){
										isBlack=false;
										break;
									}
								}
							}
							if(isBlack)
								neighbor.status=Node.BLACK;
							else{
								isBlack=true;
								for(Node t:neighbor.getSuccessorNodes()){
									if(!t.equals(neighbor)){
										if(this.getEdge(neighbor.getID(), t.getID()).getCapacity()>0){
											isBlack=false;
											break;
										}
									}
								}
							}
							if(isBlack)
								neighbor.status=Node.BLACK;
						}
						//System.out.println(queue.toString());
					}
					System.out.println(path.toString());
				}
			}
			System.out.println("out");
			if(!path.getLast().equals(end))
				return new LinkedList<Node>();
			/*
			Map<Node,Node> parent = new HashMap<>();
	        Queue<Node> queue = new LinkedList<>();
	        queue.add(start);
	        start.status=Node.GRAY;
	        boolean foundAugmentedPath = false;
	        while(!queue.isEmpty()){
	            Node u = queue.poll();
	            for(Node v :u.getSuccessorNodes()){
	                if(v.status==Node.WHITE &&  this.getEdge(u.getID(), v.getID()).getCapacity()>0){
	                    parent.put(v, u);
	                    v.status=Node.GRAY;
	                    queue.add(v);
	                    if ( v .equals(end)) {
	                        foundAugmentedPath = true;
	                        break;
	                    }
	                }
	            }
	        }
	        if(!foundAugmentedPath)
	        	return new LinkedList<Node>();
	        else{
	        	Node v = end;
	            while(!v.equals(start)){
	                path.add(v);
	                Node u = parent.get(v);
	                v = u;
	            }
	            path.add(start);
	            //Collections.reverse(path);
	            
	            while(path.size()!=0){
	            	result.addFirst(path.poll());
	            }
	        }
			 */
		}
		return path;
		
		
	}

	// -- code to complete --

	/**
	 * Computes the maximum flow over the network with the Ford-Fulkerson
	 * Algorithm
	 * 
	 * @returns Value of maximal flow
	 */
	public int fordFulkerson() {

		// These methods find the source and sink in the network
		Node source = findSource();
		Node sink = findSink();

		// You can use this method to create a residual network
		Network residualGraph = initializeResidualGraph();
		
		// TODO: Your implementation here
		this.breadthFirstSearch(source);
		if(sink.status!=Node.BLACK)
			throw new RuntimeException("Source has no path to sink!");
		while(!residualGraph.findAugmentingPath(source.id, sink.id).isEmpty()){
			LinkedList<Node> path=residualGraph.findAugmentingPath(source.id, sink.id);
			System.out.println(path.toString());
			int minCapacity=residualGraph.findMinCapacity(path);
			residualGraph.updateResidualCapacity(minCapacity, path);
			for(int i=0;i<path.size()-1;i++){
				Node tmp1=path.get(i);
				//Node tmp1=path.poll();
				//Node tmp2=path.peek();
				Node tmp2=path.get(i+1);
				this.getEdge(tmp1.id, tmp2.id).currentFlow+=minCapacity;
				//this.getEdge(tmp2.id, tmp1.id).currentFlow=-this.getEdge(tmp1.id, tmp2.id).currentFlow;
			}
		}
		System.out.println(this.getFlow(source));
		return this.getFlow(source);
	}


	/**
	 * Builds the residual graph to a flow graph
	 * 
	 * @return the residual graph to this flow graph
	 */
	public Network initializeResidualGraph() {

		Network residualGraph = new Network();

		// adding nodes
		for (Node n : nodes.values())
			residualGraph.addNode();
		// adding edges
		for (Node n : nodes.values()) {
			for (Edge e : n.getIncidentEdges()) {
				// Add forward edges with same capacity
				residualGraph.addEdge(n.id, e.endNode.id, e.capacity);
				// Add backwards edges
				residualGraph.addEdge(e.endNode.id, n.id, 0);
			}
		}

		return residualGraph;
	}

	/**
	 * Finds the minimal residual capacity over the given path
	 * 
	 * @return the minimal capacity
	 */
	public int findMinCapacity(LinkedList<Node> path) {
		// TODO: Your implementation here
		if(path.size()<2)
			throw new RuntimeException("path is not exist");
		int min_capacity=path.peek().getEdgeTo(path.get(1)).capacity;
		for(int i=0;i<path.size()-1;i++){
			Node tmp1=path.get(i);
			Node tmp2=path.get(i+1);
			if(tmp1.getEdgeTo(tmp2).capacity<min_capacity)
				min_capacity=tmp1.getEdgeTo(tmp2).capacity;
		}
		return min_capacity;
	}

	/**
	 * Update capacity on given path, to be executed on residual graph
	 */
	public void updateResidualCapacity(int minCapacity, LinkedList<Node> path) {
		// TODO: Your implementation here
		for(int i=0;i<path.size()-1;i++){
			Node tmp1=path.get(i);
			Edge e1=tmp1.getEdgeTo(path.get(i+1));
			//System.out.println(e1.toString());
			Edge e2=path.get(i+1).getEdgeTo(tmp1);
			//System.out.println(e2.toString());
			e1.setCapacity(e1.getCapacity()-minCapacity);
			e2.setCapacity(e2.getCapacity()+minCapacity);
			//System.out.println(e1.toString());
			//System.out.println(e2.toString());
		}
	}

	/**
	 * Calculates the current flow in this graph.
	 * 
	 * @param source
	 *            the source of the flow
	 * 
	 * @return the value of the flow
	 */
	public int getFlow(Node source) {
		int flow = 0;
		for (Edge e : source.getIncidentEdges()) {
			if (e.currentFlow > 0)
				flow += e.currentFlow;
		}
		return flow;
	}

	public LinkedList<Node> breadthFirstSearch(int startNode) {
		return breadthFirstSearch(nodes.get(startNode));
	}

	public LinkedList<Node> breadthFirstSearch(Node startNode) {
		LinkedList<Node> nodeList = null;
		clearMarksNodes();

		if (startNode == null || !nodes.values().contains(startNode)) {
			nodeList = new LinkedList<Node>();
		} else {
			nodeList = new LinkedList<Node>();
			LinkedList<Node> queue = new LinkedList<Node>();

			startNode.status = Node.GRAY;
			queue.addLast(startNode);

			while (!queue.isEmpty()) {
				Node current = queue.removeFirst();
				current.status = Node.BLACK;
				nodeList.addLast(current);

				for (Node neighbor : current.getSuccessorNodes()) {

					if (neighbor.status == Node.WHITE) {
						neighbor.status = Node.GRAY;
						queue.addLast(neighbor);
					}
				}
			}
		}
		return nodeList;
	}

	// -- utils --
	public void inputError() {
		System.out.println("Incorrect input.");
		System.exit(1);
	}
}

