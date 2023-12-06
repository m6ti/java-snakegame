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
        // Initialise background images
        this.backgroundLevel1 = GameUtil.getColoredImage(ImageUtil.getImage("UI-background"),gameColor);
        this.backgroundLevel2 = GameUtil.getColoredImage(ImageUtil.getImage("UI-background2"),gameColor);
        this.fail =  ImageUtil.getImage("wasted");
        // Ensure music player stops on exit
        this.stage = stage;
        stage.setOnCloseRequest(e -> {
            stopMusicPlayer();
        });
        // Set up the canvas for the objects
        canvas = new Canvas(stackPane.getPrefWidth(),stackPane.getPrefHeight());
        // Initialise models and controllers
        initModels();
        initControllers();
        // Initialise the canvas
        initCanvas();
        // Set icon for the taskbar and window bar
        initIcon();
        // Begin the game
        startGame();
    }
    private void initIcon() {
        stage.getIcons().add(ImageUtil.getImage("snake-head-right"));
    }
    private void initCanvas() {
        // Remove any existing items from the pane
        stackPane.getChildren().removeAll();
        // Add the canvas to the pane
        stackPane.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        // Add listener to the canvas
        canvas.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            // Check if each mouse click corresponds to pause or exit
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
        exitController = new ExitController();
        musicController = new MusicController("src/main/resources/assets/music/Alan Walker" +
                " Spectre NCS Release.mp3");
        snake = new SnakeController(snakeModel,stage);
        score = new ScoreController(scoreModel);
        blockade = new BlockadeController(blockadeModel);
        food = new FoodController(foodModel, blockadeModel);
        pauseController = new PauseController();
    }
    public void restartGame() {
        // Stop sad music
        musicController.stopPlayer();
        // Make a new canvas
        canvas = new Canvas(stackPane.getPrefWidth(),stackPane.getPrefHeight());
        // Initialise models and controllers
        initModels();
        initControllers();
        // Initialise canvas
        initCanvas();
    }
    public void togglePauseButton() {
        isPaused = !isPaused;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public void drawObjects(GraphicsContext gc) {
        // Draw the correct background, snake and blockades
        if(getLevel() == 1) {
            gc.drawImage(backgroundLevel1, 0, 0, 870.0, 560.0);
        }else {
            gc.drawImage(backgroundLevel2, 0, 0, 870.0, 560.0);
        }
        snake.draw(gc);
        blockade.draw(gc);
    }
    public void drawGameObjects(GraphicsContext gc) {
        // Draw score and pause button
        score.draw(gc);
        pauseController.draw(gc);
    }

    public void startGame(){
        new AnimationTimer(){
            @Override
            public void handle(long l) {
                Platform.runLater(() -> {
                    if (!isPaused) {
                        // If not paused, check if snake has hit a blockade.
                        snake.checkHitBlockade(blockade);
                        blockade.moveTowardsSnake(snakeModel);
                        // Draw objects
                        drawObjects(gc);
                        if (snakeModel.isAlive()) {
                            // If alive, check if snake has eaten the food
                            if (!food.intersectsSnake(snakeModel)) {
                                // If food has not been eaten, draw the food
                                food.draw(gc);
                            } else {
                                // If food has been eaten, increase score and add new food
                                score.increaseScore();
                                foodModel = new FoodModel();
                                food = new FoodController(foodModel, blockadeModel);
                            }
                            // Draw score and pause buttons
                            drawGameObjects(gc);
                        } else {
                            // The snake has died
                            snakeDied(this);
                        }
                    }else{
                        // Draw the exit button if the game is paused
                        exitController.draw(gc);
                    }
                });
            }
        }.start();
    }
    private void snakeDied(AnimationTimer animationTimer) {
        // Add the score to the scores file
        ScoreUtil.appendScore(playerName,scoreModel.getIntScore());
        // Stop game music
        stopMusicPlayer();
        // Play sad music
        musicController = new MusicController("src/main/resources/assets/music/y2mate.com" +
                " - SadMusic Sound Effect 1080p HD No copyright.mp3");
        // Initialise the death screen
        deathScreen(musicController, gameController, animationTimer);
        //Stop the animation
        animationTimer.stop();
    }


    private void deathScreen(MusicController musicController, GameController gameController,
                             AnimationTimer animationTimer) {
        // Create the fail/death image
        ImageView failImageView = new ImageView(fail);
        // Add transition to bring the image in over the game view
        failImageView.setOpacity(0);
        stackPane.getChildren().add(failImageView);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), failImageView);
        fadeTransition.setToValue(1);
        // When transition finishes, show buttons to navigate
        fadeTransition.setOnFinished(event -> showRetryExitButtons(stackPane,musicController,gameController,animationTimer,failImageView));
        fadeTransition.play();
    }

    private static void showRetryExitButtons(StackPane stackPane, MusicController musicController,
                                             GameController gameController, AnimationTimer animationTimer,
                                             ImageView failImageView) {
        // Create horizontal box.
        HBox buttonBox = new HBox(10);
        // Create a retry button, which restarts the game
        Button retryButton = GameUtil.createStyledButton("Retry");
        retryButton.setOnAction(event -> {
            failImageView.setOpacity(0);
            stackPane.getChildren().remove(buttonBox);
            gameController.restartGame();
            animationTimer.start();
        });
        // Create exit button, which exits the game
        Button exitButton = GameUtil.createStyledButton("Exit");
        exitButton.setOnAction(event -> {
            exit();
            musicController.stopPlayer();
        });
        // Create change level button, which changes the level and restarts the game
        Button changeButton = GameUtil.createStyledButton("Change Level");
        changeButton.setOnAction(event -> {
            if(gameController.getLevel()==1){
                gameController.setLevel(2);
            }else{
                gameController.setLevel(1);
            }
            retryButton.fire();
        });
        // Add all the buttons to the horizontal box, and add to view
        buttonBox.getChildren().addAll(retryButton, exitButton,changeButton);
        stackPane.getChildren().add(buttonBox);
        // Position the box correctly
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setTranslateY(buttonBox.getTranslateY() + 90);
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
    private int getLevel() {
        return this.level;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void homeButtonClicked() throws IOException {
        // Close current stage and stop music
        Stage currentStage = this.stage;
        currentStage.close();
        stopMusicPlayer();
        // Create new stage
        Stage MenuStage = new Stage();
        // Load in the menu view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/psymk6/menu-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene1 = new Scene(root, 700, 500);
        MenuStage.setTitle("SnakeGame");
        // Initialise the menu view
        ViewController viewController = fxmlLoader.getController();
        viewController.addScores();
        // Add to stage and show
        MenuStage.setScene(scene1);
        MenuStage.getIcons().add(ImageUtil.getImage("snake-head-right"));
        MenuStage.show();
    }
}

