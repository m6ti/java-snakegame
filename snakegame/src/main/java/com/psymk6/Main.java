package com.psymk6;


import com.psymk6.util.GameUtil;
import com.psymk6.util.ImageUtil;
import com.psymk6.util.MusicPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load(),700,500);
        stage.setTitle("SnakeGame");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene1);
        stage.getIcons().add(ImageUtil.getImage("snake-head-right"));
        stage.show();
    }
}