/**
 * 
 */
package com.github.exahexa.yukebox.data;

/**
 * @author exahexa
 *
 */
public class Progress {
	
	private int current;
	private int max;
	private String curElement;

	/**
	 * 
	 */
	public Progress() {

	}
	
	public Progress(int current, int max, String curElement) {
		this.current = current;
		this.max = max;
		this.curElement = curElement;
	}
	
	public void update(int i, String s) {
		this.current = i;
		this.curElement = s;
	}
	
	/**
	 * @return the current
	 */
	public int getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(int current) {
		this.current = current;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * @return the curElement
	 */
	public String getCurElement() {
		return curElement;
	}

	/**
	 * @param curElement the curElement to set
	 */
	public void setCurElement(String curElement) {
		this.curElement = curElement;
	}

}
