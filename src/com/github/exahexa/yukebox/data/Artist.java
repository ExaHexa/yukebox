/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author exahexa
 *
 */
public class Artist implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1612965854542176473L;
	
	private String name;
	//private HashMultimap<String, Album> albums;
	private HashMap<String, Album> albums;
		
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
		this.albums = new HashMap<String, Album>();
		//this.albums = HashMultimap.create();
	}
	
	
	/**
	 * 
	 * @param album
	 */
	public void addAlbum(Album album) {
		if(album != null) {
			if(!containsAlbum(album)) {
				this.albums.put(album.getName().toLowerCase(), album);
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
	 * @param album
	 * @return
	 */
	public boolean containsAlbum(Album album) {
		return !albums.isEmpty() && albums.containsKey(
						album.getName().toLowerCase());
	}
	
	public boolean containsAlbumByKey(String albumName) {
		return !albumName.isEmpty() && albums.containsKey(albumName.toLowerCase());
	}
	
	/**
	 * 
	 * @param album
	 */
	public void deleteAlbum(Album album) {
		this.albums.remove(album.getName().toLowerCase(), album);
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
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<String, Album> getAlbums(){
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
