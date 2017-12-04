/**
 * 
 */
package com.github.exahexa.yukebox.data;

/**
 * @author exahexa
 * 
 */
public class AudioFile {
	
	//private int id;
	private String title;
	private byte track;
	private int duration;
	private String filePath;
	private String fileName;
	
	/**
	 * constructs a new audio file
	 */
	public AudioFile() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte getTrack() {
		return track;
	}

	public void setTrack(byte track) {
		this.track = track;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getDuration() {
		return duration;
	}

	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof AudioFile)
				&& (this.title.equals( ((AudioFile)obj).getTitle() ))
				&& (this.filePath.equals( ((AudioFile)obj).getFilePath() ))
				&& (this.fileName.equals( ((AudioFile)obj).getFileName() ))
				&& (this.track == ((AudioFile)obj).getTrack() )
				&& (this.duration == ((AudioFile)obj).getDuration() );
	}

	
	
	
	

}
