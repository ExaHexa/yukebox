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

	/**
	 * Constructs a new Playlist whose name is specified by the argument 
	 * of the same name
	 * @param name 
	 */
	public Playlist(String name) {
		this.name = name;
		playlist = new ArrayList<MusicLibObj>();
	}
	
	/**
	 * Returns this Playlist name
	 * @return this Playlist name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets this Playlist name
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Adds an element to the list, specified by the argument track 
	 * @param track the MusicLibObj to add
	 */
	public void addTrack(MusicLibObj track) {
		playlist.add(track);
	}
	
	/**
	 * Adds a MusicLibObj type collection to the list, specified
	 * by the argument collectionTrack
	 * @param collectionTrack the collection to add
	 */
	public void addCollection(Collection<MusicLibObj> collectionTrack) {
		playlist.addAll(collectionTrack);
	}
	
	/**
	 * Returns the number of elements in this Playlist
	 * @return the number of elements in this Playlist
	 */
	public int getPlaylistSize() {
		return playlist.size();
	}
	
	/**
	 * Returns the element at the specified position in this list,
	 * if the index is greater than this playlist size returns the 
	 * first element
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 */
	public MusicLibObj getAudioFileByIndex(int index) {
		if(!playlist.isEmpty()) {
			if(index < playlist.size()) {
				return playlist.get(index);
			}
			else {
				return playlist.get(0);
			}
		}else {
			throw new IllegalStateException();
		}
		
	}
	
	/**
	 * Returns an ArrayList that contains MusicLibObj in this Playlist
	 * @return
	 */
	public ArrayList<MusicLibObj> getPlaylist(){
		return playlist;
	}


}
