package com.psymk6.controllers;

import com.psymk6.util.MusicPlayer;

public class MusicController {
    MusicPlayer player;
    public MusicController(String fileName) {
        player = MusicPlayer.getMusicPlayer(fileName);
    }
    public void stopPlayer() {
        player.stopPlayer();
    }
}
