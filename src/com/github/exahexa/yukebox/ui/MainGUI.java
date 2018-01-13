/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.github.exahexa.yukebox.data.Album;
import com.github.exahexa.yukebox.data.Artist;
import com.github.exahexa.yukebox.data.AudioFile;
import com.github.exahexa.yukebox.data.MusicLibraryAdmin;
import com.github.exahexa.yukebox.data.PlaylistManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author exahexa
 *
 */
public class MainGUI extends Application {
	
	private PlaylistManager playLAdm;
	private MusicLibraryAdmin musicLibAdm;
	private Stage stage;
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		playLAdm = new PlaylistManager();
		musicLibAdm = new MusicLibraryAdmin();
		stage = primaryStage;
		stage.setTitle("YukeBox");
		stage.setScene(swScene());
		stage.show();
	}
	
	public HashMap<String, Artist> getMusicLibrary(){
		return musicLibAdm.getMusicLibrary();
	}
	
	public void addArtist(Artist artist) {
		musicLibAdm.addArtist(artist);
	}
	
	public void addAlbum(Album album) {
		musicLibAdm.addAlbum(album);
	}
	
	public void addTrack(AudioFile track) {
		musicLibAdm.addTrack(track);
	}
	
	public boolean containsArtist(Artist artist) {
		return musicLibAdm.containsArtist(artist);
	}
	
	public boolean containsArtistByKey(String artistName) {
		return musicLibAdm.containsArtistByKey(artistName);
	}
	
	public boolean containsAlbumByKey(String artistName, String albumName) {
		return musicLibAdm.containsAlbumByKey(artistName, albumName);
	}
	
	public void createPlaylist(String name) {
		playLAdm.createPlaylist(name);
	}
	
	public void addToPlaylist(String name, AudioFile track) {
		playLAdm.addToPlaylist(name, track);
	}
	
	public ArrayList<AudioFile> getPlaylist(String name) {
		return playLAdm.getPlaylist(name);
	}
	
	
	
	private Scene swScene() throws IOException{
	    FXMLLoader loader = new FXMLLoader(getClass().
				                  getResource("MainScene.fxml"));
	    loader.setController(new MainSceneController(this));
	    return new Scene(loader.load());		
	}
	
	


}
