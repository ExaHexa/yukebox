/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.Serializable;

/**
 * @author exahexa
 * 
 */
public class AudioFile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8640513031864985031L;
	
	private String title;
	private String trackNr;
	private float duration;
	private String filePath;
	private String fileName;
	
	private String artistKey;
	private String albumKey;
	
	/**
	 * @param title
	 * @param track 
	 * @param duration
	 * @param filePath
	 * @param fileName
	 */
	public AudioFile(String title, String trackNr, float duration, String filePath,
							String fileName, String artist, String album) {
		if( title != null && trackNr != null && filePath != null && fileName != null &&
			artist != null && album != null && !title.isEmpty() &&
			!filePath.isEmpty() && !fileName.isEmpty() && !artist.isEmpty()
													   && !album.isEmpty()) {
			this.title = title;
			this.trackNr = trackNr;
			this.duration = duration;
			this.filePath = filePath;
			this.fileName = fileName;
			this.artistKey = artist.toLowerCase();
			this.albumKey = album.toLowerCase();
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		if(title != null && !title.isEmpty()) {
			this.title = title;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTrackNr() {
		return trackNr;
	}

	/**
	 * 
	 * @param track
	 */
	public void setTrackNr(String trackNr) {
		this.trackNr = trackNr;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * 
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		if(filePath != null && !filePath.isEmpty()) {
			this.filePath = filePath;
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		if(fileName != null && !fileName.isEmpty()) {
			this.fileName = fileName;
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int getDuration() {
		return (int)duration;
	}
	
	/**
	 * @return the artistKey
	 */
	public String getArtistKey() {
		return artistKey;
	}

	/**
	 * @param artistKey the artistKey to set
	 */
	public void setArtistKey(String artistKey) {
		this.artistKey = artistKey.toLowerCase();
	}

	/**
	 * @return the albumKey
	 */
	public String getAlbumKey() {
		return albumKey;
	}

	/**
	 * @param albumKey the albumKey to set
	 */
	public void setAlbumKey(String albumKey) {
		this.albumKey = albumKey.toLowerCase();
	}
	
	/**
	 * @see 
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof AudioFile)
				&& (this.title.equals( ((AudioFile)obj).getTitle() ))
				&& (this.filePath.equals( ((AudioFile)obj).getFilePath() ))
				&& (this.fileName.equals( ((AudioFile)obj).getFileName() ))
				&& (this.trackNr == ((AudioFile)obj).getTrackNr() )
				&& (this.duration == ((AudioFile)obj).getDuration() );
	}
	

}
