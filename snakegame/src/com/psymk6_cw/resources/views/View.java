package com.psymk6.resources.views;

import com.psymk6.resources.controllers.PlayController;
import com.psymk6.resources.models.Food;
import com.psymk6.resources.models.SnakeModel;
import com.psymk6.resources.util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class View extends JPanel{
    public SnakeModel mySnake;
    public Food food = new Food();
    public JFrame jFrame = new JFrame();
    public Image background = ImageUtil.images.get("UI-background");
    public Image fail = ImageUtil.images.get("game-scene-01");
    public View(SnakeModel mySnake){
        this.mySnake = mySnake;
        jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(("/com/psymk6/assets/images/snake-logo.png")));
    }

    public void loadFrame(PlayController controller)
    {
        /*
         * Komið í veg fyrir að myndin blikki.
         */
        this.setDoubleBuffered(true);
        jFrame.add(this);
        jFrame.addKeyListener(controller);

        jFrame.setTitle("Snakee Yipee");
        jFrame.setSize(870, 560);
        jFrame.setLocationRelativeTo(null);
        jFrame.addWindowListener(new WindowAdapter()// loka
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        jFrame.setVisible(true);
    }

    public void drawScore(Graphics g)
    {
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        g.setColor(Color.MAGENTA);
        g.drawString("SCORE : " + mySnake.score, 20, 40);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(background, 0, 0, null);

        // Ákveða stöðu leiksins // 'Determine the state of the game'
        if (mySnake.l)
        {
            mySnake.draw(g);
            if (food.l)
            {
                food.draw(g);
                food.eaten(mySnake);
            } else
            {
                food = new Food();
            }
        } else
        {
            g.drawImage(fail, 0, 0, null);
        }
        drawScore(g);
    }
}
