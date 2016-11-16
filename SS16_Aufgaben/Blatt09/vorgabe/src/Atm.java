import java.util.LinkedList;
import java.util.List;
import java.lang.RuntimeException;

/**
 * The class <code>Node</code> implements a node in a network.
 * 
 * @author AlgoDat team
 */
public class Atm {

	public LinkedList<Integer> denominations;

    /**
	 * Initializes the banknote denominations available to the ATM
	 *
	 * @param name
	 *            the drawn value in visualization
	 **/
	public Atm() {
		// initialize list of available denominations
		denominations = new LinkedList<Integer>();
		//Add denominations in a sorted order, highest value first:
		denominations.add(200);
		denominations.add(100);
		denominations.add(50);
		denominations.add(20);
		denominations.add(10);
		denominations.add(5);
	}

	/**
	 * Computes the the number of banknotes for each denomination
	 * 
	 * @param total
	 *            Amount of money requested
	 *            End point of this edge.
	 * @return List<int> 
	 *            Amount of banknotes for each denomination, 
	 *            as a list in the same order as the list denominations
	 *            Example: [0,1,0,0,0,0]: one 100EUR banknote
	 */
	public LinkedList<Integer> getDivision(int total) {
		LinkedList<Integer> division = new LinkedList<Integer>();
		//TODO: Implement this
		
		for(int i=0;i<this.denominations.size();i++)
			division.add(0);
		int sum=0;
		for(int i=0;i<this.denominations.size();i++){
			if((total-sum)>=this.denominations.get(i)){
				division.set(i, division.get(i)+(total-sum)/denominations.get(i));
				sum+=(total-sum)/denominations.get(i)*denominations.get(i);
			}
		}
		if((total-sum)!=0)
			throw new RuntimeException("Can not offer this amount!");
		return division; 
		/*
		 * Below is with too few loop to waste time!
		 * 
		int sum=0;
		for(int i=0;i<this.denominations.size();i++)
			division.add(0);
		while((total-sum)!=0){
			boolean findFlag=false;
			for(int i=0;i<this.denominations.size();i++)
				if((total-sum)>=this.denominations.get(i)){
					sum+=this.denominations.get(i);
					division.set(i,division.get(i)+1);
					findFlag=true;
					break;
				}
			if(findFlag==false)
				throw new RuntimeException("Can not offer this amount!");
		}
		return division; 
		*/
	}
}

