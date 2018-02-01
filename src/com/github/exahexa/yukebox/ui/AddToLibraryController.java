/**
 * 
 */
package com.github.exahexa.yukebox.ui;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.exahexa.yukebox.data.Progress;
import com.github.exahexa.yukebox.data.ProgressListener;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

/**
 * @author exahexa
 *
 */
public class AddToLibraryController implements ProgressListener{
	
	private MainGUI main;
	private ProgressIndicator pi;
	private Label progressLabel;
	/**
	 * 
	 */
	public AddToLibraryController(MainGUI main) {
		this.main = main;
	}

	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="anchor"
    private AnchorPane anchor; // Value injected by FXMLLoader
    
    @FXML // fx:id="box"
    private HBox box; // Value injected by FXMLLoader


    @FXML // fx:id="dirchooser_button"
    private Button dirchooser_button; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
       dirchooser_button.setOnAction(event->{
    	   DirectoryChooser dirChooser = new DirectoryChooser();
			dirChooser.setTitle("");
		    dirChooser.setInitialDirectory(
		            new File(System.getProperty("user.home"))
		    ); 
		    ProgressListener pl = this;
			File f = dirChooser.showDialog(anchor.getScene().getWindow());
			if(f != null) {
				Task task = new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						try {
							main.parse(f.toPath(), pl);
						}catch(IOException e) {
							e.printStackTrace();
						}
						return null;
					}					
				};
				
				initProgressDisplay();
				new Thread(task).start();		
			}
       });
    }
    
    private void initProgressDisplay(){
    	anchor.getChildren().clear();
    	VBox container = new VBox();
    	container.prefHeightProperty().bind(anchor.prefHeightProperty());
    	container.prefWidthProperty().bind(anchor.prefWidthProperty());
    	container.setAlignment(Pos.CENTER);
    	pi = new ProgressIndicator(0);
    	progressLabel = new Label();
    	container.getChildren().addAll(pi,progressLabel);
    	anchor.getChildren().add(container);
    }
        

	@Override
	public void progressChange(Progress progress) {
		Platform.runLater(()->{
			pi.setProgress((double)progress.getCurrent()/(double)progress.getMax());
			progressLabel.setText(progress.getCurElement() + "(" 
								+ progress.getCurrent() + "/" 
								+ progress.getMax() + ")");	

		});
	}
	
	
	
}
