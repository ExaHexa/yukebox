/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


/**
 * @author exahexa
 *
 */
public class Playlist implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1275669474559668527L;
	
	private String name;
	private ArrayList<MusicLibObj> playlist;
	private int indexLastPlayed;

	/**
	 * 
	 */
	public Playlist(String name) {
		this.name = name;
		playlist = new ArrayList<MusicLibObj>();
		indexLastPlayed = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addTrack(MusicLibObj track) {
		playlist.add(track);
	}
	
	public void addCollection(Collection<MusicLibObj> collectionTrack) {
		playlist.addAll(collectionTrack);
	}
	
	public int getPlaylistSize() {
		return playlist.size();
	}
	
	public int getIndexLastPlayed() {
		return indexLastPlayed;
	}
	
	public MusicLibObj getNext() {
		return getAudioFileByIndex((indexLastPlayed +1));
	}
	
	public MusicLibObj getAudioFileByIndex(int index) {
		if(index < playlist.size()) {
			indexLastPlayed = index;
			return playlist.get(index);
		}
		else {
			indexLastPlayed = 0;
			return playlist.get(0);
		}
	}
	
	public ArrayList<MusicLibObj> getPlaylist(){
		return playlist;
	}


}
