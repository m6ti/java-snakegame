package com.psymk6.controllers;

import com.psymk6.util.ImageUtil;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.io.IOException;

import static java.lang.System.exit;

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

    public static void deathScreen(Image image, StackPane stackPane) {
        ImageView failImageView = new ImageView(image);
        failImageView.setOpacity(0);
        stackPane.getChildren().add(failImageView);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), failImageView);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

}
