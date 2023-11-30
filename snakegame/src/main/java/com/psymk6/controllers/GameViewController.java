package com.psymk6.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;

import static java.lang.System.exit;

public class GameViewController {
    public Canvas gameCanvas;

    //    @FXML
//    private Canvas gameCanvas;
//    @FXML
//    public void initialize() {
//        // Get the graphics context of the canvas
//        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
//
//        // Draw on the canvas, for example, a rectangle
//        gc.fillRect(10, 10, 50, 50);
//    }
    protected void onHelloButtonClick() throws IOException {
        Platform.exit();
    }
}
