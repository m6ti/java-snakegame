package com.psymk6.abstracts;

import com.psymk6.controllers.GameController;

public abstract class ClickableButton {
    public abstract void setClick(GameController gameController, double mouseX, double mouseY);
    protected boolean isMouseInsideButton(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
