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

public class ViewController {
    @FXML
    private TextArea scoreBoard = new TextArea();
    @FXML
    private Text headingText = new Text();
    @FXML
    private Text highScoreText = new Text();
    @FXML
    private Button button1 = new Button();
    @FXML
    private Button button2 = new Button();
    @FXML
    private ColorPicker colorPicker = new ColorPicker();
    @FXML
    private TextField nameTextField = new TextField();
    @FXML
    private CheckBox accessibilityCheckBox = new CheckBox();
    private Color gameColor;
    private Stage stage;
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
    @FXML
    private void accessibilityOption() {
        if (accessibilityCheckBox.isSelected()) {
            // Set the color to yellow for high visibility
            setColor(Color.YELLOW);
            colorPicker.setValue(Color.YELLOW);
        }
    }

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
    public void setColorFromColorPicker() {
        Color chosenColor = colorPicker.getValue();
        // Set the user color to the color in the color picker
        setColor(chosenColor);
    }
    public void setColor(Color chosenColor) {
        // Apply the updated CSS to the buttons and texts
        button1.setStyle(changeColour(button1.getStyle(),chosenColor));
        button2.setStyle(changeColour(button2.getStyle(),chosenColor));
        headingText.setFill(chosenColor);
        highScoreText.setFill(chosenColor);
        // Change the game color
        gameColor = chosenColor;
    }
    public void addScores(){
        scoreBoard.appendText(ScoreUtil.getTopScores());
    }
}
