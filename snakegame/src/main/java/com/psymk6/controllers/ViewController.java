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
    private Text scoreText = new Text();
    @FXML
    private Button button1 = new Button();
    @FXML
    private Button button2 = new Button();
    @FXML
    private ColorPicker colorPicker = new ColorPicker();
    @FXML
    private TextField nameTextField = new TextField();
    private Color gameColor;
    private Stage stage;
    public void onEnterButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Parent root = fxmlLoader.load();

        GameController gameController = fxmlLoader.getController();
        setupGameStage(root,event);

        if(event.getSource() == button1){
            gameController.setLevel(1);
            System.out.println("Level 1");
        }else{
            gameController.setLevel(2);
            System.out.println("Level 2");
        }

        setColor();
        gameController.setGameColor(gameColor);

        String name = nameTextField.getText();
        gameController.setPlayerName(name);

        gameController.initialize(stage);
    }
    private void setupGameStage(Parent root, Event event) {
        stage = new Stage();
        stage.setTitle("SnakeGame");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
    public String changeColour(String existingCss, Color chosenColor) {
        existingCss = existingCss.replaceAll("-fx-background-color\\s*:\\s*#[0-9a-fA-F]+\\s*;", "");

        String newColorCss = String.format("-fx-background-color: #%02x%02x%02x;",
                (int) (chosenColor.getRed() * 255),
                (int) (chosenColor.getGreen() * 255),
                (int) (chosenColor.getBlue() * 255));
        // Combine the existing CSS with the new color property
        return existingCss + newColorCss;
    }
    public void setColor() {
        Color chosenColor = colorPicker.getValue();
        // Apply the updated CSS to the button
        button1.setStyle(changeColour(button1.getStyle(),chosenColor));
        button2.setStyle(changeColour(button2.getStyle(),chosenColor));
        headingText.setFill(chosenColor);
        scoreText.setFill(chosenColor);
        gameColor = chosenColor;
    }
    public void addScores(){
        scoreBoard.appendText(ScoreUtil.getTopScores());
    }
}
