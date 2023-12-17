package com.psymk6.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

/**
 * The MusicPlayer class provides functionality for playing background music in the game.
 * It uses JavaFX's MediaPlayer for media playback.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class MusicPlayer {
	private final MediaPlayer mediaPlayer;

	/**
	 * Constructs a new MusicPlayer with the specified media file.
	 *
	 * @param fileName The name of the media file to be played.
	 */
	public MusicPlayer(String fileName) {
		// Load the media file from the resource path
		Media media = new Media(Objects.requireNonNull(getClass().getResource(fileName)).toString());
		// Create a media player
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.8);
		mediaPlayer.play();
		// Set up an event handler for when the player reaches the end of the media
		mediaPlayer.setOnEndOfMedia(() -> {
			// Stop the music player
			mediaPlayer.stop();
			mediaPlayer.play();
		});
	}

	/**
	 * Stops the music player.
	 */
	public void stopPlayer() {
		if (mediaPlayer != null) {
			// Stop the music
			mediaPlayer.stop();
		}
	}
}
