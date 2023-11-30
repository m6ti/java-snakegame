package com.psymk6;

import com.psymk6.controllers.*;
import com.psymk6.models.SnakeModel;
import com.psymk6.util.MusicPlayer;
import com.psymk6.views.View;

public class Main {

    public static void main(String[] args) {
        MusicPlayer.getMusicPlay("src/main/resources/assets/music/frogger.mp3");

        SnakeModel snakeModel = new SnakeModel(100, 100);
        View view = new View(snakeModel);
        PlayController controller = new PlayController(snakeModel,view);

        view.loadFrame(controller);
        new ThreadController(view).run();

    }
}