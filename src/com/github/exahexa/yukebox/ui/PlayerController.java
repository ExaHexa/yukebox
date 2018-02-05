/**
 * 
 */
package com.github.exahexa.yukebox.ui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

/**
 * @author exahexa
 *
 */
public class PlayerController {
	
	MainGUI main;

	/**
	 * 
	 */
	public PlayerController(MainGUI main) {
		this.main = main;
	}
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="random_button"
    private Button random_button; // Value injected by FXMLLoader

    @FXML // fx:id="backward_button"
    private Button backward_button; // Value injected by FXMLLoader

    @FXML // fx:id="play_button"
    private Button play_button; // Value injected by FXMLLoader

    @FXML // fx:id="forward_button"
    private Button forward_button; // Value injected by FXMLLoader

    @FXML // fx:id="repeat_button"
    private Button repeat_button; // Value injected by FXMLLoader

    @FXML // fx:id="tim_current"
    private Label time_current; // Value injected by FXMLLoader

    @FXML // fx:id="duration_slider"
    private Slider duration_slider; // Value injected by FXMLLoader

    @FXML // fx:id="time_max"
    private Label time_max; // Value injected by FXMLLoader

    @FXML // fx:id="volume_button"
    private Button volume_button; // Value injected by FXMLLoader

    @FXML // fx:id="volume_slider"
    private Slider volume_slider; // Value injected by FXMLLoader
    
    private MediaPlayer mP = new MediaPlayer(new Media(new File("/home/exahexa/Music/Music/Ben Harper/Fight For Your Mind/01 - Ben Harper - Oppression.mp3").toURI().toString()));
    private boolean playing = false;
    private Duration duration;

    private double volume;
    
    
    /**
     * Load all images 
     */
    private Image playImg = new Image("file:resources/pic/play_h2.png");
    private Image pauseImg = new Image("file:resources/pic/pause_h2.png");
    private ImageView playPauseIcon = new ImageView(playImg);
    
    private Image prevImg = new Image("file:resources/pic/prev_track_h.png");
    private Image prevImgH = new Image("file:resources/pic/prev_track.png");
    private ImageView prevIcon = new ImageView(prevImg);
    
    private Image nextImg = new Image("file:resources/pic/next_track_h.png");
    private Image nextImgH = new Image("file:resources/pic/next_track.png");
    private ImageView nextIcon = new ImageView(nextImg);
    
    private Image shuffleImg = new Image("file:resources/pic/shuffle_h.png");
    private Image shuffleImgA = new Image("file:resources/pic/shuffle_a.png");
    private ImageView shuffleIcon = new ImageView(shuffleImg);
    
    private Image repeatImg = new Image("file:resources/pic/replay_h.png");
    private Image repeatImgA = new Image("file:resources/pic/replay_a.png");
    private ImageView repeatIcon = new ImageView(repeatImg);
    
    private Image volumeImg = new Image("file:resources/pic/audio_h.png");
    private ImageView volumeIcon = new ImageView(volumeImg);
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
    	volume = 100.00;
    	play_button.setGraphic(playPauseIcon);
    	forward_button.setGraphic(nextIcon);
    	backward_button.setGraphic(prevIcon);
    	random_button.setGraphic(shuffleIcon);
    	repeat_button.setGraphic(repeatIcon);
    	volume_button.setGraphic(volumeIcon);
    	
    	forward_button.setOnAction(event->{
    		nextMedia(main.getNextMedia());    		
    	});
    	forward_button.setOnMouseEntered(event->{
    		nextIcon.setImage(nextImgH);
    	});
    	forward_button.setOnMouseExited(event->{
    		nextIcon.setImage(nextImg);
    	});
    	backward_button.setOnMouseEntered(event->{
    		prevIcon.setImage(prevImgH);
    	});
    	backward_button.setOnMouseExited(event->{
    		prevIcon.setImage(prevImg);
    	});
    	
    	
    	
    	play_button.setOnAction(handle->{
    		
    		Status status = mP.getStatus();
			if(status == Status.UNKNOWN || status == Status.HALTED) {
				return;
			}
			if(status == Status.PAUSED || status == Status.READY
									   || status == Status.STOPPED) {
				mP.play();
			}
			else {
				mP.pause();
			}
    	});
    	
        volume_slider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
               if(volume_slider.isValueChanging()) {
            	   volume = volume_slider.getValue() / 100.0;
                   mP.setVolume(volume);
               }
            }
        });
    	
    	initPlayer();

    	
    }
    
    private void nextMedia(File f) {
    	mP.stop();
    	mP = new MediaPlayer(new Media(f.toURI().toString()));
    	initPlayer();
    	mP.setVolume(volume);
    	mP.play();
    }
    
    private void initPlayer() {
    	mP.currentTimeProperty().addListener(new InvalidationListener() 
        {
            public void invalidated(Observable ov) {
                updateValues();
            }
        });
 
        mP.setOnPlaying(new Runnable() {
            public void run() {
                if (playing) {
                    mP.pause();
                    playing = false;
                } else {
                	playPauseIcon.setImage(pauseImg);
                    play_button.setGraphic(playPauseIcon);
                }
            }
        });
 
        mP.setOnPaused(new Runnable() {
            public void run() {
                playPauseIcon.setImage(playImg);
                play_button.setGraphic(playPauseIcon);
            }
        });
 
        mP.setOnReady(new Runnable() {
            public void run() {
                duration = mP.getMedia().getDuration();
                updateValues();
            }
        });
 
        //mP.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        /**
        mP.setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!repeat) {
                    playButton.setText(">");
                    stopRequested = true;
                    atEndOfMedia = true;
                }
            }
       });
       */
        duration_slider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
               if (duration_slider.isValueChanging()) {
               // multiply duration by percentage calculated by slider position
                  mP.seek(duration.multiply(duration_slider.getValue() / 100.0));
               }
            }
        });
        
        

    	
    }
    
    /**
     * 
     */
    protected void updateValues() {
    	  if(time_current != null && duration_slider != null && volume_slider != null) {
    	     Platform.runLater(new Runnable() {
    	    	 public void run() {
	    	          Duration currentTime = mP.getCurrentTime();
	    	          time_current.setText(formatTime(currentTime, duration));
	    	          duration_slider.setDisable(duration.isUnknown());
	    	          if(!duration_slider.isDisabled() 
	    	        		  	  && duration.greaterThan(Duration.ZERO) 
	    	        		  	  && !duration_slider.isValueChanging()) {
	    	              duration_slider.setValue(currentTime
	    	            		  	.divide(duration).toMillis() * 100.0);
	    	          }
	    	          if(!volume_slider.isValueChanging()) {
	    	        	  volume_slider.setValue((int)Math.round
	    	            							(mP.getVolume()* 100));
	    	          }
    	        }
    	     });
    	  }
    	}
    
    
    /**
     * 
     * @param elapsed
     * @param duration
     * @return
     */
    private String formatTime(Duration elapsed, Duration duration) {
    	   int intElapsed = (int)Math.floor(elapsed.toSeconds());
    	   int elapsedHours = intElapsed / (60 * 60);
    	   if (elapsedHours > 0) {
    	       intElapsed -= elapsedHours * 60 * 60;
    	   }
    	   int elapsedMinutes = intElapsed / 60;
    	   int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 
    	                           - elapsedMinutes * 60;
    	 
    	   if (duration.greaterThan(Duration.ZERO)) {
    	      int intDuration = (int)Math.floor(duration.toSeconds());
    	      int durationHours = intDuration / (60 * 60);
    	      if (durationHours > 0) {
    	         intDuration -= durationHours * 60 * 60;
    	      }
    	      int durationMinutes = intDuration / 60;
    	      int durationSeconds = intDuration - durationHours * 60 * 60 - 
    	          durationMinutes * 60;
    	      if (durationHours > 0) {
    	         return String.format("%d:%02d:%02d/%d:%02d:%02d", 
    	            elapsedHours, elapsedMinutes, elapsedSeconds,
    	            durationHours, durationMinutes, durationSeconds);
    	      } else {
    	          return String.format("%02d:%02d/%02d:%02d",
    	            elapsedMinutes, elapsedSeconds,durationMinutes, 
    	                durationSeconds);
    	      }
    	      } else {
    	          if (elapsedHours > 0) {
    	             return String.format("%d:%02d:%02d", elapsedHours, 
    	                    elapsedMinutes, elapsedSeconds);
    	            } else {
    	                return String.format("%02d:%02d",elapsedMinutes, 
    	                    elapsedSeconds);
    	            }
    	        }
    }

}
