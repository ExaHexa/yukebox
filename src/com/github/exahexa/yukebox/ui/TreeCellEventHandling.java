/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import com.github.exahexa.yukebox.data.Album;
import com.github.exahexa.yukebox.data.AudioFile;
import com.github.exahexa.yukebox.data.MusicLibObj;
import com.github.exahexa.yukebox.data.PlaylistManager;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;

/**
 * @author exahexa
 *
 */
public class TreeCellEventHandling extends TreeCell<MusicLibObj> {
	
	//private ContextMenu menu = new ContextMenu();
	//private MenuItem add = new MenuItem("Add to playlist");
	
	public TreeCellEventHandling() {
		/**
		menu.getItems().add(add);
		menu.setOnAction(new EventHandler() {
			public void handle(Event t) {
				if(getItem() instanceof AudioFile) {
					
					//PlaylistManager.getInstance().addToPlaylist("test", (AudioFile)getItem());
				}
			}
		});
		*/
		
	}
	
	@Override
	public void startEdit() {
		super.startEdit();
	}
	
	@Override
	public void cancelEdit() {
		super.cancelEdit();
	}
	
	@Override
	public void updateItem(MusicLibObj item, boolean empty) {
		super.updateItem(item, empty);
		if(empty) {
			setText(null);
			setGraphic(null);
		}
		else {
			if(item != null) {
				setText(getItem().getName());
			}
			
		}
	}
	

}
