/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;


/**
 * @author exahexa
 *
 */
public class MusicLibraryAdmin {
	
	private HashMap<String, Artist> artists;
	private int initCapacity = 167;
	private String filePath = "resources/db/music.db";
	
	public MusicLibraryAdmin() {
		artists = new HashMap<String, Artist>(initCapacity);
		try {
			deserialize();
		}catch(IOException e) {
			System.out.println("file not found");
			try {
				parse();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}catch(ClassNotFoundException e) {
			System.out.println("class not found");
		}
	}
	
	public void addArtist(Artist artist) {
		if(artist != null) {
			if(!containsArtist(artist)) {
				artists.put(artist.getName().toLowerCase(), artist);
			}
			else {
				throw new ElementAlreadyExistsException();
			}
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public void addAlbum(Album album) {
		artists.get(album.getArtist()).addAlbum(album);
	}
	
	public void addTrack(AudioFile track) {
		artists.get(track.getArtist()).getAlbums().get(
					track.getAlbum()).addTrack(track);
	}
	
	public boolean containsArtist(Artist artist) {
		return !artists.isEmpty() && artists.containsKey(
									 artist.getName().toLowerCase());
	}
	
	public boolean containsArtistByKey(String artistName) {
		return !artistName.isEmpty() && artists.containsKey(
										artistName.toLowerCase());
	}
	
	public boolean containsAlbumByKey(String artistName, String albumName) {
		return !artistName.isEmpty() && !albumName.isEmpty()
				&& artists.get(artistName.toLowerCase())
								.containsAlbumByKey(albumName);
	}
	
	
	
	public void parse() throws IOException{
		new FileParser("/home/exahexa/Music/Music/", artists);	
		serialize();
	}
	
	public HashMap<String, Artist> getMusicLibrary(){
		return artists;
	}
	
	public void dbOutput() {
		for(Artist value : artists.values()) {
			System.out.println(value.getName());
			for(Album val : value.getAlbums().values()) {
				System.out.println("	" +val.getName());
				for(AudioFile v : val.getTracks().values()) {
					System.out.println("		" + v.getName());
				}
			}
		}
	}
	

	private void serialize() throws IOException{
		try(ObjectOutputStream oos = new ObjectOutputStream(
									 new FileOutputStream(filePath));){
			oos.writeObject(artists);	
		}
	}
	
	@SuppressWarnings("unchecked")
	private void deserialize() throws IOException, ClassNotFoundException{
		File f = new File(filePath);
		if(f.exists()) {
			try(ObjectInputStream ois = new ObjectInputStream(
										new FileInputStream(filePath));){
				artists = (HashMap<String, Artist>) ois.readObject();
			}
		}else{
			throw new IOException();
		}
				
	}
		
	

}
