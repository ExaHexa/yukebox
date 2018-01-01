/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author exahexa
 *
 */
class SceneManager {
	
	private static SceneManager sceneManager;
	
	private SceneManager() {
		
	}
	
	public synchronized static SceneManager getInstance() {
		if(sceneManager == null) {
			sceneManager = new SceneManager();
		}
		return sceneManager;
	}
	
	public void swToMainScene(Stage stage) throws Exception{
		stage.setScene(new Scene(new FXMLLoader(getClass().
				getResource("SceneTest.fxml")).load()));
				
	}
}
