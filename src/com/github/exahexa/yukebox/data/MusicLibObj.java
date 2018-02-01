/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.util.Collection;

/**
 * @author exahexa
 *
 */
public interface MusicLibObj {
	
	public String getName();
	public void setName(String name);
	
	public void add(MusicLibObj e);
	public void delete(MusicLibObj e);
	public boolean contains(MusicLibObj e);
	public boolean containsKey(String key);
	public boolean isLeaf();
	public Collection<MusicLibObj> getValues();

}
