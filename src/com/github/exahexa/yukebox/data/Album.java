/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.Serializable;
import java.util.Collection;

import com.google.common.collect.HashMultimap;

/**
 * @author exahexa
 *
 */
public class Album implements Serializable, MusicLibObj{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4801091934373896376L;
	
	private String name;
	private int releaseDate;
	private HashMultimap<String, MusicLibObj> tracks;
	
	private String artist;
	
	//private int initCapacity = 19;
	
	public Album(String name, String artist) {
		if(name != null && !name.isEmpty() 
						&& artist != null && !artist.isEmpty()) {
			this.name = name;
			this.artist = artist;
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
			this.artist = artist;
			
		}
		else {
			throw new IllegalArgumentException();
		}
		this.tracks = HashMultimap.create();
	}
	
	@Override
	public void add(MusicLibObj e) {
		if(e != null) {
			if(!contains(e)) {
				this.tracks.put(e.getName().toLowerCase(), e);
			}
			else {
				throw new ElementAlreadyExistsException();
			}
			
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}

	@Override
	public void delete(MusicLibObj e) {
		tracks.remove(e.getName().toLowerCase(), e);
		
	}

	@Override
	public boolean contains(MusicLibObj e) {
		return tracks.containsEntry(e.getName().toLowerCase(), e);
	}

	@Override
	public boolean containsKey(String key) {
		return !key.isEmpty() && tracks.containsKey(key.toLowerCase());
	}
	
	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public Collection<MusicLibObj> getValues() {
		return tracks.values();
	}
	/**
	 * 
	 * @return
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @param name
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMultimap<String, MusicLibObj> getTracks(){
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
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artistKey the artistKey to set
	 */
	public void setArtist(String artistKey) {
		this.artist = artistKey;
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
