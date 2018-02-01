/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


/**
 * @author exahexa
 *
 */
public class NavigationController {
	
	MainGUI main;

	/**
	 * 
	 */
	public NavigationController(MainGUI main) {
		this.main = main;
	}
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="box"
    private VBox box; // Value injected by FXMLLoader


    @FXML // fx:id="artist_button"
    private Button artist_button; // Value injected by FXMLLoader

    @FXML // fx:id="album_button"
    private Button album_button; // Value injected by FXMLLoader

    @FXML // fx:id="song_button"
    private Button song_button; // Value injected by FXMLLoader
    
    @FXML // fx:id="currentPlayback"
    private Button currentPlayback; // Value injected by FXMLLoader
    
    @FXML // fx:id="playlistsBox"
    private VBox playlistsBox; // Value injected by FXMLLoader
    
    @FXML // fx:id="addPath"
    private Button addPath; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	artist_button.setOnAction(event->{
    		main.changeMusicLibView("artists");
    	});
    	album_button.setOnAction(event->{
    		main.changeMusicLibView("albums");
    	});
    	
    	currentPlayback.setOnAction(event->{
    		main.changePlaylistView("currentplayback");
    	});
    	
    	addPath.setOnAction(event->{
    		main.changeAddToLibView();
    	});
    	
    	getPlaylists();
    	
    	box.addEventHandler(MouseEvent.ANY, event->{
    		if(event.isSecondaryButtonDown()) {
    			contextMenu((Node)event.getTarget(), event.getScreenX(), event.getScreenY());
    		}
    	});
    	
    }
    
    private void getPlaylists() {
    	playlistsBox.getChildren().clear();
    	for(String s : main.getPlaylistNames()) {
	    	Button b = new Button(s);
	    	b.setOnAction(event->{
	    		main.changePlaylistView(s);
	    	});
	    	playlistsBox.getChildren().add(b);
    	}
    }
    
    private void contextMenu(Node n, double x, double y) {
    	ContextMenu menu = new ContextMenu();
    	MenuItem item1 = new MenuItem("Create new playlist");
    	item1.setOnAction( event ->{
    		TextInputDialog dialog = new TextInputDialog("new playlist");
			dialog.setTitle("Create new playlist");
			dialog.setHeaderText(null);
			dialog.setContentText("Please enter the playlist name");
			dialog.showAndWait().ifPresent(response -> createPlaylist(response));
    	});
    	menu.getItems().addAll(item1);
    	/**
    	menu.addEventHandler(MouseEvent.ANY, event->{
    		if(event.isPrimaryButtonDown() && menu.isShowing()) {
    			TextInputDialog dialog = new TextInputDialog("new playlist");
    			dialog.setTitle("Create new playlist");
    			dialog.setContentText("Please enter the playlist name");
    			dialog.showAndWait().ifPresent(response -> createPlaylist(response));
    			
    		}  		
    	});
    	*/    
    	menu.show(n, x, y);
    }
    
    private void createPlaylist(String s) {
    	if(s != null && !s.isEmpty()) {
    		main.createPlaylist(s);
    		getPlaylists();
    	}
    }
    
    

}
