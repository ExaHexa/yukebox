/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import com.github.exahexa.yukebox.data.MusicLibObj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;


/**
 * @author exahexa
 *
 */
public class TileViewController {
	
	private MainGUI main;

	/**
	 * 
	 */
	public TileViewController(MainGUI main) {
		this.main = main;
	}
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML
    private ScrollPane scrollpane;

    @FXML // fx:id="tilePane"
    private TilePane tilePane; // Value injected by FXMLLoader
    
    private Image backgroundImg = new Image("file:resources/pic/record.png");
    //private Image play = new Image("file:resources/pic/png/t/play.png");
    //private ImageView play_b = new ImageView(play);
    private Background background;
    
    private ObservableList<Group> tiles;
    private ContextMenu menu = new ContextMenu();
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	scrollpane.setId("scrollpane");
    	scrollpane.setCache(true);
    	tilePane.setCache(true);
    	tilePane.setCacheHint(CacheHint.SPEED);
    	tilePane.setVgap(10);
    	tilePane.setHgap(10);
    	tilePane.setId("tilepane");
    	tilePane.prefWidthProperty().bind(scrollpane.widthProperty());
    	tilePane.prefHeightProperty().bind(scrollpane.heightProperty());
    	tilePane.setPrefTileWidth(132);
    	tilePane.setPrefTileHeight(132);
    	
    	background = new Background(new BackgroundImage(backgroundImg, 
    						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
    						BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
    	createView(main.getMusicLibrary());
    	tilePane.addEventHandler(MouseEvent.ANY, (event)->{
    			if(event.isPrimaryButtonDown() && !menu.isShowing()) {
    				Node n = (Node)event.getTarget();
    				if(n != null) {
    					main.changeMusicLibView(n.getId());
    				}    					
    			}else if(event.isSecondaryButtonDown()) {
    				contextMenu(((Node)event.getTarget()).getId(),
    							 (Node)event.getTarget(), 
    							 event.getScreenX(), event.getScreenY());
    			}
    			
    	});
    	
    }
    
    public void createView(Collection<MusicLibObj> c) {
    	if(tiles == null) {
    		tiles = FXCollections.observableArrayList();
    	}else {
    		tiles.clear();
    	}
    	for(MusicLibObj e : c) {
    		tiles.add(createTile(e.getName()));
    	}
    	tilePane.getChildren().clear();
    	tilePane.getChildren().addAll(tiles);
    	
    }
    
    private Group createTile(String name) { 
    		Group tile = new Group();
    		tile.setId(name);
    		tile.setPickOnBounds(true);
	    	VBox c = new VBox();
	    	c.setMouseTransparent(true);
	    	c.setId("tile");
	    	c.setBackground(background);
	    	Region r = new Region();
	    	Label d = new Label();
	    	d.setWrapText(true);
	    	d.setPrefWidth(128);
	    	VBox.setVgrow(r, Priority.ALWAYS);
	    	c.setAlignment(Pos.CENTER);
	    	d.setText(name);
	    	c.getChildren().addAll(r,d);
	    	c.setCache(true);
	    	tile.getChildren().add(c);
    	return tile;
    }
    
    private void contextMenu(String target, Node node, double x, double y) {
    	menu = new ContextMenu();
    	MenuItem play = new MenuItem("Play");
    	play.setOnAction(event->{
    		
    	});
    	MenuItem addPlayback = new MenuItem("Add to Playback");
    	addPlayback.setOnAction(event->{
    		main.addToPlaylist(target);
    	});
    	menu.getItems().addAll(play, addPlayback);
    	for(String s : main.getPlaylistNames()) {
	    	MenuItem item = new MenuItem("Add to " + s);
	    	item.setOnAction(event->{
	    		main.addToPlaylist(s, target);
	    	});
	        menu.getItems().add(item);
    	}
    	menu.show(node, x, y);
    }

}
