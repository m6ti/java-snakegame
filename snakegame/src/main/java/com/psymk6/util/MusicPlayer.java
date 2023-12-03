package com.psymk6.util;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MusicPlayer extends Thread {
	private String filename;
	private Player player;
	private volatile boolean stopRequested = false;

	public MusicPlayer(String filename) {
		this.filename = filename;
	}

	public void stopPlayer() {
		player.close();
	}

	@Override
	public void run() {
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename))) {
			player = new Player(buffer);
			player.play();
		} catch (Exception e) {
			if (!stopRequested) {
				System.err.println("Error while playing music: " + e.getMessage());
			}
		} finally {
			if (player != null) {
				player.close();
			}
		}
	}

	public static MusicPlayer getMusicPlayer(String filename) {
		MusicPlayer musicPlayer = new MusicPlayer(filename);
		musicPlayer.start();
		return musicPlayer;
	}
}
