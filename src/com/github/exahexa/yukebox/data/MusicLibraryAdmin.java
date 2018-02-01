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
import java.nio.file.Path;
import java.util.Collection;
import java.util.TreeMap;

import javafx.scene.media.Media;


/**
 * @author exahexa
 *
 */
public class MusicLibraryAdmin {
	
	private TreeMap<String, MusicLibObj> artists;
	private TreeMap<String, MusicLibObj> albums;
	private String filePath = "resources/db/music.db";
	
	
	public MusicLibraryAdmin() {
		artists = new TreeMap<String, MusicLibObj>();
	}
	
	public void loadMusicLib() throws ClassNotFoundException, IOException {
		deserialize();
	}
	
	
	public void parse(Path p) throws IOException{
		new FileParser(p , artists);	
		serialize();
	}
	
	public void parse(Path p, ProgressListener pL) throws IOException{
		new FileParser(p , artists, pL);	
		serialize();
	}
	
	public TreeMap<String, MusicLibObj> getMusicLibraryTree(){
		return artists;
	}
	
	public Collection<MusicLibObj> getMusicLibrary(){
		return artists.values();
	}
	
	public Collection<MusicLibObj> getAlbums(){
		if(albums == null) {
			createAlbumMap();
		}		
		return albums.values();
	}
	
	public boolean containsKey(String key) {
		key = key.toLowerCase();
		if(albums == null) {
			createAlbumMap();
		}
		return (artists.containsKey(key) || albums.containsKey(key));
	}
	
	public boolean isAlbum(String key) {
		key = key.toLowerCase();
		if(albums == null) {
			createAlbumMap();
		}
		return albums.containsKey(key);
	}
	
	public MusicLibObj getByName(String key){
		key = key.toLowerCase();
		if(artists.containsKey(key)) {
			return artists.get(key);
		}
		else {
			if(albums == null) {
				createAlbumMap();
			}
			if(albums.containsKey(key)) {
				return albums.get(key);
			}
		}
		return null;
	}
	
	public Collection<MusicLibObj> getCollectionByName(String key){
		key = key.toLowerCase();
		if(artists.containsKey(key)) {
			return artists.get(key).getValues();
		}
		else {
			if(albums == null) {
				createAlbumMap();
			}
			if(albums.containsKey(key)) {
				return albums.get(key).getValues();
			}
		}
		return null;
	}
	
	private void createAlbumMap() {
		albums = new TreeMap<String, MusicLibObj>();
		for(MusicLibObj e : artists.values()) {
			for(MusicLibObj album : e.getValues()) {
				if(album instanceof Album) {
					albums.put(album.getName().toLowerCase(), album);
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
				artists = (TreeMap<String, MusicLibObj>) ois.readObject();
			}
		}else {
			throw new FileNotFoundException();
		}
				
	}
		
	

}
