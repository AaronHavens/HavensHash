/**
 * @author Aaron Havens
 */
package hashMapTests;
import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

import hashmapping.HashMap;

public class HashMapTests {
	HashMap<Integer> mapInt;
	HashMap<String> mapString;
	HashMap<Double> mapDouble;
	HashMap<Integer> bigMap;
	HashMap<Object> invalidMap;
	HashMap<Object> objMap;
	//Initialize ALL the hashMaps!
	@Before
	public void initialize(){
		mapInt = new HashMap<Integer>(10);
		mapString = new HashMap<String>(10);
		mapDouble = new HashMap<Double>(3);
		bigMap = new HashMap<Integer>(1000);
		invalidMap = new HashMap<Object>(0);
		objMap = new HashMap<Object>(10);
	}
	//add a few ints to the map, get them and add them together.
	@Test
	public void addSomeInts(){
		mapInt.set("hey",10);
		mapInt.set("hello", 9);
		mapInt.set("I think this might work", 500);
		int a = mapInt.get("hey");
		int b = mapInt.get("hello");
		int c = mapInt.get("I think this might work");
		assertEquals(10,a);
		assertEquals(519,a+b+c);
	}
	//delete from end case and check returned value
	@Test
	public void deleteFromEnd(){
		mapString.set("takingUpSpace", "here");
		mapString.set("remove me", "it works");
		String g = mapString.delete("remove me");
		assertEquals("it works",g);
		assertEquals(.1,mapString.load(),.01);
	}
	//delete the last element case and check for empty index
	@Test
	public void deleteLast(){
		mapString.set("removeMe", "here");
		String l = mapString.delete("removeMe");
		String n = mapString.get("removeMe");
		assertEquals(null,n);
		assertEquals("here",l);
	}
	//delete the first element case
	@Test
	public void deleteFirst(){
		mapInt.set("a", 1);
		mapInt.set("aa", 2);
		mapInt.set("aaa", 3);
		mapInt.set("aaaa", 4);
		mapInt.set("aaaaa", 5);
		mapInt.set("aaaaaa", 6);
		int first = mapInt.delete("a");
		assertEquals(1,first);
		assertTrue(mapInt.get("a") == null);	
	}
	//give the map a null input where it should fail
	@Test
	public void nullInput(){
		boolean a = mapInt.set("null",null);
		boolean b = mapString.set("null", null);
		boolean c = objMap.set("null", null);
		assertFalse(a);
		assertFalse(b);
		assertFalse(c);
		assertEquals(0,mapString.load(),.01);
		assertEquals(0,mapInt.load(),.01);
		assertEquals(0,objMap.load(),.01);
		
	}
	//Shows that even though size input was 0, the default is 10
	@Test
	public void badMap(){
		assertTrue(invalidMap.set("default", 0));
	}
	//Put a large number of things in a larger map
	@Test
	public void bigMaps(){
		Random r = new Random();
		for(int i = 0; i < 1000;i++){
			bigMap.set("I did" +r.nextInt(50000)+" pushups", 0);
			float load = bigMap.load();
			assertTrue(load > 0);
		}
	}
	//Checks to see if a map can be properly loaded to maximum capacity and return a load of 1.
	@Test 
	public void fullHouse(){
		mapDouble.set("hi", 0.0);
		mapDouble.set("hello", 0.0);
		mapDouble.set("isThisWorking", 0.0);
		mapDouble.set("PlsWork", 0.0);
		mapDouble.set("?", 0.0);
		assertEquals(1,mapDouble.load(),.01);
	}
	//verifies that object maps can be used after extracted and casted properly
	@Test
	public void getObj(){
		objMap.set("doot", 10);
		int a = (int)objMap.get("doot");
		assertEquals(10,a);
	}
}
