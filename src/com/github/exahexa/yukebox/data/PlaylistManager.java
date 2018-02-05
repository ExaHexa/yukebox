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
import java.util.List;
import java.util.Stack;



/**
 * @author exahexa
 *
 */
public class PlaylistManager {
	
	private HashMap<String, Playlist> playlists;
	private Stack<MusicLibObj> lastPlayed;
	private String currentPlaylist;
	private String filePath = "resources/db/playlist.db";
	private int indexLastPlayed = 0;
	private boolean shuffle;
	private boolean repeat;
	
	/**
	 * 
	 */
	public PlaylistManager() {
		playlists = new HashMap<String, Playlist>();
		currentPlaylist = "currentplayback";
		createPlaylist("currentplayback");
		lastPlayed = new Stack<MusicLibObj>();
		shuffle = false;
		repeat = false;
	}
	
	/**
	 * Creates a new playlist with a specified name
	 * @param name the name of the playlist to create
	 */
	public void createPlaylist(String name) {
		playlists.put(name.toLowerCase(), new Playlist(name));
	}
	
	/**
	 * Removes all elements from the specified Playlist
	 * @param playlistName the name of the playlist to clear
	 */
	public void clearPlaylist(String playlistName) {
		playlists.get(playlistName.toLowerCase()).getPlaylist().clear();
	}
	
	/**
	 * Adds element to standard playlist
	 * @param e the element to add
	 */
	public void addToPlaylist(MusicLibObj e) {
		addToPlaylist("currentplayback", e);		
	}
	
	/**
	 * Adds an element to a specified playlist
	 * @param name name of the playlist
	 * @param e the element to add
	 */
	public void addToPlaylist(String name, MusicLibObj e) {
		name = name.toLowerCase();
		if(e.isLeaf()) {
			playlists.get(name).addTrack(e);
		}
		else {
			for(MusicLibObj m : e.getValues()) {
				if(m.isLeaf()) {
					playlists.get(name).addTrack(m);
				}else {
					for(MusicLibObj t : m.getValues()) {
						playlists.get(name).addTrack(t);
					}
				}
				
			}
		}
		
	}
	
	/**
	 * Returns a string collection filled with all names of the playlists
	 * @return a string collection filled with names of the playlists
	 */
	public Collection<String> getPlaylistNames(){
		Collection<String> ret = new ArrayList<String>();
		for(Playlist p : playlists.values()) {
			if(p.getName() != "currentplayback") {
				ret.add(p.getName());
			}
		}
		return ret;
		
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<MusicLibObj> getPlaylist() {
		return getPlaylist(currentPlaylist);
	}
	
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public ArrayList<MusicLibObj> getPlaylist(String name) {
		return playlists.get(name.toLowerCase()).getPlaylist();
	}
	
	
	/**
	 * Returns the next element in the list
	 * @return the next element
	 */
	public MusicLibObj getNext() {
		MusicLibObj ret;
		if(indexLastPlayed >= playlists.get(currentPlaylist).getPlaylistSize()) {
			indexLastPlayed = 0;
		}
		ret = playlists.get(currentPlaylist).getAudioFileByIndex(indexLastPlayed);
		indexLastPlayed++;
		return ret;
		
	}
	
	/**
	 * Removes the object on top of this stack and returns that object
	 * @return the object on top of this stack
	 */
	public AudioFile getPrevious() {
		if(!lastPlayed.empty()) {
			return (AudioFile)lastPlayed.pop();
		}
		else {
			throw new IllegalArgumentException("bla");
		}
	}
	
	/**
	 * Inverts the shuffle playback mode
	 */
	public void setShuffle() {
		shuffle = !shuffle;
	}
	
	/**
	 * Inverts the repeat playback mode
	 */
	public void setRepeat() {
		repeat = !repeat;
	}
	
	/**
	 * Adds an object to this object stack
	 * should be called, when the getNext() method returns an element
	 * @param audioFile the MusicLibObj to add
	 */
	private void addToPreviousPlayed(MusicLibObj audioFile) {
		lastPlayed.push(audioFile);
	}
	
	/**
	 * serializes the map that contains all Playlists
	 * @throws IOException
	 */
	private void serialize() throws IOException{
		try(ObjectOutputStream oos = new ObjectOutputStream(
									 new FileOutputStream(filePath));){
			oos.writeObject(playlists);
		}
	}
	
	/**
	 * Deserializes the map that contains all Playlists, if such a file exists
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
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
