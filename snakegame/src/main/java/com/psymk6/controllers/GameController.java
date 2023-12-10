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

/**
 * The GameController class controls the main functionality of the SnakeGame after the menu screen.
 * It manages the game state, initializes models and controllers, handles user input, and
 * orchestrates the game loop.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class GameController {
    /**
     * StackPane for organizing UI elements.
     */
    @FXML
    StackPane stackPane = new StackPane();

    /**
     * VBox for organizing UI elements.
     */
    @FXML
    VBox viewBox = new VBox();

    /**
     * Canvas for drawing game objects.
     */
    Canvas canvas;

    /**
     * GraphicsContext for drawing on the canvas.
     */
    GraphicsContext gc;

    /**
     * Controller for managing snake behavior.
     */
    SnakeController snake;

    /**
     * Controller for managing food behavior.
     */
    FoodController food;

    /**
     * Controller for managing score display.
     */
    ScoreController score;

    /**
     * Image for the first background level.
     */
    Image backgroundLevel1;

    /**
     * Image for the second background level.
     */
    Image backgroundLevel2;

    /**
     * Image for displaying when the snake dies.
     */
    Image fail;

    /**
     * Model for managing food behavior.
     */
    FoodModel foodModel;

    /**
     * Model for managing snake behavior.
     */
    SnakeModel snakeModel;

    /**
     * Model for managing score display.
     */
    ScoreModel scoreModel;

    /**
     * The primary stage for the game.
     */
    Stage stage;

    /**
     * Flag indicating whether the game is paused.
     */
    private boolean isPaused = false;

    /**
     * Controller for managing music playback.
     */
    MusicController musicController;

    /**
     * Model for managing blockades.
     */
    BlockadeModel blockadeModel;

    /**
     * Controller for managing blockade behavior.
     */
    BlockadeController blockade;

    /**
     * Reference to the current instance of the GameController.
     */
    GameController gameController = this;

    /**
     * Controller for managing pause functionality.
     */
    PauseController pauseController;

    /**
     * Player name for the game.
     */
    String playerName;

    /**
     * Controller for managing exit button functionality.
     */
    ExitController exitController;

    /**
     * Color scheme for the game.
     */
    private Color gameColor;

    /**
     * Current level of the game.
     */
    private int level;

    /**
     * Initializes the GameController with the primary Stage for the game.
     *
     * @param stage The primary Stage for the game.
     */
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

    /**
     * Initializes the icon for the taskbar and window bar.
     */
    private void initIcon() {
        stage.getIcons().add(ImageUtil.getImage("snake-head-right"));
    }

    /**
     * Initializes the canvas for drawing game objects and sets up listeners.
     */
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

    /**
     * Initializes the models used in the game, including SnakeModel, ScoreModel, and others.
     */
    private void initModels() {
        snakeModel = new SnakeModel(100,100);
        scoreModel = new ScoreModel();
        blockadeModel = new BlockadeModel(level);
        foodModel = new FoodModel();
    }

    /**
     * Initializes the controllers used in the game, including SnakeController, ScoreController, and others.
     */
    private void initControllers() {
        exitController = new ExitController();
        musicController = new MusicController("/assets/music/Alan-Walker-Spectre-NCS-Release.mp3");
        snake = new SnakeController(snakeModel,stage);
        score = new ScoreController(scoreModel);
        blockade = new BlockadeController(blockadeModel);
        food = new FoodController(foodModel, blockadeModel);
        pauseController = new PauseController();
    }

    /**
     * Restarts the game by stopping sad music, creating a new canvas, and initializing models and controllers.
     */
    private void restartGame() {
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

    /**
     * Toggles the paused state of the game.
     */
    public void togglePauseButton() {
        isPaused = !isPaused;
    }

    /**
     * Sets the player name for the game.
     *
     * @param name The name of the player.
     */
    public void setPlayerName(String name) {
        this.playerName = name;
    }

    /**
     * Draws the game objects, including the background, snake, and blockades, on the canvas.
     *
     * @param gc The GraphicsContext used for drawing on the canvas.
     */
    private void drawObjects(GraphicsContext gc) {
        // Draw the correct background, snake and blockades
        if(getLevel() == 1) {
            gc.drawImage(backgroundLevel1, 0, 0, 870.0, 560.0);
        }else {
            gc.drawImage(backgroundLevel2, 0, 0, 870.0, 560.0);
        }
        snake.draw(gc);
        blockade.draw(gc);
    }

    /**
     * Draws the score and pause button on the canvas.
     *
     * @param gc The GraphicsContext used for drawing on the canvas.
     */
    private void drawGameObjects(GraphicsContext gc) {
        // Draw score and pause button
        score.draw(gc);
        pauseController.draw(gc);
    }

    /**
     * Starts the game loop using an AnimationTimer, handling game logic and updating the canvas.
     */
    private void startGame(){
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

    /**
     * Handles the game over scenario when the snake dies, updates the score, plays sad music,
     * and shows the death screen.
     *
     * @param animationTimer The AnimationTimer used for the game loop.
     */
    private void snakeDied(AnimationTimer animationTimer) {
        // Add the score to the scores file
        ScoreUtil.appendScore(playerName,scoreModel.getIntScore());
        // Stop game music
        stopMusicPlayer();
        // Play sad music
        musicController = new MusicController("/assets/music/y2mate-com" +
                "-SadMusic-Sound-Effect-1080p-HD-No-copyright.mp3");
        // Initialise the death screen
        deathScreen(musicController, gameController, animationTimer);
        //Stop the animation
        animationTimer.stop();
    }

    /**
     * Displays the death screen with a fail image, fades it in, and shows retry, exit, and change level buttons.
     *
     * @param musicController The MusicController responsible for playing music in the game.
     * @param gameController The GameController instance.
     * @param animationTimer The AnimationTimer used for the game loop.
     */
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

    /**
     * Shows retry, exit, and change level buttons after the death screen animation finishes.
     *
     * @param stackPane The StackPane containing the game elements.
     * @param musicController The MusicController responsible for playing music in the game.
     * @param gameController The GameController instance.
     * @param animationTimer The AnimationTimer used for the game loop.
     * @param failImageView The ImageView displaying the fail image.
     */
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
        // Create exit button, which exits the game
        Button homeButton = GameUtil.createStyledButton("Menu");
        homeButton.setOnAction(event -> {
            try {
                failImageView.setOpacity(0);
                stackPane.getChildren().remove(buttonBox);
                gameController.homeButtonClicked();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Add all the buttons to the horizontal box, and add to view
        buttonBox.getChildren().addAll(retryButton, homeButton, changeButton, exitButton);
        stackPane.getChildren().add(buttonBox);
        // Position the box correctly
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setTranslateY(buttonBox.getTranslateY() + 90);
    }

    /**
     * Sets the color scheme for the game.
     *
     * @param gameColor The Color to be used for the game.
     */
    public void setGameColor(Color gameColor) {
        this.gameColor = gameColor;
    }

    /**
     * Stops the music player in the game.
     */
    private void stopMusicPlayer() {
        musicController.stopPlayer();
    }

    /**
     * Sets the game level.
     *
     * @param i The level to be set.
     */
    public void setLevel(int i) {
        this.level = i;
    }

    /**
     * Gets the current game level.
     *
     * @return The current game level.
     */
    private int getLevel() {
        return this.level;
    }

    /**
     * Checks if the game is paused.
     *
     * @return True if the game is paused, false otherwise.
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Handles the event when the home button is clicked, closes the current stage, stops the music,
     * and opens the menu stage.
     *
     * @throws IOException If an I/O error occurs while loading the menu stage.
     */
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
        MenuController viewController = fxmlLoader.getController();
        viewController.addScores();
        // Add to stage and show
        MenuStage.setScene(scene1);
        MenuStage.getIcons().add(ImageUtil.getImage("snake-head-right"));
        MenuStage.show();
    }
}

