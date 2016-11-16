import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A class implementing a Union-Find-data structure with representatives.
 * 
 * @author AlgoDat-Team
 */
public class UnionFindSet<T>{

	//You can use this map to store the representant for each element of the Union find structure
    private HashMap<T,T> element2representative;

	public UnionFindSet() {
		element2representative = new HashMap<>();
	}

	/**
	 * Takes a collection of n elements and adds 
	 * n disjoint partitions each with one element in it.
	 * 
	 * @param set
	 *            The set to be partitioned.
	 */
	public void add(List<T> elements) {
		// TODO Homework 2.1
		for(T e:elements){
			element2representative.put(e, e);
		}
	}

	/**
	 * Creates one disjoint partition with the element in it 
	 * and adds it to the Union-Find data structure
	 * 
	 * @param element
	 *            The element to put in the partition.
	 */
	public void add(T element) {
		// TODO Homework 2.1
		element2representative.put(element, element);
		
	}

	/**
	 * Retrieves the partition which contains the wanted element.
	 * 
	 * A partition is identified by a single, representative element.
	 * This function returns the representative of the partition
	 * that contains x. 
	 * 
	 * @param x
	 *            The element whose partition we want to know
	 * @return 
	 *            The representative element of the partition
	 */
	public T getRepresentative(T x) {
		// TODO Homework 2.1
		return element2representative.get(x);
	}

	/**
	 * Joins two partitions. First it retrieves the partitions containing the
	 * given elements. Removes one of the joined partitions from
	 * <code>partitions</code>.
	 * 
	 * @param x
	 *            One element whose partition is to be joined.
	 * @param y
	 *            The other element whose partition is to be joined.
	 */
	public void union(T x, T y) {
		// TODO Homework 2.1  
		if(!this.element2representative.containsKey(x))
			this.add(x);
		if(!this.element2representative.containsKey(y))
			this.add(y);
		T y_value=element2representative.get(y);
		T x_value=element2representative.get(x);
		if(x_value.equals(y_value))
			return;
		Set<Entry<T, T>> set=element2representative.entrySet();
		Iterator<Entry<T, T>> it=set.iterator();
		while(it.hasNext()){
			Map.Entry<T,T> entry=(Map.Entry<T,T>)it.next();
			if(entry.getValue().equals(y_value)) {
				T o=entry.getKey();
				element2representative.replace(o,y_value,x_value);
			}
		}
	}
}

