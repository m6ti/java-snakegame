package com.psymk6.controllers;

import com.psymk6.util.MusicPlayer;

/**
 * The MusicController class manages the functionality of the music player in the SnakeGame.
 * It is responsible for initializing the MusicPlayer with a specified file and providing
 * a method to stop the music player.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class MusicController {
    /**
     * The MusicPlayer instance associated with this controller.
     */
    MusicPlayer player = null;

    /**
     * Constructs a MusicController with the specified file name.
     * Initializes the associated MusicPlayer with the given file.
     *
     * @param fileName The name of the music file to be played.
     */
    public MusicController(String fileName) {
        player = new MusicPlayer(fileName);
    }

    /**
     * Stops the music player if it is currently playing.
     */
    public void stopPlayer() {
        if(player!= null){player.stopPlayer();}
    }
}
