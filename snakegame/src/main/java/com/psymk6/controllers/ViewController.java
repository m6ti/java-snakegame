package com.psymk6.controllers;

import com.psymk6.util.ScoreUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * The ViewController class controls the main functionality of the SnakeGame menu view.
 * It handles user interactions, such as button clicks, color selections, and entering the game.
 * The class is responsible for setting up the game stage, initializing the game, and displaying scores.
 * It implements the user interface logic for the main menu.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class ViewController {
    /**
     * The TextArea used for displaying the score board.
     */
    @FXML
    private TextArea scoreBoard = new TextArea();

    /**
     * The Text used for displaying the heading text.
     */
    @FXML
    private Text headingText = new Text();

    /**
     * The Text used for displaying the high score text.
     */
    @FXML
    private Text highScoreText = new Text();

    /**
     * The Button used for the first option in the menu.
     */
    @FXML
    private Button button1 = new Button();

    /**
     * The Button used for the second option in the menu.
     */
    @FXML
    private Button button2 = new Button();

    /**
     * The ColorPicker used for selecting the game color.
     */
    @FXML
    private ColorPicker colorPicker = new ColorPicker();

    /**
     * The TextField used for entering the player's name.
     */
    @FXML
    private TextField nameTextField = new TextField();

    /**
     * The CheckBox used for enabling or disabling accessibility options.
     */
    @FXML
    private CheckBox accessibilityCheckBox = new CheckBox();

    /**
     * The Color representing the chosen game color.
     */
    private Color gameColor;

    /**
     * The Stage for the game.
     */
    private Stage stage;

    /**
     * Handles the "Enter" button click event.
     * Loads the game view, sets up the game stage, and initializes the game with user preferences.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If an error occurs while loading the game view.
     */
    public void onEnterButtonClick(ActionEvent event) throws IOException {
        // Load in the game view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Parent root = fxmlLoader.load();
        GameController gameController = fxmlLoader.getController();
        // Set up the stage for the game
        setupGameStage(root,event);
        // Set the correct game level
        if(event.getSource() == button1){
            gameController.setLevel(1);
        } else{
            gameController.setLevel(2);
        }
        // Get the user color option
        setColorFromColorPicker();
        // Set the game to user color
        gameController.setGameColor(gameColor);
        // Get username from the text field
        String name = nameTextField.getText();
        // Set username
        gameController.setPlayerName(name);
        // Initialise the game
        gameController.initialize(stage);
    }

    /**
     * Handles the "Accessibility Option" checkbox.
     * Sets the color to yellow for high visibility if the checkbox is selected.
     */
    @FXML
    private void accessibilityOption() {
        if (accessibilityCheckBox.isSelected()) {
            // Set the color to yellow for high visibility
            setColor(Color.YELLOW);
            colorPicker.setValue(Color.YELLOW);
        }
    }

    /**
     * Sets up the game stage with the provided root node and closes the current stage.
     *
     * @param root The root node of the game view.
     * @param event The Event that triggered the stage setup.
     */
    private void setupGameStage(Parent root, Event event) {
        // Create and initialise a new stage
        stage = new Stage();
        stage.setTitle("SnakeGame");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Close the current stage.
        currentStage.close();
    }

    /**
     * Changes the background color of UI elements based on the chosen color.
     *
     * @param existingCss The existing CSS styles of the UI element.
     * @param chosenColor The color chosen by the user.
     * @return The updated CSS styles with the new color property.
     */
    public String changeColour(String existingCss, Color chosenColor) {
        // Remove current background color
        existingCss = existingCss.replaceAll("-fx-background-color\\s*:\\s*#[0-9a-fA-F]+\\s*;", "");
        String newColorCss = String.format("-fx-background-color: #%02x%02x%02x;",
                (int) (chosenColor.getRed() * 255),
                (int) (chosenColor.getGreen() * 255),
                (int) (chosenColor.getBlue() * 255));
        // Return the existing CSS with the new color property
        return existingCss + newColorCss;
    }

    /**
     * Gets the color selected from the color picker and sets the user color accordingly.
     */
    public void setColorFromColorPicker() {
        Color chosenColor = colorPicker.getValue();
        // Set the user color to the color in the color picker
        setColor(chosenColor);
    }

    /**
     * Sets the color of UI elements based on the chosen color.
     *
     * @param chosenColor The color chosen by the user.
     */
    public void setColor(Color chosenColor) {
        // Apply the updated CSS to the buttons and texts
        button1.setStyle(changeColour(button1.getStyle(),chosenColor));
        button2.setStyle(changeColour(button2.getStyle(),chosenColor));
        headingText.setFill(chosenColor);
        highScoreText.setFill(chosenColor);
        // Change the game color
        gameColor = chosenColor;
    }

    /**
     * Displays top scores on the score board text area.
     */
    public void addScores(){
        scoreBoard.appendText(ScoreUtil.getTopScores());
    }
}
