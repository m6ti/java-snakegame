package com.psymk6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main2 extends Application {

    public static Stage menuStage;
    public static Stage gameStage;

    public static void main(String[] args) {

        launch(args);
//
//        SnakeModel snakeModel = new SnakeModel(100, 100);
//        View view = new View(snakeModel);
//        PlayController controller = new PlayController(snakeModel,view);
//
//        view.loadFrame(controller);
//        new ThreadController(view).run();
//
//        MusicPlayer.getMusicPlay("src/main/resources/com.psymk6.controllers.assets/music/frogger.mp3");
    }
    @Override
    public void start(Stage stage) throws Exception {
        menuStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load(),500,500);

        // Create the scene
        stage.setTitle("SnakeGame");
        // Set the scene on the stage
        stage.setScene(scene1);
        // Show the stage
        stage.show();
    }
    public static Stage getGameStage() {
        return gameStage;
    }
    public static void setGameStage(Stage gameStage) {
        Main2.gameStage = gameStage;
    }
    public static Stage getMenuStage() {
        return menuStage;
    }

}