/**
0 * @author wustudent
 *
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MyHashMapUnitTest {

	private MyHashMap hashmap;
	private Student s1, s2, s3, s4, s5, s6, s7, s8;//, s9;
	
	@Before
	public void setUp() throws Exception {
		hashmap = new MyHashMap(7);
		s1 = new Student(1, "Max", "Mustermann", "Informatik");
		s2 = new Student(2, "Annika", "Mustermann", "Mathematik");
		s3 = new Student(3, "Alex", "Mustermann", "Biologie");
		s4 = new Student(4, "Julie", "Mustermann", "TI");
		s5 = new Student(5, "Leila", "Mustermann", "Informatik");
		s6 = new Student(6, "Arne", "Mustermann", "Jura");
		s7 = new Student(7, "Bella", "Mustermann", "Physik");
		s8 = new Student(8, "Linus", "Mustermann", "Psychologie");
		//s9 = new Student(9, "Albert", "Mustermann", "Informatik");
		/*
			System.out.println(hashmap.hashFunction(s1));
			System.out.println(hashmap.hashFunction(s2));
			System.out.println(hashmap.hashFunction(s3));
			System.out.println(hashmap.hashFunction(s4));
			System.out.println(hashmap.hashFunction(s5));
			System.out.println(hashmap.hashFunction(s6));
			System.out.println(hashmap.hashFunction(s7));
			System.out.println(hashmap.hashFunction(s8));
			System.out.println(hashmap.hashFunction(s9));
			System.out.println(" ");
		*/
	}

	/**
	 * Adds an element to the hash table. Checks if it is in the array
	 */
	@Test(timeout=1000)
	public void testSimpleAdd() {
		hashmap.add(s1);
		Student[] array = hashmap.getArray();
		boolean success = false;
		for (int i = 0; i < array.length; i++) {
			Student s = array[i];
			if (s1.equals(s)){
				success = true;
				break;
			}
		}
		assertTrue("add() failed. Element not found in array", success);
	}

	/**
	 * Remove an element from a 
	 */
	@Test(timeout=1000)
	public void testRemove() {
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		hashmap.setArray(array);
		hashmap.remove(s2);
		// Check if the element has been removed
		boolean success = true;
		array = hashmap.getArray();
		for (int i = 0; i < array.length; i++) {
			Student s = array[i];
			if (s2.equals(s)){
				success = false;
				break;
			}
		}
		assertTrue("remove() failed. Element was not removed from hashtable", success);
	}

	@Test(timeout=1000)
	public void testContains() {
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		hashmap.setArray(array);
		assertTrue("contains() failed. Element was not found in the hashtable", hashmap.contains(s2));
	}
	
	/**
	 * Test getNumberStudentsWithHashvalue
	 */
	@Test(timeout=1000)
	public void testGetNumberStudentsWithHashvalue() {
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		array[0] = s5;
		array[3] = s1;
		array[4] = s3;
		hashmap.setArray(array);/*
		for(int i=0;i<array.length;i++){
			if(array[i]!=null)
				System.out.println("array["+i+"] hash:"+hashmap.hashFunction(array[i])+" "+array[i].toString());
		}*/
		assertEquals("getNumberStudentsWithHashvalue() failed. Wrong number",3, hashmap.getNumberStudentsWithHashvalue(5));
		// 2 is wrong? should be 3.
	}
	
	
	@Test(timeout=1000)
	public void testTrivialResize() {
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		array[0] = s5;
		array[3] = s1;
		array[4] = s3;
		hashmap.setArray(array);
		hashmap.resize();
		int newsize = hashmap.getArray().length;
		assertEquals("resize() failed; length of new hash table is not the double of the old one", 14, newsize);
	}
	
	
	
	/* The rest of these tests are not implemented. We include them 
	 * to give you an idea on the parts of your code that we will test for the correction.
	 * Note, that these is not the final list of tests that we will use
	 * We do recommend that you implement these tests. 
	 * But, YOU DO NOT HAVE TO IMPLEMENT THEM!
	 */
	
	/**
	 * Tests resize: Checks that each element is saved in the correct position after a resize operation
	 */
	@Test(timeout=1000)
	public void testResize(){
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		array[0] = s5;
		array[3] = s1;
		array[4] = s3;
		hashmap.setArray(array);
		hashmap.resize();
		Student[] array_new = hashmap.getArray();
		/*
		for(int i=0;i<array_new.length;i++){
			if(array_new[i]!=null)
				System.out.println("array_new["+i+"] hash:"+hashmap.hashFunction(array_new[i])+" "+array_new[i].toString());
		}*/
		assertEquals("Resize() failed. Element in wrong Position.",true, array_new[0]==null);
		assertEquals("Resize() failed. Element in wrong Position.",true, s5.equals(array_new[hashmap.hashFunction(s5)]));
	}
	
	/**
	 * Test remove an element saved in the wrong position of the hashtable.
	 * This test will set the array of MyHashTable with an array containing one element on the wrong index
	 * We then try removing these elements. This should not be possible.
	 */
	@Test(timeout=1000)
	public void testWrongRemove() {
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		array[0] = s5;
		array[3] = s1;
		array[2] = s3;	// This is the element at the wrong position.
		hashmap.setArray(array);
		System.out.println("testWrongRemove:");
		for(int i=0;i<array.length;i++){
			if(array[i]!=null)
				System.out.println("array["+i+"] hash:"+hashmap.hashFunction(array[i])+" "+array[i].toString());
			else
				System.out.println("array["+i+"] is NULL");
		}
		
		boolean succeed=false;
		try{
			hashmap.remove(s3);
		}catch(RuntimeException err){
			succeed=true;
		}
		for(int i=0;i<array.length;i++){
			if(hashmap.array[i]!=null)
				System.out.println("array_afterRemove["+i+"] hash:"+hashmap.hashFunction(hashmap.array[i])+" "+hashmap.array[i].toString());
			else
				System.out.println("array_afterRemove["+i+"] is NULL");
		}
		System.out.println(" ");
		assertEquals("incorrent use of Remove()",true, s3.equals(array[2]));
		assertTrue("No RuntimeException throws",succeed);
	}
	

	/**
	 * Add element to the hash table twice. Checks if an exception is thrown.
	 */
	@Test(timeout=1000)//,expected=RuntimeException.class)
	public void testAddElementTwice() {
		boolean succeed=false;
		try{
			hashmap.add(s1);
			hashmap.add(s1);
		}catch(RuntimeException err){
			succeed=true;
		}
		assertTrue("No RuntimeException throws",succeed);
	}
	
	/**
	 * Add elements to the hashtable until it is full. Check if an exception is thrown
	 */
	@Test(timeout=1000)
	public void testAddUntilFull() {
		boolean succeed=false;
		try{
			hashmap.add(s1);
			hashmap.add(s2);
			hashmap.add(s3);
			hashmap.add(s4);
			hashmap.add(s5);
			hashmap.add(s6);
			hashmap.add(s7);
			hashmap.add(s8);
		}catch(RuntimeException err){
			succeed=true;
		}
		assertTrue("No RuntimeException throws",succeed);
	}
	
	/**
	 * Add an element, then remove it. Check what contains return.
	 */
	@Test(timeout=1000)
	public void testAddRemoveContains() {
		hashmap.add(s1);
		hashmap.remove(s1);
		assertFalse("After remove still contains",hashmap.contains(s1));
	}
	
	/**
	 * Remove an element twice. Check for the exception.
	 */
	@Test(timeout=1000)
	public void testRemoveElementTwice() {
		boolean succeed=false;
		try{
			hashmap.add(s1);
			hashmap.add(s2);
			hashmap.add(s3);
			hashmap.add(s4);
			hashmap.add(s5);
			hashmap.add(s6);
			hashmap.add(s7);
			hashmap.remove(s1);
			hashmap.remove(s1);
		}catch(RuntimeException err){
			succeed=true;
		}
		assertTrue("No RuntimeException throws",succeed);
	}
	
	/**
	 * Remove an element from a , test the order of other affected element
	 */
	@Test(timeout=1000)
	public void testRemoveOrder() {
		Student[] array = new Student[7];
		array[0] = s4;
		array[1] = s6; 
		array[2] = s5;  
		array[3] = s1;  
		array[4] = s3;  
		array[5] = s2;  
		array[6] = s8;  
		hashmap.setArray(array);
		System.out.println("Remove order test:");
		for(int i=0;i<array.length;i++){
			if(array[i]!=null)
				System.out.println("array["+i+"] hash:"+hashmap.hashFunction(array[i])+" "+array[i].toString());
			else
				System.out.println("array["+i+"] is NULL");
		}
		hashmap.remove(s8);
		for(int i=0;i<array.length;i++){
			if(hashmap.array[i]!=null)
				System.out.println("array_afterRemove["+i+"] hash:"+hashmap.hashFunction(hashmap.array[i])+" "+hashmap.array[i].toString());
			else
				System.out.println("array_afterRemove["+i+"] is NULL");
		}
		
		// Check if the element has been removed rightly.
		boolean succeed=true;
		if(!hashmap.array[0].equals(s6)) succeed=false;
		if(!hashmap.array[1].equals(s5)) succeed=false;
		if(hashmap.array[2]!=null) 		 succeed=false;
		if(!hashmap.array[3].equals(s1)) succeed=false;
		if(!hashmap.array[4].equals(s3)) succeed=false;
		if(!hashmap.array[5].equals(s2)) succeed=false;
		if(!hashmap.array[6].equals(s4)) succeed=false;
		assertTrue("remove() failed. Element was not in correct position",succeed);
	}
	

}

