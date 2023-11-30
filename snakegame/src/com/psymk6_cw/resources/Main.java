package com.psymk6.resources;

import com.psymk6.resources.controllers.PlayController;
import com.psymk6.resources.controllers.ThreadController;
import com.psymk6.resources.models.SnakeModel;
import com.psymk6.resources.util.MusicPlayer;
import com.psymk6.resources.views.View;

public class Main {
    public static void main(String[] args) {

        SnakeModel snakeModel = new SnakeModel(100, 100);
        View view = new View(snakeModel);
        PlayController controller = new PlayController(snakeModel,view);

        view.loadFrame(controller);
        new ThreadController(view).run();

        MusicPlayer.getMusicPlay("src/assets/music/frogger.mp3");
    }
}