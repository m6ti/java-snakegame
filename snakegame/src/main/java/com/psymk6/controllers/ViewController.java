package com.psymk6.controllers;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;

import static javafx.scene.paint.Color.TRANSPARENT;

public class ViewController {
    Stage stage;
    public void onEnterButtonClick(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Parent root = fxmlLoader.load();

        GameController gameController = fxmlLoader.getController();

        stage = new Stage();
        stage.setTitle("SnakeGame");
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        gameController.initialize(stage);

        stage.setOnCloseRequest(e -> {
            gameController.shutDown();
        });

    }

    public static void deathScreen(Image image, StackPane stackPane, MusicController musicController, GameController gameController) {
        ImageView failImageView = new ImageView(image);
        failImageView.setOpacity(0);
        stackPane.getChildren().add(failImageView);
        stackPane.setAlignment(Pos.CENTER); // Adjust the alignment as needed
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), failImageView);
        fadeTransition.setToValue(1);

        fadeTransition.setOnFinished(event -> showRetryExitButtons(stackPane,musicController,gameController));

        fadeTransition.play();
    }

    private static Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 16; -fx-background-color: #FF0000; -fx-text-fill: white;");
        return button;
    }
    private static void showRetryExitButtons(StackPane stackPane, MusicController musicController, GameController gameController) {
        VBox buttonBox = new VBox(10);


        Button retryButton = createStyledButton("Retry");
        retryButton.setOnAction(event -> gameController.restartGame());

        Button exitButton = createStyledButton("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
            musicController.stopPlayer();
        });

        Text highScore = new Text("Highscore: 0");  // Add logic to retrieve and display highscore
        highScore.setFont(new Font("consolas",16));
        highScore.setFill(Color.WHITE);

        buttonBox.getChildren().addAll(retryButton, exitButton, highScore);

        stackPane.getChildren().add(buttonBox);
        buttonBox.setAlignment(Pos.CENTER);
    }
}
