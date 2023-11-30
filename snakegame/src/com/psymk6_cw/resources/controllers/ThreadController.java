package com.psymk6.resources.controllers;

import com.psymk6.resources.views.View;

import static java.lang.Thread.sleep;

public class ThreadController implements Runnable {

    private final View view;

    public ThreadController(View view){
        this.view = view;
    }
    @Override
    public void run()
    {
        while (true)
        {
            view.repaint();
            try
            {
                sleep(30);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
