/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author exahexa
 * 
 * Abstract representation of an audio file. 
 * The Class AudioFile is Serializable and implements (as leaf node)
 * the MusicLibObj interface. It contains meta data from the audio file and 
 * its path in the file system.
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
	 * Constructs a new AudioFile specified by the given arguments.
	 * 
	 * @param title the title of this AudioFile
	 * @param trackNr the track number of this AudioFile
	 * @param duration the duration of this AudioFile
	 * @param filePath the system file path of this AudioFile
	 * @param fileName the file name of this AudioFile
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
	}
	
	

	
	/**
	 * Returns a string that contains the track number of this specific AudioFile
	 * @return the track number
	 */
	public String getTrackNr() {
		return trackNr;
	}

	/**
	 * Sets the track number of this specific AudioFile
	 * @param trackNr a string that contains the track number 
	 */
	public void setTrackNr(String trackNr) {
		this.trackNr = trackNr;
	}
	
	/**
	 * Returns the file path of this specific AudioFile on the file system
	 * that contains the file
	 * @return the path of the AudioFile
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * Sets the path specified by the argument of the same name
	 * @param filePath the path to set for this AudioFile
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
	 * Returns the file name of this specific AudioFile
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Sets the file name specified by the argument of the same name
	 * @param fileName the name to set this AudioFile file name
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
	 * Returns the duration of this specific AudioFile as milliseconds
	 * @return the duration as milliseconds
	 */
	public int getDuration() {
		return (int)duration;
	}
	
	/**
	 * Returns a string that contains the duration in hh:mm:ss format
	 * @return a string that contains the duration in hh:mm:ss format
	 */
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
	 * Returns the name of the Artist that contains this AudioFile
	 * @return the artist name
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Sets the artistKey of this AudioFile specified by the argument artist
	 * @param artist the name of artist to set the artist that contains this AudioFile
	 */
	public void setArtistKey(String artist) {
		this.artist = artist;
	}

	/**
	 * Returns the name of the Album that contains this AudioFile 
	 * @return the album name
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * Sets the name of the Album that contains this AudioFile 
	 * @param album specifies the name of the Album that contains this AudioFile
	 */
	public void setAlbum(String album) {
		this.album = album;
	}
	
	/**
	 * @see Object#equals(Object)
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

	/**
	 * @see MusicLibObj#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * @see MusicLibObj#setName(String) 
	 */
	@Override
	public void setName(String name) {
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}
	}

	/**
	 * @see MusicLibObj#add(MusicLibObj)
	 */
	@Override
	public void add(MusicLibObj e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see MusicLibObj#delete(MusicLibObj)
	 */
	@Override
	public void delete(MusicLibObj e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see MusicLibObj#contains(MusicLibObj)
	 */
	@Override
	public boolean contains(MusicLibObj e) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see MusicLibObj#containsKey(String)
	 */
	@Override
	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see MusicLibObj#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return true;
	}

	/**
	 * @see MusicLibObj#getValues()
	 */
	@Override
	public Collection<MusicLibObj> getValues() {
		return null;
	}

}
