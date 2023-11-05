package UserInterface;

import Helper.GetResourceImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameMenu {



    BufferedImage Logo , Start , Quit;

    public GameMenu(){
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
}
