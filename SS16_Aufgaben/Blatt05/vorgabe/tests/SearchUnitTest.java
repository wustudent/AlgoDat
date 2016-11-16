import static org.junit.Assert.*;

import java.io.Console;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SearchUnitTest {
	private DiGraph g1;

	@Before
	public void setUp() throws Exception {
		// read graph from file
		try {
			g1 = GraphIO.loadGraph("tests/testgraphen/graphBFS_VS_DFS.txt");
			g1.setShowSteps(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDepthFirstOrderEmptyGraph() {
		Node a=new Node(g1.nodes.size());
		DiGraph g=new DiGraph();
		boolean succeed=false;
		try{
			List<Node> nodeList = g.depthFirstSearch(a);
			if(nodeList==null)
				succeed=true;
		}
		catch(RuntimeException e){
			succeed=true;
		}
		assertTrue("testDepthFirstOrderEmptyGraph failed",succeed);

	}	

	@Test
	public void testDepthFirstOrderNonExistingNode() {
		Node a=new Node(g1.nodes.size());
		boolean succeed=false;
		try{
			List<Node> nodeList = g1.depthFirstSearch(a);
			if(nodeList==null)
				succeed=true;
		}
		catch(RuntimeException e){
			succeed=true;
		}
		assertTrue("testDepthFirstOrderEmptyGraph failed",succeed);
	}

	@Test
	public void testDepthFirstOrder() {
		List<Node> nodeList = g1.depthFirstSearch(4);
		String out="";
		for(Node i:nodeList){
			out+=i.id;
		}
		System.out.println("DFS:"+out);
		String[] expected={"430215","432105","432510","452103","452031"};
		String expectedStr="";
		boolean succeed=false;
		for(int i=0;i<expected.length;i++){
			if(out.equals(expected[i])) succeed=true;
			expectedStr+=expected[i]+" ";
		}
		assertTrue("DepthFirstOder failed,expected one from< "+expectedStr+">,but is< "+out+" >",succeed);
	}

	@Test
	public void testBreadthFirstOrderEmptyGraph() {
		Node a=new Node(g1.nodes.size());
		DiGraph g=new DiGraph();
		boolean succeed=false;
		try{
			List<Node> nodeList = g.breadthFirstSearch(a);
			if(nodeList==null)
				succeed=true;
		}
		catch(RuntimeException e){
			succeed=true;
		}
		assertTrue("testDepthFirstOrderEmptyGraph failed",succeed);
	} 

	@Test
	public void testBreadthFirstOrderNonExistingNode() {
		Node a=new Node(g1.nodes.size());
		boolean succeed=false;
		try{
			List<Node> nodeList = g1.breadthFirstSearch(a);
			if(nodeList==null)
				succeed=true;
		}
		catch(RuntimeException e){
			succeed=true;
		}
		assertTrue("testDepthFirstOrderEmptyGraph failed",succeed);
	}
  
	@Test
	public void testBreadthFirstOrder() {
		List<Node> nodeList = g1.breadthFirstSearch(4);
		String out="";
		for(Node i:nodeList){
			out+=i.id;
		}
		System.out.println("BFS:"+out);
		String[] expected={"435021","435201","453201"};
		String expectedStr="";
		boolean succeed=false;
		for(int i=0;i<expected.length;i++){
			if(out.equals(expected[i])) succeed=true;
			expectedStr+=expected[i]+" ";
		}
		assertTrue("BreadthFirstOder failed,expected one from< "+expectedStr+">,but is< "+out+" >",succeed);
	}	
	

}

