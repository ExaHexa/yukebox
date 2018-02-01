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
	 * 
	 * @param name
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
	
	@Override
	public void add(MusicLibObj e) {
		if(e != null) {
			if(!contains(e)) {
				albums.put(e.getName().toLowerCase(), e);
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
		albums.remove(e.getName().toLowerCase(), e);
		
	}


	@Override
	public boolean contains(MusicLibObj e) {
		return albums.containsKey(e.getName().toLowerCase());
	}


	@Override
	public boolean containsKey(String key) {
		return !key.isEmpty() && albums.containsKey(key.toLowerCase());
	}


	@Override
	public Collection<MusicLibObj> getValues() {
		return albums.values();
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
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
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
	 * @see
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof Artist)
				&& (this.name.equals( ((Artist)obj).getName() ))
				&& (this.albums.equals( ((Artist)obj).getAlbums() ));
	}


	


}
