/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

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
public class FileVisitor extends SimpleFileVisitor<Path> {

	private FileInputStream inStream;
	private Metadata metadata = new Metadata();
	private Parser parser = new Mp3Parser();
	
	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#postVisitDirectory(java.lang.Object, java.io.IOException)
	 */
	@Override
	public FileVisitResult postVisitDirectory(Path arg0, IOException arg1) throws IOException {
		// TODO Auto-generated method stub
		return super.postVisitDirectory(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#preVisitDirectory(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	 */
	@Override
	public FileVisitResult preVisitDirectory(Path arg0, BasicFileAttributes arg1) throws IOException {
		// TODO Auto-generated method stub
		return FileVisitResult.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFile(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	 */
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
		if (file.toString().endsWith(".mp3")) {;
			try { 
				parse(file.toFile()); 
			}catch(Exception e) {
				e.printStackTrace();
			};
		}
		return FileVisitResult.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFileFailed(java.lang.Object, java.io.IOException)
	 */
	@Override
	public FileVisitResult visitFileFailed(Path arg0, IOException arg1) throws IOException {
		// TODO Auto-generated method stub
		return super.visitFileFailed(arg0, arg1);
	}
	
	private Artist artist;
	private Album album;
	private AudioFile track;
	
	private String title;
	private String trackNr;
	private String artistName;
	private String albumName;
	private float duration;
	
	public void parse(File f) throws FileNotFoundException, IOException, 
												SAXException, TikaException {
		
		inStream = new FileInputStream(f);
		parser.parse(inStream, new DefaultHandler(), metadata, new ParseContext());
		inStream.close();
		
		title = metadata.get("title");
		trackNr = metadata.get(XMPDM.TRACK_NUMBER);
		artistName = metadata.get(XMPDM.ARTIST);
		albumName = metadata.get(XMPDM.ALBUM);
		duration = Float.parseFloat(metadata.get(XMPDM.DURATION));
		
		if(title.isEmpty()) {
			title = f.getName();
		}
		
		if(artistName.isEmpty()) {
			artistName = "Unknown";
		}
		
		if(albumName.isEmpty()) {
			albumName = "Unknown";
		}
		
		System.out.println(f.getAbsolutePath());
		if(artist == null || !MusicDB.getInstance().containsArtistByKey(artistName)) {
			artist = new Artist(artistName);
			MusicDB.getInstance().addArtist(artist);
		}
		
		if(album == null || !album.getName().equals(albumName)) {
			if(metadata.get(XMPDM.RELEASE_DATE).isEmpty()) {
				album = new Album(albumName, artistName);
			}
			else {
				album = new Album(albumName, artistName, 
						  Integer.parseInt(metadata.get(XMPDM.RELEASE_DATE)));
			}
			MusicDB.getInstance().addAlbum(album);			
		}

		track = new AudioFile(title, trackNr, duration,
							  f.getAbsolutePath(), f.getName(),
							  artistName, albumName);
		MusicDB.getInstance().addTrack(track);		
		
	}

}
