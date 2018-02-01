/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author exahexa
 * 
 * 
 */
public class AudioFile implements Serializable, MusicLibObj{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8640513031864985031L;
	
	private String name;
	private String trackNr;
	private float duration;
	private String filePath;
	private String fileName;

	private String artist;
	private String album;
	
	/**
	 * @param title
	 * @param track 
	 * @param duration
	 * @param filePath
	 * @param fileName
	 */
	public AudioFile(String name, String trackNr, float duration, String filePath,
							String fileName, String artist, String album) {
		if( name != null && trackNr != null && filePath != null && fileName != null &&
			artist != null && album != null && !name.isEmpty() &&
			!filePath.isEmpty() && !fileName.isEmpty() && !artist.isEmpty()
													   && !album.isEmpty()) {
			this.name = name;
			this.trackNr = trackNr;
			this.duration = duration;
			this.filePath = filePath;
			this.fileName = fileName;
			this.artist = artist;
			this.album = album;
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param title
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
	
	public String getDurationFormat() {
		int sec = (int)(duration/1000)%60;
		int min = (int)(duration/(60*1000))%60;
		int hour = (int)(duration/(1000*3600));
		String ret = "";
		
		if(min < 10) {
			ret += 0+""+min+":";
		}else {
			ret += min+":";
		}
		
		if(sec < 10) {
			ret += 0+""+sec;
		}else {
			ret += sec;
		}
		if(hour > 0) {
			ret = hour+":"+ret;
		}
		return ret;
	}
	
	/**
	 * @return the artistKey
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artistKey the artistKey to set
	 */
	public void setArtistKey(String artist) {
		this.artist = artist;
	}

	/**
	 * @return the albumKey
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @param albumKey the albumKey to set
	 */
	public void setAlbumKey(String album) {
		this.album = album;
	}
	
	/**
	 * @see 
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof AudioFile)
				&& (this.name.equals( ((AudioFile)obj).getName() ))
				&& (this.filePath.equals( ((AudioFile)obj).getFilePath() ))
				&& (this.fileName.equals( ((AudioFile)obj).getFileName() ))
				&& (this.trackNr == ((AudioFile)obj).getTrackNr() )
				&& (this.duration == ((AudioFile)obj).getDuration() );
	}

	@Override
	public void add(MusicLibObj e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(MusicLibObj e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(MusicLibObj e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<MusicLibObj> getValues() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isLeaf() {
		return true;
	}
	

}
