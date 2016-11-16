import java.io.IOException;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.Assert;

public class GraphTest {

	// Test correct total edge weight of MST
	@Test
	public void testWeight() {
		Graph g1;
		try {
			g1 = GraphIO.loadGraph("tests/testgraphen/test_kruskal01.txt");
			
			// get mst
			Graph mst = g1.toMinSpanTree();

			// get actual weight
			int actual = 0;
			for (Node n : mst.getNodes()) {
				for (Edge e : n.getIncidentEdges()) {
					actual += e.getWeight();
				}
			}

			// get correct weight
			int expected = 12;
			
			Assert.assertEquals("This is not the minimum spanning tree",
					expected, actual);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//You can implement the following tests yourself.
	//We recommend to add more test than just these examples.
	
	// test if the MinSpanTree has the correct number of edges
	@Test
	public void testEdgeCount() {
		DiGraph g1;
		try {
			g1 = GraphIO.loadGraph("tests/testgraphen/test_kruskal01.txt");
			
			// get mst
			DiGraph mst =(DiGraph) g1.toMinSpanTree();

			// get actual count
			int actual = mst.getEdges().size();
			

			// get correct count
			int expected = 6;
			
			Assert.assertEquals("Count to Edges is not correct:",
					expected, actual);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// Test if every node has at least one edge (in an MST with more than 1 node)
	@Test
	public void testEveryNodeHasEdges() {
		DiGraph g1;
		try {
			g1 = GraphIO.loadGraph("tests/testgraphen/test_kruskal01.txt");
			
			// get mst
			DiGraph mst =(DiGraph) g1.toMinSpanTree();
			LinkedList<Edge> edges= new LinkedList<Edge>(mst.getEdges());
			// get actual nodes
			g1.clearMarks();
			for(Edge e:edges){
				e.getStartnode().status=Node.BLACK;
				e.getEndnode().status=Node.BLACK;
			}
			LinkedList<Node> nodes= new LinkedList<Node>(mst.nodes.values());
			for(Node n:nodes)
			Assert.assertTrue("Count to Edges is not correct:",
					n.status==Node.BLACK);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// Test if the MinSpanTree actually connects all nodes
	@Test
	public void testConnectivity() {
		DiGraph g1;
		try {
			g1 = GraphIO.loadGraph("tests/testgraphen/test_kruskal01.txt");
			
			// get mst
			DiGraph mst =(DiGraph) g1.toMinSpanTree();
			LinkedList<Edge> edges= new LinkedList<Edge>(mst.getEdges());
			LinkedList<Node> nodes= new LinkedList<Node>(mst.nodes.values());
			UnionFindSet<Node> ufs=new UnionFindSet<Node>();
			ufs.add(nodes);
			for(Edge e:edges)
				ufs.union(e.getStartnode(),e.getEndnode());
			Node expected=ufs.getRepresentative(nodes.get(0));
			for(Node n:nodes){
				Assert.assertTrue("MinSpanTree actually not connects all nodes",ufs.getRepresentative(n)==expected);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	@Test
	public void testWrongGraph() {
		boolean isSuccess=false;
		DiGraph g1;
		try {
			g1 = GraphIO.loadGraph("tests/testgraphen/test_kruskal03.txt");
			try{
				g1.toMinSpanTree();
			}
			catch(RuntimeException e){
				isSuccess=true;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Assert.assertTrue("Graph error not caught!",isSuccess);
	}
}

