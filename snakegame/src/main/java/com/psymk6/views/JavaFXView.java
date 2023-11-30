package com.psymk6.views;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.System.exit;


public class JavaFXView extends Application{

        private Scene scene1, scene2;

        public static void main(String[] args) {
                launch(args);
        }
        public void start(Stage primaryStage) throws Exception {
                primaryStage.setTitle("SnakeGame");

                //Menu view
                Label label1= new Label("Menu");
                Button button1= new Button("Start Game");
                Button button2= new Button("Exit Game");
                button1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                                primaryStage.setScene(scene2);
                        }
                });
                button2.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {exit(0);}
                });

                VBox layout1 = new VBox(20);
                layout1.setAlignment(Pos.CENTER);
                layout1.getChildren().addAll(label1, button1,button2);
                scene1= new Scene(layout1, 300, 400);

                scene2 = new Scene(layout1,800,800);

        }

}
