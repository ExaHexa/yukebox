/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


/**
 * @author exahexa
 *
 */
public class TopController {
	
	MainGUI main;

	/**
	 * 
	 */
	public TopController(MainGUI main) {
		this.main = main;
	}
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="hbox"
    private HBox hbox; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
  
    }
    
    public void addPath(String value) {
    	Button b;
    	if(value == "artists") {
    		hbox.getChildren().clear();
    		b = new Button("All Artists");
    	}else if(value == "albums") {
    		hbox.getChildren().clear();
    		b = new Button("All Albums");
    	}else {
    		b = new Button(value);        
    	}    	
    	b.setId(value);
    	b.setOnAction(event->{
    		hbox.getChildren().subList(hbox.getChildren().indexOf(b), hbox.getChildren().size()).clear();
    		main.changeMusicLibView(b.getId());
    	});
    	hbox.getChildren().add(b);
    }

}
