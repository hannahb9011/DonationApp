/*
 * Hannah Bradley wql896
 * This is the controller for the NeedGive.fxml, it creates the actions for those buttons
 * These buttons deal with taking in items and deleting items from an inventory file
 * 
 * */
package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import model.Model;//created this model class
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserController 
{
	@FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private TextField text3;
    @FXML
    private TextField text4;
    @FXML
    private TextField text5;
    @FXML
    private Button button1;
    @FXML
    private Button givebutton;
    @FXML
    private Button needbutton;
    @FXML
    private AnchorPane needgivePane;
    @FXML
    private AnchorPane inventorypane;
    
    private String selection;
    
    public void getAddSubtract(String selection) {//saves string for which button was selected in Main.fxml screen
    	this.selection = selection;
    }
    
    @FXML
	public void Clear(ActionEvent event) throws IOException
	{
    	String name = text1.getText().toString();
    	String itemKey = text2.getText().toString(); //gets text(item) and turns it into a string
      	String value = text3.getText().toString(); //gets text(quantity) and turns it into a string
    	
      	if(selection.equals("givebutton"))
    	{
      		
      		Model.addItem(itemKey,value);
    	}
    	else if(selection.equals("needbutton")) 
    	{
	    	Model.subtractItem(itemKey, value);
	        
    	}
      	
        //clears all text boxes 
    	text1.clear();
    	text2.clear();
    	text3.clear();
    	
    	boolean validName = Model.addUserName(name);
    		
    	if(validName == true) {
			//creates pop-up to confirm
			Alert a = new Alert(AlertType.NONE);
			// set alert type
			a.setAlertType(AlertType.CONFIRMATION);
			// show the dialog
			a.show();
    	}		
	}
    
    @FXML
   	public void findItem(ActionEvent event) throws IOException
   	{  	
    	String compKey = text4.getText().toString();//gets text(item) and turns it into a string
    	String val = Model.getNumberOfItemsInInventory(compKey);//gets item 
    	text5.setText(val);//prints stock to text box	  	
   	}
    	
    @FXML
    public void handle4(ActionEvent event) throws IOException //used to take you to Main.fxml
    {
    	needgivePane = FXMLLoader.load(getClass().getResource("Main.fxml"));// pane you are GOING TO
        Scene scene = new Scene(needgivePane);// pane you are GOING TO show
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }    
	
    @FXML
    public void handle5(ActionEvent event2) throws IOException //used to take you to Main.fxml
    {
    	inventorypane = FXMLLoader.load(getClass().getResource("Main.fxml"));// pane you are GOING TO
        Scene scene2 = new Scene(inventorypane);// pane you are GOING TO show
        scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Stage window2 = (Stage) ((Node)event2.getSource()).getScene().getWindow();// pane you are ON
        window2.setScene(scene2);
        window2.show();
    }
}
