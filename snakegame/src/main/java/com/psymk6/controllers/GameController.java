package com.psymk6.controllers;
import com.psymk6.models.BlockadeModel;
import com.psymk6.models.ScoreModel;
import com.psymk6.models.SnakeModel;

import com.psymk6.models.FoodModel;
import com.psymk6.util.GameUtil;
import com.psymk6.util.ImageUtil;
import com.psymk6.util.ScoreUtil;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private boolean isPaused = false;
    MusicController musicController;
    @FXML
    VBox viewBox = new VBox();
    BlockadeModel blockadeModel;
    BlockadeController blockade;
    GameController gameController = this;
    PauseController pauseController;
    String playerName;
    private Color gameColor;

    public void initialize(Stage stage){
        this.background = GameUtil.getColoredImage(ImageUtil.getImage("UI-background"),gameColor);
        this.fail =  ImageUtil.getImage("wasted");
        this.stage = stage;
        stage.setOnCloseRequest(e -> {
            stopMusicPlayer();
        });

        canvas = new Canvas(stackPane.getPrefWidth(),stackPane.getPrefHeight());

        initModels();
        initControllers();

        initCanvas();
        initIcon();
        startGame();
    }
    private void initIcon() {
        stage.getIcons().add(ImageUtil.getImage("snake-head-right"));
    }
    private void initCanvas() {
        stackPane.getChildren().removeAll();
        stackPane.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        pauseController.setClick(canvas,this);
    }
    private void initModels() {
        snakeModel = new SnakeModel(100,100);
        scoreModel = new ScoreModel();
        blockadeModel = new BlockadeModel();
        foodModel = new FoodModel();
    }
    private void initControllers() {
        musicController = new MusicController("src/main/resources/assets/music/Alan Walker Spectre NCS Release.mp3");
        snake = new SnakeController(snakeModel,stage);
        score = new ScoreController(scoreModel);
        blockade = new BlockadeController(blockadeModel);
        food = new FoodController(foodModel, blockadeModel);
        pauseController = new PauseController();
    }
    public void restartGame() {
        musicController.stopPlayer();
        canvas = new Canvas(stackPane.getPrefWidth(),stackPane.getPrefHeight());
        initModels();
        initControllers();
        initCanvas();
    }
    public void togglePauseButton() {
        isPaused = !isPaused;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public void drawObjects(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(background, 0, 0, 870.0, 560.0);

        snake.draw(gc);
        blockade.draw(gc);
        score.draw(gc);
        pauseController.draw(gc);
    }

    public void startGame(){
        new AnimationTimer(){
            @Override
            public void handle(long l) {
                Platform.runLater(() -> {
                    if (!isPaused) {
                        snake.checkHitBlockade(blockade);
                        drawObjects(gc);

                        if (snakeModel.isAlive()) {
                            if (foodModel.isAlive()) {
                                food.draw(gc);
                                if (food.intersectsSnake(snakeModel)) {
                                    score.scoreIncrease();
                                }
                            } else {
                                foodModel = new FoodModel();
                                food = new FoodController(foodModel, blockadeModel);
                            }
                        } else {
                            snakeDied(this);
                        }
                    }
                });
            }
        }.start();
    }
    private void snakeDied(AnimationTimer animationTimer) {
        ScoreUtil.appendScore(playerName,scoreModel.getIntScore()); //Add the score to the scores file
        stopMusicPlayer();
        musicController = new MusicController("src/main/resources/assets/music/y2mate.com - heisenburger.mp3");
        deathScreen(musicController, gameController, animationTimer);
        animationTimer.stop(); //Stop the animation
    }


    private void deathScreen(MusicController musicController,
                                   GameController gameController, AnimationTimer animationTimer) {
        ImageView failImageView = new ImageView(fail);
        failImageView.setOpacity(0);
        stackPane.getChildren().add(failImageView);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), failImageView);
        fadeTransition.setToValue(1);

        fadeTransition.setOnFinished(event -> showRetryExitButtons(stackPane,musicController,gameController,animationTimer,failImageView));
        fadeTransition.play();
    }

    private static void showRetryExitButtons(StackPane stackPane, MusicController musicController,
                                             GameController gameController, AnimationTimer animationTimer,
                                             ImageView failImageView) {
        VBox buttonBox = new VBox(10);
        Button retryButton = createStyledButton("Retry");
        retryButton.setOnAction(event -> {
            failImageView.setOpacity(0);
            stackPane.getChildren().remove(buttonBox);
            gameController.restartGame();
            animationTimer.start();
        });
        Button exitButton = createStyledButton("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
            musicController.stopPlayer();
        });
        buttonBox.getChildren().addAll(retryButton, exitButton);
        stackPane.getChildren().add(buttonBox);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setTranslateY(buttonBox.getTranslateY() + 90);
    }
    private static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 16; -fx-background-color: #FF0000; -fx-text-fill: white;");
        return button;
    }
    public void setGameColor(Color gameColor) {
        this.gameColor = gameColor;
    }
    private void stopMusicPlayer() {
        musicController.stopPlayer();
    }
}

