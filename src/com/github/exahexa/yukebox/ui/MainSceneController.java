/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;


import com.github.exahexa.yukebox.data.AudioFile;
import com.github.exahexa.yukebox.data.Album;
import com.github.exahexa.yukebox.data.Artist;
import com.github.exahexa.yukebox.data.MusicLibObj;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Callback;

/**
 * @author exahexa
 *
 */
public class MainSceneController {
	
	MainGUI main;
	
	public MainSceneController(MainGUI main) {
		this.main = main;
	}
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tab_artist"
    private Tab tab_artist; // Value injected by FXMLLoader

    @FXML // fx:id="artistTree"
    private TreeView<MusicLibObj> artistTree; // Value injected by FXMLLoader

    @FXML // fx:id="tab_album"
    private Tab tab_album; // Value injected by FXMLLoader

    @FXML // fx:id="tab_folder"
    private Tab tab_folder; // Value injected by FXMLLoader
    
    @FXML // fx:id="play_button"
    private ImageView play_button; // Value injected by FXMLLoader

    @FXML // fx:id="forward_button"
    private ImageView forward_button; // Value injected by FXMLLoader

    @FXML // fx:id="backward_button"
    private ImageView backward_button; // Value injected by FXMLLoader
    
    @FXML // fx:id="playlist_table"
    private TableView<AudioFile> playlist_table; // Value injected by FXMLLoader

    @FXML // fx:id="titleTableColumn"
    private TableColumn<AudioFile, String> titleTableColumn; // Value injected by FXMLLoader

    @FXML // fx:id="artistTableColumn"
    private TableColumn<AudioFile, String> artistTableColumn; // Value injected by FXMLLoader

    @FXML // fx:id="albumTableColumn"
    private TableColumn<AudioFile, String> albumTableColumn; // Value injected by FXMLLoader

    @FXML // fx:id="durationTableColumn"
    private TableColumn<AudioFile, String> durationTableColumn; // Value injected by FXMLLoader
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {    	
    	main.createPlaylist("test");
    	
    	if(!main.getMusicLibrary().isEmpty()) {
    		setArtistTree();
    		setPlaylistColumn();
    	}
    }
    
    private MediaPlayer player;
    
    private void playMedia(AudioFile a) {
    	if(player != null) {
    		player.stop();
    		player = new MediaPlayer(new Media(new File(a.getFilePath()).toURI().toString() ));
    		player.play();
    	}else {
    		player = new MediaPlayer(new Media(new File(a.getFilePath()).toURI().toString() ));
        	player.play();
    	}
    	
    }
    private void setArtistTree() {
    	
    	artistTree.setShowRoot(false);
    	artistTree.addEventHandler(MouseEvent.ANY, (event)-> {
	    		if(event.isPrimaryButtonDown()) {
	    			if(event.getClickCount() == 2) {
	    				if(artistTree.getSelectionModel().getSelectedItem().getValue() instanceof AudioFile) {
		    				AudioFile a = (AudioFile)artistTree.getSelectionModel().getSelectedItem().getValue();
		    				playMedia(a);
	    				}	
	    			}
	    			TreeItem<MusicLibObj> t = artistTree
		    				.getSelectionModel().getSelectedItem();
			    	if(t.isExpanded()) {
			    		t.setExpanded(false);
			    	}else {
			    		t.setExpanded(true);
			    	}			    		
	    		}
	    		if(event.isSecondaryButtonDown()) {
	    			if(artistTree.getSelectionModel().getSelectedItem().getValue() instanceof AudioFile) {
	    				AudioFile a = (AudioFile)artistTree.getSelectionModel().getSelectedItem().getValue();
	    				playList.add(a);
	    			}	
	    		
	    		}
    	});
    	
    	TreeItem<MusicLibObj> rootItem = new TreeItem<MusicLibObj>();  
    	rootItem.setExpanded(true);
    	//rootItem.addEventHandler(eventType, eventHandler);
    	TreeMap<String, Artist> musicDB = new TreeMap<String, Artist>(
    									 			main.getMusicLibrary());
    	for(Artist artist : musicDB.values()) {
    		TreeItem<MusicLibObj> artistItem = new LazyTreeItem(artist);
    		rootItem.getChildren().add(artistItem);
    	}
    	artistTree.setRoot(rootItem);
    	artistTree.setCellFactory(new Callback<TreeView<MusicLibObj>, TreeCell<MusicLibObj>>(){
    		@Override
    		public TreeCell<MusicLibObj> call(TreeView<MusicLibObj> p){
    			return new TreeCellEventHandling();
    		}
    	});
    	
    }
    
    private ObservableList<AudioFile> playList;
    
	private void setPlaylistColumn() {
    	
    	playList = FXCollections.observableArrayList(
    			main.getPlaylist("test"));
    	
    	playList.addListener((ListChangeListener.Change
    						<? extends AudioFile> change) -> {
    	    while(change.next()) {
    	    	  for(AudioFile a : change.getAddedSubList()){
    	    		  main.addToPlaylist("test", a);
			      }
    	    }		
    	});
    	
    	titleTableColumn.setCellValueFactory(cellData -> {
    		return new ReadOnlyObjectWrapper<String>(cellData.getValue().getName());
    	});
    	artistTableColumn.setCellValueFactory(cellData -> {
    		return new ReadOnlyObjectWrapper<String>(cellData.getValue().getArtist());
    	});
    	albumTableColumn.setCellValueFactory(cellData -> {
    		return new ReadOnlyObjectWrapper<String>(cellData.getValue().getAlbum());
    	});
    	durationTableColumn.setCellValueFactory(cellData -> {
    		return new ReadOnlyObjectWrapper<String>(cellData.getValue().getDurationFormat());
    	});	
    	playlist_table.setItems(playList);
    }
    
    

}
