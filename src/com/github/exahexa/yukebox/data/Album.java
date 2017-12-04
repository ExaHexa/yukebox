/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.util.LinkedList;

/**
 * @author exahexa
 *
 */
public class Album {
	
	//private int id;
	private String name;
	private LinkedList<AudioFile> trackList;

	public Album(String name) {
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public LinkedList<AudioFile> getTrackList(){
		return this.trackList;
	}
	
	public void addTrack(AudioFile track) {
		if(track != null) {
			if(!containsTrack(track)) {
				this.trackList.add(track);
			}
			else {
				throw new ElementAlreadyExistsException();
			}
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	public boolean containsTrack(AudioFile track) {
		return this.trackList.contains(track);
	}
	
	public void deleteTrack(AudioFile track) {
		this.trackList.remove(track);
	}
	
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof Album)
				&& (this.name.equals( ((Album)obj).getName() ))
				&& (this.trackList.equals( ((Album)obj).getTrackList() ));
	}
}
