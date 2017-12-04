/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.util.LinkedList;

/**
 * @author exahexa
 *
 */
public class Artist {
	
	//private int id;
	private String name;
	private LinkedList<Album> albumList;
	
	public Artist(String name) {
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
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public LinkedList<Album> getAlbumList(){
		return this.albumList;
	}
	
	public void addAlbum(Album album) {
		if(album != null) {
			if(!containsAlbum(album)) {
				this.albumList.add(album);
			}
			else {
				throw new ElementAlreadyExistsException();
			}
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public boolean containsAlbum(Album album) {
		return this.albumList.contains(album);
	}
	
	public void deleteAlbum(Album album) {
		this.albumList.remove(album);
	}
	
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof Artist)
				&& (this.name.equals( ((Artist)obj).getName() ))
				&& (this.albumList.equals( ((Artist)obj).getAlbumList() ));
	}

}
