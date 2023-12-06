package com.psymk6.abstracts;

public abstract class LiveCanvasObjectModel extends CanvasObjectModel{
    protected boolean isAlive;
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
