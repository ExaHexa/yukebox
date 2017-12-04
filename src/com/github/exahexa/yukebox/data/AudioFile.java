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
	
	
	public AudioFile(String title, byte track, 
					 int duration, String filePath, String fileName) {
		if( title != null && filePath != null && fileName != null &&
			!title.isEmpty() && !filePath.isEmpty() && !fileName.isEmpty()) {
			this.title = title;
			this.track = track;
			this.duration = duration;
			this.filePath = filePath;
			this.fileName = fileName;
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title != null && !title.isEmpty()) {
			this.title = title;
		}
		else {
			throw new IllegalArgumentException();
		}
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
		if(filePath != null && !filePath.isEmpty()) {
			this.filePath = filePath;
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		if(fileName != null && !fileName.isEmpty()) {
			this.fileName = fileName;
		}
		else {
			throw new IllegalArgumentException();
		}
		
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
