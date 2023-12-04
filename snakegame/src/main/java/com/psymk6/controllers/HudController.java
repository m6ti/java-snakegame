package com.psymk6.controllers;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class HudController {
    //Heads Up Display Controller
    HBox hud;

    VBox viewBox;
    public HudController(HBox hud, VBox viewBox) {
        this.viewBox = viewBox;
        this.hud = hud;

        hud.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        hud.setPadding(new Insets(10));
        hud.setSpacing(100);
    }

    public void draw() {

    }
}
