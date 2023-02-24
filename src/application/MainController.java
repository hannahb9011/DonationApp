/*
 * Hannah Bradley wql896
 * This is the controller for the Main.fxml, it creates the actions for those buttons
 * These buttons deal with taking you to the next scenes (NeedGive.fxml and Inventory.fxml)
 * 
 * */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController 
{
	@FXML
    private AnchorPane mainPane;
	
    @FXML
    public void toNeedGive(ActionEvent event) throws IOException //used to take you to NeedGive.fxml
    {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("NeedGive.fxml"));
    	mainPane = loader.load();
    	
    	//helps tells the UserController which button (needbutton or givebutton) was pressed
    	UserController controller = loader.getController();
    	controller.getAddSubtract(((Node)event.getSource()).getId());

    	Scene scene = new Scene(mainPane);// pane you are GOING TO show
    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
    }

    
    @FXML
    public void toInventory(ActionEvent event2) throws IOException //used to take you to Inventory.fxml
    {
    	mainPane = FXMLLoader.load(getClass().getResource("Inventory.fxml"));// pane you are GOING TO
        Scene scene2 = new Scene(mainPane);// pane you are GOING TO show
        scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Stage window2 = (Stage) ((Node)event2.getSource()).getScene().getWindow();// pane you are ON
        window2.setScene(scene2);
        window2.show();
    }
}
