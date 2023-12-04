package com.psymk6.controllers;

import com.psymk6.util.MusicPlayer;

public class MusicController {
    MusicPlayer player = null;
    public MusicController(String fileName) {
        player = MusicPlayer.getMusicPlayer(fileName);
    }
    public void stopPlayer() {
        if(player!= null){player.stopPlayer();}
    }
}
