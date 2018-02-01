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
	
	HashMap<String, Playlist> playlists;
	Stack<MusicLibObj> lastPlayed;
	String currentPlaylist;
	
	/**
	 * 
	 */
	public PlaylistManager() {
		playlists = new HashMap<String, Playlist>();
		currentPlaylist = "currentplayback";
		createPlaylist("currentplayback");
		lastPlayed = new Stack<MusicLibObj>();
	}
	
	public void createPlaylist(String name) {
		playlists.put(name.toLowerCase(), new Playlist(name));
	}
	
	public void clearPlaylist(String playlistName) {
		playlists.get(playlistName.toLowerCase()).getPlaylist().clear();
	}
	
	public void addToPlaylist(MusicLibObj e) {
		addToPlaylist("currentplayback", e);		
	}
	
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
	
	public AudioFile getNext() {
		if(playlists.containsKey(currentPlaylist)) {
			addToPreviousPlayed(playlists.get(currentPlaylist).getNext());
			return (AudioFile)playlists.get(currentPlaylist).getNext();
		}
		else {
			throw new IllegalArgumentException("test");
		}
	}
	
	public AudioFile getPrevious() {
		if(!lastPlayed.empty()) {
			return (AudioFile)lastPlayed.pop();
		}
		else {
			throw new IllegalArgumentException("bla");
		}
	}
	
	private void addToPreviousPlayed(MusicLibObj audioFile) {
		lastPlayed.push(audioFile);
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
