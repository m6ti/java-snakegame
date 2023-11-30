package com.psymk6.controllers;

import com.psymk6.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    protected void onEnterButtonClick() throws IOException {
        Stage stage = Main.getMenuStage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load(),500,500);

        Stage gameStage = new Stage();
        Main.setGameStage(gameStage);
        gameStage.setTitle("SnakeGame");
        // Set the scene on the second stage
        gameStage.setScene(scene2);
        stage.hide();
        //   Show the second stage
        gameStage.show();
    }
}