/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author exahexa
 *
 */
public class PlaylistManager {
	
	HashMap<String, Playlist> playlists;
	
	/**
	 * 
	 */
	public PlaylistManager() {
		playlists = new HashMap<String, Playlist>();
	}
	
	public void createPlaylist(String name) {
		playlists.put(name.toLowerCase(), new Playlist(name));
	}
	
	public void addToPlaylist(String name, AudioFile track) {
		playlists.get(name.toLowerCase()).addTrack(track);
	}
	
	public void addCollectionToPlaylist(String name, Collection<AudioFile> collection) {
		playlists.get(name.toLowerCase()).addCollection(collection);
	}
	
	public ArrayList<AudioFile> getPlaylist(String name) {
		return playlists.get(name.toLowerCase()).getPlaylist();
	}
	
	String filePath = "resources/db/playlist.db";
	private void serialize() throws IOException{
		try(ObjectOutputStream oos = new ObjectOutputStream(
									 new FileOutputStream(filePath));){
			oos.writeObject(playlists);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void deserialize() throws IOException, ClassNotFoundException{
		File f = new File(filePath);
		if(f.exists()) {
			try(ObjectInputStream ois = new ObjectInputStream(
										new FileInputStream(filePath));){
				playlists = (HashMap<String, Playlist>) ois.readObject();
			}
						
		}
				
	}

}
