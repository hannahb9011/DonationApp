/*
 * Hannah Bradley wql896
 * this is a test class to check all the methods that are in the model class
 * 
 * */
package test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import model.Model;

public class testTHAppTest {
	//test values
	Model model;
	String username = "wql896";
	String item = "strawberries";
	String quantity = "4";
	
	
	@Before
	public void setUp() throws FileNotFoundException, IOException {
		Model.properties.load(new FileInputStream("data.properties")); //loads properties file 
		 for(String key: Model.properties.stringPropertyNames()){ //adding all values to hash map
	        	Model.inventory.put(key, Model.properties.get(key).toString());
	        }	
	} //Method runs before each test is run, setting up any necessary items.
	
	@Test
	public void testAddItem() {
		String actual = Model.inventory.get(item); 
		assertEquals(actual , quantity);
	} //What happens when someone donates a item
	        
	@Test
	public void testGetNumberOfItemsInInventory() throws IOException {
		assertEquals(Model.inventory.get(item), quantity);
	}
	
	@Test
	public void testSubtractItem() {
		String actual = Model.inventory.get(item); 
		assertEquals(actual , quantity);
	} //What happens when someone needs a item
	
	@Test
	public void testAddUserName() {
		assertTrue(model.addUserName(username));
	} //Tests what happens when user adds a new name to the
	

}
