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
	private ArrayList<AudioFile> playlist;

	/**
	 * 
	 */
	public Playlist(String name) {
		this.name = name;
		playlist = new ArrayList<AudioFile>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addTrack(AudioFile track) {
		playlist.add(track);
	}
	
	public void addCollection(Collection<AudioFile> collectionTrack) {
		playlist.addAll(collectionTrack);
	}
	
	public ArrayList<AudioFile> getPlaylist(){
		return playlist;
	}


}
