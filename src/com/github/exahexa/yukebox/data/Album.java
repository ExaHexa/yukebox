/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.Serializable;

import com.google.common.collect.HashMultimap;

/**
 * @author exahexa
 *
 */
public class Album implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4801091934373896376L;
	
	private String name;
	private int releaseDate;
	private HashMultimap<String, AudioFile> tracks;
	
	private String artistKey;
	
	//private int initCapacity = 19;
	
	
	public Album(String name, String artist) {
		if(name != null && !name.isEmpty() 
						&& artist != null && !artist.isEmpty()) {
			this.name = name;
			this.artistKey = artist.toLowerCase();
		}
		else {
			throw new IllegalArgumentException();
		}
		this.tracks = HashMultimap.create();
	}
	
	/**
	 * @param name
	 * @param releaseData
	 */
	public Album(String name, String artist, int releaseDate) {
		if(name != null && !name.isEmpty()
						&& artist != null && !artist.isEmpty()) {
			this.name = name;
			this.releaseDate = releaseDate;
			this.artistKey = artist.toLowerCase();
			
		}
		else {
			throw new IllegalArgumentException();
		}
		this.tracks = HashMultimap.create();
	}
	
	
	/**
	 * 
	 * @param track
	 */
	public void addTrack(AudioFile track) {
		if(track != null) {
			if(!containsTrack(track)) {
				this.tracks.put(track.getTitle().toLowerCase(), track);
			}
			else {
				throw new ElementAlreadyExistsException();
			}
			
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 
	 * @param track
	 * @return
	 */
	public boolean containsTrack(AudioFile track) {
		return !tracks.isEmpty() && tracks.containsEntry(
									 track.getTitle().toLowerCase(), track);
	}
	
	/**
	 * 
	 * @param track
	 */
	public void deleteTrack(AudioFile track) {
		this.tracks.remove(track.getTitle().toLowerCase(), track);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMultimap<String, AudioFile> getTracks(){
		return this.tracks;
	}
	
	/**
	 * @return the releaseDate
	 */
	public int getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(int releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the artistKey
	 */
	public String getArtistKey() {
		return artistKey;
	}

	/**
	 * @param artistKey the artistKey to set
	 */
	public void setArtistKey(String artistKey) {
		this.artistKey = artistKey;
	}
	
	/**
	 * @see 
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof Album)
				&& (this.name.equals( ((Album)obj).getName() ))
				&& (this.tracks.equals( ((Album)obj).getTracks() ));
	}

}
