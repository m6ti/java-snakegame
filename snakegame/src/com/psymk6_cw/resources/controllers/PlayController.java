package com.psymk6.resources.controllers;

import com.psymk6.resources.models.SnakeModel;
import com.psymk6.resources.views.View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayController implements KeyListener {

    View view;
    SnakeModel snakeModel;


    public PlayController(SnakeModel snakeModel, View view) {
        this.snakeModel = snakeModel;
        this.view = view;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        snakeModel.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}
