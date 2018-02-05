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
	 * Constructs a new Playlist whose name is specified by the argument 
	 * of the same name
	 * @param name 
	 */
	public Playlist(String name) {
		this.name = name;
		playlist = new ArrayList<MusicLibObj>();
		indexLastPlayed = 0;
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
	 * Returns the index of the last played element
	 * @return the index of the last played element 
	 */
	public int getIndexLastPlayed() {
		return indexLastPlayed;
	}
	
	/**
	 * Returns the next element 
	 * @return the next element
	 */
	public MusicLibObj getNext() {
		return getAudioFileByIndex((indexLastPlayed +1));
	}
	
	/**
	 * Returns the element at the specified position in this list
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 */
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
	
	/**
	 * Returns an ArrayList that contains MusicLibObj in this Playlist
	 * @return
	 */
	public ArrayList<MusicLibObj> getPlaylist(){
		return playlist;
	}


}
