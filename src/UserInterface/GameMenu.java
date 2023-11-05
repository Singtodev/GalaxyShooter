package UserInterface;

import Helper.GetResourceImage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Point;
import javax.swing.JFrame;

public class GameMenu implements MouseListener {



    BufferedImage Logo , Start , Quit;

    GameScreen gs;

    public GameMenu(GameScreen gs){
        this.gs = gs;
        Logo = GetResourceImage.ImageCall("/images/logo.png");
        Start = GetResourceImage.ImageCall("/images/start.png");
        Quit = GetResourceImage.ImageCall("/images/quit.png");
    }


    public void drawGameMenu(Graphics g2){

        // draw logo
        g2.drawImage(this.Logo, 50, 50, 500, 150, null);

        // draw start
        g2.drawImage(this.Start, 200, 250, 180, 50, null);

        // draw quit

        g2.drawImage(this.Quit, 250, 350, 75, 50, null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int mouseX = e.getX();
        int mouseY = e.getY();

        // Check if the click was inside the boundaries of the button
        if (mouseX >= 200 && mouseX <= 380 && mouseY >= 250 && mouseY <= 300) {
            // Handle the start button click
            this.gs.setShowMenuGame(false);
            System.out.println(this.gs.getShowMenuGame());
            System.out.println("Start button clicked.");

            Cursor invisibleCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "invisibleCursor"
            );

            this.gs.setCursor(invisibleCursor);

        } else if (mouseX >= 250 && mouseX <= 325 && mouseY >= 350 && mouseY <= 400) {
            // Handle the quit button click
            System.out.println("Quit button clicked.");
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
