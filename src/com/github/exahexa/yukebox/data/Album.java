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
 * Abstract representation of an album. The class implements Serializable 
 * and MusicLibObj. Album contains the album name, the optional release data, 
 * a MusicLibObj multimap of its track and the name of the artist that 
 * contains this album.
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
	
	/**
	 * Constructs a new Album specified by the arguments name and artist
	 * @param name the name of this Album
	 * @param artist the name of the Artist that contains this Album
	 * 
	 */
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
	 * Constructs a new Album specified by the given arguments
	 * @param name the name of this Album
	 * @param artist the name of the Artist that contains this Album
	 * @param releaseDate the release data of this Album
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
	
	/**
	 * @see MusicLibObj#add(MusicLibObj)
	 */
	@Override
	public void add(MusicLibObj e) {
		if(e != null && !contains(e)) {
				this.tracks.put(e.getName().toLowerCase(), e);
		}
		
	}
	
	/**
	 * @see MusicLibObj#delete(MusicLibObj)
	 */
	@Override
	public void delete(MusicLibObj e) {
		tracks.remove(e.getName().toLowerCase(), e);
		
	}
	
	/**
	 * @see MusicLibObj#contains(MusicLibObj)
	 */
	@Override
	public boolean contains(MusicLibObj e) {
		return tracks.containsEntry(e.getName().toLowerCase(), e);
	}
	
	/**
	 * @see MusicLibObj#containsKey(String)
	 */
	@Override
	public boolean containsKey(String key) {
		return !key.isEmpty() && tracks.containsKey(key.toLowerCase());
	}
	
	/**
	 * @see MusicLibObj#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return false;
	}
	
	/**
	 * @see MusicLibObj#getValues()
	 */
	@Override
	public Collection<MusicLibObj> getValues() {
		return tracks.values();
	}
	
	/**
	 * @see MusicLibObj#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * @see MusicLibObj#setName(String)
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
	 * Returns the release date of this Album
	 * @return the release date of this Album
	 */
	public int getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Sets the release date of this Album
	 * @param releaseDate the release date to set 
	 */
	public void setReleaseDate(int releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * Returns the name of the Artist that contains this Album
	 * @return the name of the Artist that contains this Album
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Sets the name of the Artist that contains this Album
	 * @param artist the name of the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof Album)
				&& (this.name.equals( ((Album)obj).getName() ))
				&& (this.tracks.equals( ((Album)obj).getTracks() ));
	}



}
