package com.psymk6.controllers;
import com.psymk6.models.ScoreModel;
import com.psymk6.models.SnakeModel;

import com.psymk6.models.FoodModel;
import com.psymk6.util.ImageUtil;
import com.psymk6.util.MusicPlayer;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController {
    @FXML
    StackPane stackPane = new StackPane();
    Canvas canvas;
    GraphicsContext gc;
    SnakeController snake;
    FoodController food;
    ScoreController score;
    Image background, fail;
    FoodModel foodModel;
    SnakeModel snakeModel;
    ScoreModel scoreModel;
    Stage stage;
    MusicController musicController;

    public void initialize(Stage stage){
        this.background = ImageUtil.getImage("UI-background");
        this.fail =  ImageUtil.getImage("wasted");
        this.stage = stage;

        musicController = new MusicController("src/main/resources/assets/music/Alan Walker Spectre NCS Release.mp3");

        foodModel = new FoodModel();
        food = new FoodController(foodModel);

        snakeModel = new SnakeModel(100,100);
        snake = new SnakeController(snakeModel,stage);

        scoreModel = new ScoreModel();
        score = new ScoreController(scoreModel);

        canvas = new Canvas(870.0,560.0);
        stackPane.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        score.draw(gc);
        startGame();
    }


    public void startGame(){
        new AnimationTimer(){
            @Override
            public void handle(long l) {
                Platform.runLater(() -> {
                    gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    gc.drawImage(background, -1, -1, 871.0, 561.0);

                    if(snakeModel.isAlive){
                        snake.draw(gc);
                        score.draw(gc);
                        if(foodModel.isAlive){
                            food.draw(gc);
                            if(food.eaten(snakeModel)){
                                score.scoreIncrease();
                            }
                        }
                        else{
                            foodModel = new FoodModel();
                            food = new FoodController(foodModel);
                        }
                    }
                    else{
                        ViewController.deathScreen(fail,stackPane);
                        musicController.stopPlayer();
                        musicController = new MusicController("src/main/resources/assets/music/y2mate.com - heisenburger.mp3");
                        stop();
                    }

                });
            }
        }.start();
    }

    public void shutDown() {
        musicController.stopPlayer();
    }
}
