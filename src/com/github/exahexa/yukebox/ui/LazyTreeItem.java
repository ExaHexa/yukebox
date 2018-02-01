/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import com.github.exahexa.yukebox.data.AudioFile;
import com.github.exahexa.yukebox.data.MusicLibObj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.TreeMap;

/**
 * @author exahexa
 *
 */
public class LazyTreeItem extends TreeItem<MusicLibObj> {
	
	
	private boolean loaded = false;
	
	public LazyTreeItem() {
		super();
	}
	
	public LazyTreeItem(MusicLibObj musicLibObj){
		super(musicLibObj);
	}
	


	/* (non-Javadoc)
	 * @see javafx.scene.control.TreeItem#getChildren()
	 */
	@Override
	public ObservableList<TreeItem<MusicLibObj>> getChildren() {
		if(loaded == false) {
			super.getChildren().addAll(loadChildren(this));
		}
		return super.getChildren();
	}
	
	/* (non-Javadoc)
	 * @see javafx.scene.control.TreeItem#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return (getValue() instanceof AudioFile);
	}
	
	private ObservableList<TreeItem<MusicLibObj>> 
						  loadChildren(TreeItem<MusicLibObj> treeItem) {
		loaded = true;
		ObservableList<TreeItem<MusicLibObj>> children = FXCollections.observableArrayList();
		for(MusicLibObj e : treeItem.getValue().getValues()) {
			children.add(new LazyTreeItem(e));
		}
		return children;
	}
	
	
}
