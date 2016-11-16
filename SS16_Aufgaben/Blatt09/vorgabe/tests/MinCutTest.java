import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class MinCutTest
{



	@Test
	public void testReachableNodes() throws IOException{
		Network g;
		g = GraphIO.loadGraph("tests/testgraphen/graphMC_2.txt");
		
		g.fordFulkerson();
		List<Node> nodes = g.residualGraph.findReachableNodes(0);
		
		Assert.assertEquals("findReachableNodes() does not find the correct number of nodes.", 2, nodes.size());
		int node0inSet=0;
		int node1inSet=0;
		for (Node n : nodes) {
			if (0 == n.getID()) {
				node0inSet++;
			} else if (1 == n.getID()){
				node1inSet++;
			} else {
				Assert.fail("The set contains an unexpected node");
			}
		}			
		Assert.assertEquals("Node 0 is missing in the Source set", 1, node0inSet);		
		Assert.assertEquals("Node 1 is missing in the Source set", 1, node1inSet);		
		
	}

	
	@Test
	public void testMinCutGraph1() throws IOException{
		Network g;
		g = GraphIO.loadGraph("tests/testgraphen/graphMC_1.txt");

		List<Edge> cutset = g.findMinCut();
		System.out.println("Your cutset in testMinCutGraph1:");
		for(Edge e:cutset)
			System.out.println("Edge:"+e.toString());
		Assert.assertEquals("size of cutset is wrong.", 2, cutset.size());
		Assert.assertTrue("Lack of edge 0->1 in ´this´",cutset.contains(g.getEdge(0, 1)));
		Assert.assertTrue("Lack of edge 0->2 in ´this´",cutset.contains(g.getEdge(0, 2)));
	}

	@Test
	public void testMinCutGraph2() throws IOException{
		Network g;
		g = GraphIO.loadGraph("tests/testgraphen/graphMC_2.txt");
		List<Edge> cutset = g.findMinCut();
		System.out.println("Your cutset in testMinCutGraph2:");
		for(Edge e:cutset)
			System.out.println("Edge:"+e.toString());
		String msg = String.format("Number of edges cut expected: %d, but got %d", 2, cutset.size());
		Assert.assertEquals(msg, 2, cutset.size());
		Assert.assertTrue("Lack of edge 1->3 in ´this´",cutset.contains(g.getEdge(1, 3)));
		Assert.assertTrue("Lack of edge 0->2 in ´this´",cutset.contains(g.getEdge(0, 2)));
	}

 }

