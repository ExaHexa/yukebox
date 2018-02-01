/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import java.net.URL;
import java.util.ResourceBundle;
import com.github.exahexa.yukebox.data.AudioFile;
import com.github.exahexa.yukebox.data.MusicLibObj;

import javafx.fxml.FXML;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * @author exahexa
 *
 */
public class TreeViewController {
	
	private MainGUI main;
	/**
	 * 
	 */
	public TreeViewController(MainGUI main) {
		this.main = main;
	}
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="treeView"
    private TreeView<MusicLibObj> treeView; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	treeView.setShowRoot(false);
    	treeView.addEventHandler(MouseEvent.ANY, (event)-> {
	    		if(event.isPrimaryButtonDown()) {
	    			//double click event
	    			if(event.getClickCount() == 2) {
	    				if(treeView.getSelectionModel()
	    							 .getSelectedItem().getValue() 
	    										instanceof AudioFile) {
		    				AudioFile a = (AudioFile)treeView
		    							  .getSelectionModel()
		    							  .getSelectedItem().getValue();
		    				//playMedia(main.getMedia(a));
	    				}	
	    			}
	    			// lazy load
	    			TreeItem<MusicLibObj> t = treeView
		    				.getSelectionModel().getSelectedItem();
			    	if(t.isExpanded()) {
			    		t.setExpanded(false);
			    	}else {
			    		t.setExpanded(true);
			    	}			    		
	    		}
	    		/**add to playlist event 
	    		if(event.isSecondaryButtonDown()) {
	    			MusicLibObj tmp = artistTree.getSelectionModel().getSelectedItem().getValue();
	    			if(tmp instanceof AudioFile) {
	    				playList.add( ((AudioFile) tmp) );
	    			}
	    			else if(tmp instanceof Artist) {
	    				for(Album album : ((Artist) tmp).getAlbums().values()) {
	    					for(AudioFile a : album.getTracks().values()) {
	    						playList.add(a);
	    					}
	    				}
	    			}
	    			else if(tmp instanceof Album) {
	    				for(AudioFile a : ((Album) tmp).getTracks().values()) {
	    					playList.add(a);
	    				}
	    			}
	    		
	    		}
    	*/
    	});
    	
    	
    	TreeItem<MusicLibObj> rootItem = new TreeItem<MusicLibObj>();  
    	rootItem.setExpanded(true);
    	//TreeMap<String, Artist> musicDB = main.getMusicLibrary();
    	for(MusicLibObj e : main.getMusicLibrary()) {
    		TreeItem<MusicLibObj> artistItem = new LazyTreeItem(e);
    		rootItem.getChildren().add(artistItem);
    	}
    	treeView.setRoot(rootItem);
    	treeView.setCellFactory(new Callback<TreeView<MusicLibObj>, TreeCell<MusicLibObj>>(){
    		@Override
    		public TreeCell<MusicLibObj> call(TreeView<MusicLibObj> p){
    			return new TreeCellFactory();
    		}
    	});
     
    }

}
