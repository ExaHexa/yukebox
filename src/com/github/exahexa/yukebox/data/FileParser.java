/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.XMPDM;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author exahexa
 *
 */
public class FileParser {
	
	private String rootDir;
	
	private ArrayList<Path> files = new ArrayList<Path>();
	private HashMap<String, Artist> musicLibrary;
	
	/**
	 * 
	 * @param rootDir
	 * @param musicLibrary
	 * @throws IOException
	 */
	public FileParser(String rootDir, HashMap<String, Artist> musicLibrary) throws IOException{
		this.musicLibrary = musicLibrary;
		this.rootDir = rootDir;
		findFiles();
		readFiles();
	}
	
	/**
	 * list all .mp3 files in the directory and all 
	 * sub directories
	 * @throws IOException
	 */
	private void findFiles() throws IOException{
		File f = new File(rootDir);
		Files.walkFileTree(f.toPath(), new FileVisitor(files));
	}
	
	private void readFiles() throws IOException{
		for(int i = 0; i < files.size(); i++) {
			try {
				parse(files.get(i).toFile());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TikaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private Metadata metadata = new Metadata();
	private DefaultHandler defaultHandler = new DefaultHandler();
	private Parser parser = new Mp3Parser();
	
	private Artist artist;
	private Album album;
	private AudioFile track;
	
	private String title;
	private String trackNr;
	private String artistName;
	private String albumName;
	private float duration;
	private int releaseDate;
	
	private String albumKey;
	private String artistKey;
	
	/**
	 * 
	 * @param f File to parse
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public void parse(File f) throws FileNotFoundException, IOException, 
												SAXException, TikaException {
		
		try(FileInputStream fin = new FileInputStream(f)){
			parser.parse(fin, defaultHandler, metadata, new ParseContext());	
		}
		
		title = metadata.get("title");
		trackNr = metadata.get(XMPDM.TRACK_NUMBER);
		artistName = metadata.get(XMPDM.ARTIST);
		albumName = metadata.get(XMPDM.ALBUM);
		try {
			duration = Float.parseFloat(metadata.get(XMPDM.DURATION));
		}catch(NumberFormatException e) {
			
		}
		
		try {
			releaseDate = Integer.parseInt(metadata.get(XMPDM.RELEASE_DATE));
		}catch(NumberFormatException e) {
			releaseDate = -1;
		}
		
		
		if(title.isEmpty()) {
			title = f.getName();
		}
		
		if(artistName.isEmpty()) {
			artistName = "Unknown";
			artistKey = artistName.toLowerCase();
		}
		
		if(albumName.isEmpty()) {
			albumName = "Unknown";
			albumKey = albumName.toLowerCase();
		}
		artistKey = artistName.toLowerCase();
		albumKey = albumName.toLowerCase();
		
		System.out.println(f.getAbsolutePath());
		
		//checks if artist already exists in database if not 
		//create new artist
		if(!musicLibrary.containsKey(artistKey) ){
			artist = new Artist(artistName);
			musicLibrary.put(artistKey, artist);
		}
		
		//check if album already exists in database if not 
		//create new album
		if(!musicLibrary.get(artistKey).containsAlbumByKey(albumName)) {
			if(releaseDate != -1) {
				album = new Album(albumName, artistName, releaseDate);
			}
			else {
				album = new Album(albumName, artistName);
			}			
			musicLibrary.get(artistKey).addAlbum(album);	
		}
		
		//add track to database
		track = new AudioFile(title, trackNr, duration,
							  f.getAbsolutePath(), f.getName(),
							  artistName, albumName);
		musicLibrary.get(artistKey).getAlbums()
					.get(albumKey).addTrack(track);
		
	}
	

}
