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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static javafx.application.Platform.exit;

public class GameController {
    @FXML
    StackPane stackPane = new StackPane();
    Canvas canvas;
    GraphicsContext gc;
    SnakeController snake;
    FoodController food;
    ScoreController score;
    Image backgroundLevel1, backgroundLevel2, fail;
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
    ExitController exitController;
    private Color gameColor;
    private int level;


    public void initialize(Stage stage){
        this.backgroundLevel1 = GameUtil.getColoredImage(ImageUtil.getImage("UI-background"),gameColor);
        this.backgroundLevel2 = GameUtil.getColoredImage(ImageUtil.getImage("UI-background2"),gameColor);
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
        canvas.setOnMouseClicked(event -> {
                double mouseX = event.getX();
                double mouseY = event.getY();
            pauseController.setClick(this,mouseX,mouseY);
            exitController.setClick(this,mouseX,mouseY);
        });
    }
    private void initModels() {
        snakeModel = new SnakeModel(100,100);
        scoreModel = new ScoreModel();
        blockadeModel = new BlockadeModel(level);
        foodModel = new FoodModel();
    }
    private void initControllers() {
        exitController = new ExitController(this);
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
        if(getLevel() == 1) {
            gc.drawImage(backgroundLevel1, 0, 0, 870.0, 560.0);
        }else {
            gc.drawImage(backgroundLevel2, 0, 0, 870.0, 560.0);
        }
        snake.draw(gc);
        blockade.draw(gc);
    }

    private int getLevel() {
        return this.level;
    }

    public void drawGameObjects(GraphicsContext gc) {
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
                        blockade.moveTowardsSnake(snakeModel);
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
                            drawGameObjects(gc);
                        } else {
                            snakeDied(this);
                        }
                    }else{
                        exitController.draw(gc);
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
        HBox buttonBox = new HBox(10);
        Button retryButton = createStyledButton("Retry");
        retryButton.setOnAction(event -> {
            failImageView.setOpacity(0);
            stackPane.getChildren().remove(buttonBox);
            gameController.restartGame();
            animationTimer.start();
        });
        Button exitButton = createStyledButton("Exit");
        exitButton.setOnAction(event -> {
            exit();
            musicController.stopPlayer();
        });
        Button changeButton = createStyledButton("Change Level");
        changeButton.setOnAction(event -> {
            if(gameController.getLevel()==1){
                gameController.setLevel(2);
            }else{
                gameController.setLevel(1);
            }
            retryButton.fire();
        });
        buttonBox.getChildren().addAll(retryButton, exitButton,changeButton);
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

    public void setLevel(int i) {
        this.level = i;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void homeButtonClicked() throws IOException {
        Stage currentStage = this.stage;
        currentStage.close();
        stopMusicPlayer();

        Stage MenuStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/psymk6/menu-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene1 = new Scene(root, 700, 500);
        MenuStage.setTitle("SnakeGame");

        ViewController viewController = fxmlLoader.getController();
        viewController.addScores();

        MenuStage.setScene(scene1);
        MenuStage.getIcons().add(ImageUtil.getImage("snake-head-right"));
        MenuStage.show();
    }
}

