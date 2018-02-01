/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import com.github.exahexa.yukebox.data.AudioFile;
import com.github.exahexa.yukebox.data.MusicLibObj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.beans.property.ReadOnlyObjectWrapper;

/**
 * @author exahexa
 *
 */
public class TableViewController {

	private MainGUI main;
	/**
	 * 
	 */
	public TableViewController(MainGUI main) {
	this.main = main;
	}
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="songs_table"
    private TableView<MusicLibObj> songs_table; // Value injected by FXMLLoader
    
    @FXML // fx:id="title"
    private TableColumn<AudioFile, String> title; // Value injected by FXMLLoader

    @FXML // fx:id="album"
    private TableColumn<AudioFile, String> album; // Value injected by FXMLLoader

    @FXML // fx:id="artist"
    private TableColumn<AudioFile, String> artist; // Value injected by FXMLLoader

    @FXML // fx:id="duration"
    private TableColumn<AudioFile, String> duration; // Value injected by FXMLLoader
    
    private ObservableList<MusicLibObj> trackList;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {	
    	setColumns();
    	songs_table.addEventHandler(MouseEvent.ANY, (event)->{
    		if(event.isPrimaryButtonDown()) {
    			main.addToPlaylist(songs_table.getSelectionModel().getSelectedItem());
    		}
    	});
    	
    }
    
    public void createView(Collection<MusicLibObj> c) {
    	if(trackList == null) {
    		trackList = FXCollections.observableArrayList();
    	}else {
    		trackList.clear();
    	}
    	trackList.addAll(c);
    	songs_table.getItems().clear();
    	songs_table.getItems().addAll(trackList);
    }
    
    private void setColumns() {
    	
    	title.setCellValueFactory(cellData -> {
    		return new ReadOnlyObjectWrapper<String>(
    									cellData.getValue().getName());
    	});
    	artist.setCellValueFactory(cellData -> {
    		return new ReadOnlyObjectWrapper<String>(
    									cellData.getValue().getArtist());
    	});
    	album.setCellValueFactory(cellData -> {
    		return new ReadOnlyObjectWrapper<String>(
    									cellData.getValue().getAlbum());
    	});
    	duration.setCellValueFactory(cellData -> {
    		return new ReadOnlyObjectWrapper<String>(
    							cellData.getValue().getDurationFormat());
    	});	
    	
    }

}
