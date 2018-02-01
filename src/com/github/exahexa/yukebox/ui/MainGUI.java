/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

import com.github.exahexa.yukebox.data.AudioFile;
import com.github.exahexa.yukebox.data.FileNotFoundException;
import com.github.exahexa.yukebox.data.MusicLibObj;
import com.github.exahexa.yukebox.data.MusicLibraryAdmin;
import com.github.exahexa.yukebox.data.PlaylistManager;
import com.github.exahexa.yukebox.data.ProgressListener;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author exahexa
 *
 */
public class MainGUI extends Application {
	
	private PlaylistManager playLAdm;
	private MusicLibraryAdmin musicLibAdm;
	private Stage stage;
	private BorderPane rootLayout;
	private AnchorPane tileViewPane;
	private AnchorPane tableViewPane;
	private TopController topCtrl;
	private TileViewController tileViewCtrl;
	private TableViewController tableViewCtrl;
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		playLAdm = new PlaylistManager();
		musicLibAdm = new MusicLibraryAdmin();
		stage = primaryStage;
		stage.setTitle("YukeBox");		
		stage.setScene(initRootLayout());
		initPlayer();
		initNavigation();
		initTileView();
		initTableView();
		initTop();
		rootLayout.setCenter(tileViewPane);
		try {
			musicLibAdm.loadMusicLib();
			tileViewCtrl.createView(musicLibAdm.getMusicLibrary());
		}catch(FileNotFoundException e) {
			addToLibView();
		}
		
		
		
		stage.show();
	}
	
	/**
	 * 
	 * @return
	 */
	public Collection<MusicLibObj> getMusicLibrary(){
		return musicLibAdm.getMusicLibrary();
	}
	
	public void parse(Path p, ProgressListener pL) throws IOException{
		musicLibAdm.parse(p, pL);
	}
	
	/**
	 * 
	 * @param value
	 */
	public void changeMusicLibView(String value) {
		if(value != null) {
			if(value.equals("artists")) {
				topCtrl.addPath(value);
				rootLayout.setCenter(tileViewPane);
				tileViewCtrl.createView(musicLibAdm.getMusicLibrary());
			}else if(value.equals("albums")) {
				topCtrl.addPath(value);
				rootLayout.setCenter(tileViewPane);
				tileViewCtrl.createView(musicLibAdm.getAlbums());
			}else if(value.equals("songs")) {
				
			}else if(musicLibAdm.containsKey(value)){
				if(musicLibAdm.isAlbum(value)) {
					topCtrl.addPath(value);
					rootLayout.setCenter(tableViewPane);
					tableViewCtrl.createView(musicLibAdm.getCollectionByName(value));					
				}else {
					topCtrl.addPath(value);
					rootLayout.setCenter(tileViewPane);
					tileViewCtrl.createView(musicLibAdm.getCollectionByName(value));	
				}
			}
		}		
	}
	
	public void changePlaylistView(String s) {
		if(rootLayout.getCenter() != tableViewPane) {
			rootLayout.setCenter(tableViewPane);
		}		
		tableViewCtrl.createView(playLAdm.getPlaylist(s));
	}
	
	public void changeAddToLibView() {
		try {
			addToLibView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param audioFile
	 * @return
	 */
	public File getFile(AudioFile audioFile) {
		return new File(audioFile.getFilePath());
	}
	
	/**
	 * 
	 * @return
	 */
	public File getNextMedia() {
		return getFile(playLAdm.getNext());
	}
	
	/**
	 * 
	 * @return
	 */
	public File getPreviousMedia() {
		return getFile(playLAdm.getPrevious());
	}
	
	/**
	 * 
	 * @param name
	 */
	public void createPlaylist(String name) {
		playLAdm.createPlaylist(name);
	}
	
	/**
	 * 
	 * @param key
	 */
	public void addToPlaylist(String key) {
		if(musicLibAdm.containsKey(key)) {
			addToPlaylist(musicLibAdm.getByName(key));
		}
	}
	
	public void addToPlaylist(String playlistName, String key) {
		if(musicLibAdm.containsKey(key)) {
			addToPlaylist(playlistName, musicLibAdm.getByName(key));
		}
	}
	
	/**
	 * 
	 * @param e
	 */
	public void addToPlaylist(MusicLibObj e) {
		playLAdm.addToPlaylist(e);
	}
	
	/**
	 * 
	 * @param name
	 * @param e
	 */
	public void addToPlaylist(String name, MusicLibObj e) {
		playLAdm.addToPlaylist(name, e);
	}
	
	/**
	 * 
	 * @return
	 */
	public Collection<String> getPlaylistNames() {
		return playLAdm.getPlaylistNames();
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private Scene initRootLayout() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().
                			getResource("root.fxml"));
		rootLayout = (BorderPane)loader.load();
		return new Scene(rootLayout);
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private void initPlayer() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().
    						getResource("player.fxml"));
		loader.setController(new PlayerController(this));
		AnchorPane playerPane = (AnchorPane)loader.load();
		playerPane.prefWidthProperty().bind(rootLayout.widthProperty());
		//playerPane.prefHeightProperty().bind(Bindings.divide(rootLayout.heightProperty(), 6));
		rootLayout.setBottom(playerPane);
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private void initNavigation() throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().
								getResource("navigation.fxml"));
		loader.setController(new NavigationController(this));
		AnchorPane naviPane = (AnchorPane)loader.load();
		naviPane.maxHeightProperty().bind(rootLayout.heightProperty());
		//naviPane.maxWidthProperty().bind(Bindings.divide(rootLayout.heightProperty(), 5));
		rootLayout.setLeft(naviPane);
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private void initMusicLibTreeView() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().
								getResource("musicLibTreeView.fxml"));
		loader.setController(new TreeViewController(this));
		AnchorPane treeViewPane = (AnchorPane)loader.load();
		treeViewPane.maxHeightProperty().bind(rootLayout.heightProperty());
		treeViewPane.maxWidthProperty().bind(rootLayout.widthProperty());

		rootLayout.setCenter(treeViewPane);
	}
	
	
	/**
	 * 
	 * @throws IOException
	 */
	private void initTileView() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().
								getResource("tileView.fxml"));
		tileViewCtrl = new TileViewController(this);
		loader.setController(tileViewCtrl);

		tileViewPane = (AnchorPane)loader.load();
		tileViewPane.maxWidthProperty().bind(rootLayout.widthProperty());
		tileViewPane.prefHeightProperty().bind(Bindings.divide(rootLayout.heightProperty(), 1.5));
	}
	

	/**
	 * 
	 * @throws IOException
	 */
	private void initTableView() throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().
					getResource("tableView.fxml"));
		tableViewCtrl = new TableViewController(this);
		loader.setController(tableViewCtrl);
		tableViewPane = (AnchorPane)loader.load();
		tableViewPane.prefHeightProperty().bind(Bindings.divide(rootLayout.heightProperty(), 1.5));
		tableViewPane.maxWidthProperty().bind(rootLayout.widthProperty());	
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private void initTop() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().
				getResource("top.fxml"));		
		topCtrl = new TopController(this);
		loader.setController(topCtrl);
		AnchorPane a = (AnchorPane)loader.load();
		a.maxWidthProperty().bind(rootLayout.widthProperty());
		a.prefHeightProperty().bind(Bindings.divide(rootLayout.heightProperty(), 8));
		rootLayout.setTop(a);
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private void addToLibView() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().
				getResource("addToLibView.fxml"));		
		loader.setController(new AddToLibraryController(this));
		AnchorPane addToLibView = (AnchorPane)loader.load();
		addToLibView.prefWidthProperty().bind(rootLayout.widthProperty());
		addToLibView.prefHeightProperty().bind(Bindings.divide(rootLayout.heightProperty(), 1.5));
		rootLayout.setCenter(addToLibView);
	}


}
