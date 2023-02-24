/*
 * Hannah Bradley wql896
 * These are the Methods used for the UserController.java's buttons. It is the logic for checking,
 * adding, and deleting information inputed into the text boxes.
 * 
 * */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Model 
{
	public static HashMap<String, String> h = new HashMap<String, String>();
	public static HashMap<String, String> inventory = new HashMap<String, String>();	
	public static Properties properties = new Properties();//Create properties file to store hash map
	
	public static void addItem(String itemKey, String value) throws IOException
	{
		if(itemKey == null || itemKey.isEmpty() || value == null || value.isEmpty()){
    		Alert error = new Alert(AlertType.ERROR);
    		error.setTitle("Error Message");
    		error.setHeaderText("Missing information");
    		error.setContentText("Please,try again");
    		error.showAndWait();
    	}
		else {
			int oldValue = 0;
    		int newValue = 0;
			
			properties.load(new FileInputStream("data.properties"));
    		for(String keys:properties.stringPropertyNames()) {
    			inventory.put(keys, properties.get(keys).toString());//put all properties elements inside the inventory hash table
    		}
    		if(inventory.containsKey(itemKey)) { //if matching key is in hash map
    			oldValue = Integer.parseInt(inventory.get(itemKey).toString());//get value that goes with this key
    			newValue = oldValue + Integer.parseInt(value);//subtract old value with new value user has entered
    			h.put(itemKey,String.valueOf(newValue));//adds updated value to hash map
    		}
    		else {
    			h.put(itemKey,value); //sends values to hash map   	      
    		}	
    		properties.putAll(h); //stores hash map values in a properties file
	        File file=new File("data.properties"); //create/open the file
	        FileOutputStream writer = new FileOutputStream(file,true); //appends to file
	        properties.store(writer,null); //stores values
		}
	}
	
	public static void subtractItem(String itemKey, String value) throws IOException
	{
		if(itemKey == null || itemKey.isEmpty() || value == null || value.isEmpty()){
    		Alert error = new Alert(AlertType.ERROR);
    		error.setTitle("Error Message");
    		error.setHeaderText("Missing information");
    		error.setContentText("Please,try again");
    		error.showAndWait();
    	}
    	else {
    		int oldValue = 0;
    		int newValue = 0;
    		
    		properties.load(new FileInputStream("data.properties"));
    		for(String keys:properties.stringPropertyNames()) {
    			inventory.put(keys, properties.get(keys).toString());//put all properties elements inside the inventory hash table
    		}
    		if(inventory.containsKey(itemKey)) { //if matching key is in hash map
    			oldValue = Integer.parseInt(inventory.get(itemKey).toString());//get value that goes with this key
    			newValue = oldValue - Integer.parseInt(value);//subtract old value with new value user has entered
    			h.put(itemKey,String.valueOf(newValue));//adds updated value to hash map
    		}
    		properties.putAll(h);//puts all updated values into the properties file
    		FileOutputStream file = new FileOutputStream("data.properties",true);//open file for writing
        	properties.store(file,null);//store all values
    	}
	}
	
	public static String getNumberOfItemsInInventory(String compKey) throws IOException
	{
		String val;
        File file = new File("data.properties");//opens file
        FileInputStream reader=new FileInputStream(file);//open reader
        properties.load(reader);//loads/lets you see stored values
        reader.close();

        for(String key: properties.stringPropertyNames()){//adding values to hash map
        	h.put(key, properties.get(key).toString());
        }
    	 if(h.containsKey(compKey)){//check if there's a key in the hash map that matches 
 	       	val = h.get(compKey); //set val to quantity of item
         }
    	 else {
    		val = "None in stock";//set val to show there's none in stock
    	 }
    	 return val;
	}
	
	public static boolean addUserName(String name)//returns true or false to see if confirmation should be printed after it
	{
		if(name.length() == 6)//checks for the size to be 6
		{	
			char[] first3 = new char[10];
			char[] last3 = new char[10];
			
			for (int i = 0; i < 3; i++) //takes the name string and separates it into 2 char arrays of 3 characters 
			{
	            	first3[i] = name.charAt(i);
	            	last3[i] = name.charAt(i+3);
	        }

			for (int i = 0; i < 3; i++) 
			{
				if(!Character.isLetter(first3[i])) //checks the first three char to see if they are letters, if they aren't print an error
				{
					Alert error = new Alert(AlertType.ERROR);
					error.setTitle("Error Message");
					error.setHeaderText("Incorrect ID Format");
					error.setContentText("Please,try again");
					error.showAndWait();
					return false;
				}
				if(!Character.isDigit(last3[i])) //checks the last three char to see if they are digits, if they aren't print an error
				{
					Alert error = new Alert(AlertType.ERROR);
					error.setTitle("Error Message");
					error.setHeaderText("Incorrect ID Format");
					error.setContentText("Please,try again");
					error.showAndWait();
					return false;
				}
			}
			
			return true;
		}
		else //print error if size is less than or more than 6 
		{
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error Message");
			error.setHeaderText("Incorrect ID Format");
			error.setContentText("Please,try again");
			error.showAndWait();
			return false;
		}
	}
}
