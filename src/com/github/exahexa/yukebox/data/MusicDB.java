/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;


/**
 * @author exahexa
 *
 */
public class MusicDB {
	
	private static MusicDB musicDB;
	
	private HashMap<String, Artist> artists;
	private int initCapacity = 167;
	
	private MusicDB() {
		artists = new HashMap<String, Artist>(initCapacity);
	}
	
	public synchronized static MusicDB getInstance() {
		if(musicDB == null) {
			musicDB = new MusicDB();
		}
		return musicDB;
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
		artists.get(album.getArtistKey()).addAlbum(album);
	}
	
	public void addTrack(AudioFile track) {
		artists.get(track.getArtistKey()).getAlbums().get(track.getAlbumKey()).addTrack(track);
	}
	
	public boolean containsArtist(Artist artist) {
		return !artists.isEmpty() && artists.containsKey(artist.getName().toLowerCase());
	}
	
	public boolean containsArtistByKey(String artistName) {
		return !artistName.isEmpty() && artists.containsKey(artistName.toLowerCase());
	}

	
	public void parse() throws IOException{
		File f = new File("/home/exahexa/Music/Music/John Zorn/");
		Files.walkFileTree(f.toPath(), new FileVisitor());
		
		for(Artist value : artists.values()) {
			System.out.println(value.getName());
			for(Album val : value.getAlbums().values()) {
				System.out.println("	" +val.getName());
				for(AudioFile v : val.getTracks().values()) {
					System.out.println("		" + v.getTitle());
				}
			}
		}
	}
		
	

}
