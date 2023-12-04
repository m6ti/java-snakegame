package com.psymk6.controllers;
import com.psymk6.models.BlockadeModel;
import com.psymk6.models.ScoreModel;
import com.psymk6.models.SnakeModel;

import com.psymk6.models.FoodModel;
import com.psymk6.util.ImageUtil;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    VBox viewBox = new VBox();
//    @FXML
    HBox hud = new HBox();
    HudController hudController;
    BlockadeModel blockadeModel;
    BlockadeController blockade;
    GameController gameController = this;

    public void initialize(Stage stage){
        this.background = ImageUtil.getImage("UI-background");
        this.fail =  ImageUtil.getImage("wasted");
        this.stage = stage;

        hudController = new HudController(hud,viewBox);

        musicController = new MusicController("src/main/resources/assets/music/Alan Walker Spectre NCS Release.mp3");


        blockadeModel = new BlockadeModel();
        blockade = new BlockadeController(blockadeModel);

        foodModel = new FoodModel();
        food = new FoodController(foodModel, blockadeModel);

        snakeModel = new SnakeModel(100,100);
        snake = new SnakeController(snakeModel,stage);

        scoreModel = new ScoreModel();

        score = new ScoreController(scoreModel,hud);
        stackPane.setAlignment(Pos.TOP_LEFT);

        canvas = new Canvas(stackPane.getPrefWidth(),stackPane.getPrefHeight());
        stackPane.getChildren().add(canvas);



        gc = canvas.getGraphicsContext2D();

        stage.getIcons().add(ImageUtil.getImage("snake-head-right"));
        startGame();
    }

    public void restartGame() {

    }

    public void startGame(){
        new AnimationTimer(){
            @Override
            public void handle(long l) {
                Platform.runLater(() -> {
                    gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    gc.drawImage(background, 0, 0, 870.0, 560.0);
                    snake.draw(gc);
                    snake.hasHitBlockade(blockade);
                    blockade.draw(gc);
                    score.draw(gc);

                    if(snakeModel.isAlive){
                        if(foodModel.isAlive){
                            food.draw(gc);
                            if(food.intersectsSnake(snakeModel)){
                                score.scoreIncrease();
                            }
                        }
                        else{
                            foodModel = new FoodModel();
                            food = new FoodController(foodModel,blockadeModel);
                        }
                    }
                    else {
                        musicController.stopPlayer();
                        musicController = new MusicController("src/main/resources/assets/music/y2mate.com - heisenburger.mp3");
                        ViewController.deathScreen(fail, stackPane, musicController,gameController );
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
