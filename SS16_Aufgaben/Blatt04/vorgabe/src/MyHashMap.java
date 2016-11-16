/**
 * 
 * @author Algorithm and Datastructures Team SS2016
 * @version 1.0  
 * 
 */
import java.lang.RuntimeException;
public class MyHashMap {
	
	/**
	 * Use this array to store the values
	 * DO NOT MODIFY OR REMOVE THIS Attribute. Our tests rely on it.
	 */
	Student[] array;
	
	/**
	 * Tries inserting a Student into the hash map.
	 * Throw RuntimeException if the student is already in the table or the table is full.
	 */
	public void add(Student s){
		//TODO: My Code here.
		if(s==null)
			throw new RuntimeException("You want to add null to the hash table!");
		int position=this.hashFunction(s);
		boolean isfull=true;
		for(int i=0;i<this.array.length;i++){
			if(this.array[i]==null){
				isfull=false;
				break;
			}
		}
		if(isfull)
			throw new RuntimeException("Hash table is already full!");
		else{
			if(this.contains(s))
				throw new RuntimeException(s.getName()+' '+s.getSurname()+" already exists!");
			int p;	//position to insert
			for(p=position;this.array[p]!=null;p=(p+1)%this.array.length);
			//Student s1=new Student(s.getMatrikelNummer(),s.getName(),s.getSurname(),s.getDegree());
			//array[p]=s1;
			array[p]=s;
		}
	}
	
	/**
	 * Try removing a Student from the hash table.
	 * You should use the same implementation for remove discussed in the tutorial.
	 * You should NOT use the lazy deletion strategy (adding a special flag key indicating a deleted key)
	 * See https://en.wikipedia.org/wiki/Linear_probing#Deletion for more details about the algorithm!
	 * See what I've created in https://zh.wikipedia.org/wiki/%E7%BA%BF%E6%80%A7%E6%8E%A2%E6%B5%8B
	 * Throw RuntimeException if the hash table contained the Student
	 */
	public void remove(Student s){
		//TODO: My Code here.
		if(s==null)
			throw new RuntimeException("You want to remove null from the hash table!");
		else if(!this.contains(s))
			throw new RuntimeException("Hash table does not contain "+s.getName()+' '+s.getSurname());
		else{
			int i;
			for(i=this.hashFunction(s);s.equals(this.array[i])==false;i=(i+1)%this.array.length);	//to find the position of student
			while(true){                  
	              this.array[i]=null;	//delete student,this action makes a gap.
	              int j=i;	//j saves the position of the gap, the code below is to find the element to fill the gap
	              while(true){                                      
	                     i= (i+1)%this.array.length;  //to choose the nearst element
	                     if(this.array[i]==null)	//that is to say, the elements after null are arranged properly, so we can return.
	                            return;				//this will end the cycle
	                     int r=this.hashFunction(this.array[i]);
	                     if(j<r&&r<=i)	//the three condition is to check if the position of this element is between gap and the position now. if that is true, we will not choose this element
	                            continue;	//because the right position of this element should be after the gap, it is not affected by the gap
	                     else if(j>i&&j<r)							
	                            continue;
	                     else if(j>i&&r<=i)
	                            continue;                         
	                     else
	                    	 break;			//the element is at the wrong place, we must choose it.                         
	              }                                     
	              this.array[j]=this.array[i];	//choose that element to fill the gap and then delete it to make a new gap       
	       }
			
			//The code below is a way that after delete the whole hashtable to rearrange. 
			//It will affect the arrange of the elements, but after that ,all the element can be found.
			/*
			int hash_min=this.hashFunction(s);
			for(int i=hash_min;this.array[i]!=null;i=(i+1)%this.array.length){
				if(this.array[i].getMatrikelNummer()==s.getMatrikelNummer()){
					this.array[i]=null;	//to delect the student
					break;
				}
			}
			MyHashMap mhm=new MyHashMap(this.array.length);
			for(int i=0;i<this.array.length;i++){	//rearrange all the student in the hashtable
				Student s_tmp=this.array[i];
				if(s_tmp!=null){
					mhm.add(s_tmp);
				}
			}
			this.array=mhm.getArray();
			*/
		}
	}
	
	
	/**
	 * Returns true, if the hash table contains the given Student
	 */
	public boolean contains(Student s){
		//TODO: My Code here.
		if(s==null)
			return false;
		int position=this.hashFunction(s);
		for(int i=position,count=0;this.array[i]!=null&&count<this.array.length;i=(i+1)%this.array.length,count++)
			if(this.array[i].equals(s))
				return true;	//found
		return false;
	}
	
	/**
	 * @param h Hashvalue to search for
	 * @return Number of Student in the hash table that have the hashvalue h
	 */
	public int getNumberStudentsWithHashvalue(int h){
		int n = 0;
		//TODO: My Code here.
		if(h>=this.array.length)
			throw new RuntimeException("can not find this hash value in this table!");
		else
			for(int i=h;this.array[i]!=null;i=(i+1)%this.array.length)
				if(this.hashFunction(this.array[i])==h)
					n++;
		return n;
	}
	
	/**
	 * Doubles the size of the hash table. Recomputes the position of all elements using the
	 * new function.
	 */
	public void resize(){
		//TODO: Your Code here.
		MyHashMap mhm=new MyHashMap(this.array.length*2);
		//In order to reuse the function add, we should create a new HashMap instead of doing stupid thing in comment below:
		//		Student[] array_new=new Student[array.length*2];
		for(int i=0;i<this.array.length;i++){
			if(this.array[i]!=null)
				mhm.add(this.array[i]);
		}
		this.array=mhm.getArray();
	}

	/**
	 * This method return the array in which the strings are stored.
	 * DO NOT MODIFY OR REMOVE THIS METHOD. Our tests rely on it.
	 */
	public Student[] getArray(){
		return array;
	}
	
	/**
	 * DO NOT MODIFY OR REMOVE THIS METHOD. Our tests rely on it.
	 */
	public void setArray(Student[] array){
		this.array = array;
	}

	/**
	 * Runs the hash function for Student s (dependent on the size of the hash table)
	 * DO NOT MODIFY OR REMOVE THIS METHOD. Our tests rely on it.
	 * @param s Student
	 * @return the hash value for a student. (The position where it would be saved given no collisions)
	 */
	public int hashFunction(Student s){
		int hashvalue = Math.abs(s.hashCode()) % array.length;
		return hashvalue;
	}
	
	/**
	 * Constructor to initialize the hash with a given capacity
	 * DO NOT MODIFY OR REMOVE THIS METHOD. Our tests rely on it.
	 */
	public MyHashMap(int capacity){
		array = new Student[capacity];
	}

}

