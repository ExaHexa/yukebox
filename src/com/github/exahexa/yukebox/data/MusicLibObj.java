/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.util.Collection;

/**
 * @author exahexa
 *
 * This interface allows uniformly treating of all objects that are part of the
 * music library such as the Artist, Album and AudioFile class.
 * 
 * Since this is a more transparency based implementation, calling the
 * methods add(), delete(), contains(), containsKey() and getValues() 
 * in the leaf nodes wont get any meaningful results.
 *  
 */
public interface MusicLibObj {
	
	/**
	 * 
	 * @return the name of this MusicLibObj
	 */
	public String getName();
	
	/**
	 * Sets the name of the specific MusicLibObj
	 * @param name new name for the specific MusicLibObj
	 */
	public void setName(String name);
	
	/**
	 * Adds element to the data structure 
	 * @param e a new element to add
	 */
	public void add(MusicLibObj e);
	
	/**
	 * Deletes element from the data structure
	 * @param e the element to delete 
	 */
	public void delete(MusicLibObj e);
	
	/**
	 * Returns true if the data structure contains the element e, false otherwise
	 * @param e the element whose presence is to be tested
	 * @return true if the data structure contains the element e, false otherwise
	 */
	public boolean contains(MusicLibObj e);
	
	/**
	 * Returns true if the map contains the key specified by the argument of the same name
	 * @param key whose presence is to be tested
	 * @return true if the map contains the key, false otherwise
	 */
	public boolean containsKey(String key);
	
	/**
	 * Returns true if the MusicLibObj is an instance of the AudioFile class, false otherwise
	 * @return true if this object is an instance of the AudioFile class, false otherwise
	 */
	public boolean isLeaf();
	
	/**
	 * Returns a collection view of the contained in this MusicLibObj
	 * @return a collection view of the contained in this MusicLibObj
	 */
	public Collection<MusicLibObj> getValues();

}
