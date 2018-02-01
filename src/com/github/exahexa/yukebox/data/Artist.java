/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.TreeMap;

/**
 * @author exahexa
 * 
 * Abstract representation if an artist. This class implements 
 * Serializable and MusicLibObj. Artist contains a map of MusicLibObj
 * that should be instance of Album, and the name of the artist.
 * 
 */
public class Artist implements Serializable, MusicLibObj{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1612965854542176473L;
	
	private String name;
	//private HashMultimap<String, Album> albums;
	private TreeMap<String, MusicLibObj> albums;
		
	/**
	 * Constructs a new Artist specified by the argument name 
	 * @param name the name of the artist
	 */
	public Artist(String name) {
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}
		else {
			throw new IllegalArgumentException();
		}
		this.albums = new TreeMap<String, MusicLibObj>();
		//this.albums = HashMultimap.create();
	}
	
	/**
	 * @see MusicLibObj#add(MusicLibObj)
	 * The element to add can not be null 
	 */
	@Override
	public void add(MusicLibObj e) {
		if(e != null && !contains(e)) {
			albums.put(e.getName().toLowerCase(), e);
		}
		
	}

	/**
	 * @see MusicLibObj#delete(MusicLibObj)
	 * 
	 */
	@Override
	public void delete(MusicLibObj e) {
		albums.remove(e.getName().toLowerCase(), e);
		
	}

	/**
	 * @see MusicLibObj#contains(MusicLibObj)
	 * 
	 */
	@Override
	public boolean contains(MusicLibObj e) {
		return albums.containsKey(e.getName().toLowerCase());
	}

	/*
	 * (non-Javadoc)
	 * @see com.github.exahexa.yukebox.data.MusicLibObj#containsKey(java.lang.String)
	 */
	@Override
	public boolean containsKey(String key) {
		return !key.isEmpty() && albums.containsKey(key.toLowerCase());
	}

	/**
	 * @see MusicLibObj#getValues()
	 */
	@Override
	public Collection<MusicLibObj> getValues() {
		return albums.values();
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
	 * @param name can not be null or empty
	 */
	@Override
	public void setName(String name) {
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * @see MusicLibObj#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public TreeMap<String, MusicLibObj> getAlbums(){
		return this.albums;
	}

	/**
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof Artist)
				&& (this.name.equals( ((Artist)obj).getName() ))
				&& (this.albums.equals( ((Artist)obj).getAlbums() ));
	}


	


}
