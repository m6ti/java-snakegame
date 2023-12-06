    package com.psymk6;


    import com.psymk6.controllers.ViewController;
    import com.psymk6.util.GameUtil;
    import com.psymk6.util.ImageUtil;
    import com.psymk6.util.MusicPlayer;
    import javafx.application.Application;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.ListView;
    import javafx.scene.control.TextArea;
    import javafx.scene.layout.VBox;
    import javafx.stage.Stage;
    import javafx.stage.StageStyle;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;

    import java.io.IOException;
    import java.util.List;

    public class Main extends Application {
        public static void main(String[] args) {
            launch(args);
        }
        @Override
        public void start(Stage stage) throws Exception {
            // Set up the menu view
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene1 = new Scene(root,700,500);
            stage.setTitle("SnakeGame");
            // Add high scores to the menu view
            ViewController viewController = fxmlLoader.getController();
            viewController.addScores();
            stage.setScene(scene1);
            // Set window and task bar icon
            stage.getIcons().add(ImageUtil.getImage("snake-head-right"));
            // Show to the user
            stage.show();
        }

    }