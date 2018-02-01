/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import com.github.exahexa.yukebox.data.AudioFile;
import com.github.exahexa.yukebox.data.MusicLibObj;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * @author exahexa
 *
 */
public class TreeCellFactory extends TreeCell<MusicLibObj> {
	private Image playImg = new Image("file:resources/pic/png/t/play_h2.png");
	private Image playImgH = new Image("file:resources/pic/png/t/play2.png");
	private ImageView playIcon = new ImageView(playImg);
	//private ContextMenu menu = new ContextMenu();
	//private MenuItem add = new MenuItem("Add to playlist");
	
	public TreeCellFactory() {
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
				HBox cellBox = new HBox();
				cellBox.setAlignment(Pos.CENTER);
				Label label = new Label(item.getName());
				Region region = new Region();
				Button add_button = new Button("+");

				if(item instanceof AudioFile) {
					Button play_button = new Button();
					play_button.setGraphic(playIcon);
					play_button.setOnMouseEntered(handle->{
						playIcon.setImage(playImgH);
					});
					play_button.setOnMouseExited(handle->{
						playIcon.setImage(playImg);
					});
					cellBox.getChildren().addAll(play_button, label, region, add_button);
				}
				else {			
					cellBox.getChildren().addAll(label, region, add_button);
				}
				HBox.setHgrow(region, Priority.ALWAYS);
				setGraphic(cellBox);
			}
			
		}
	}
	

}
