package ObjectGame;

import Helper.GetResourceImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Energy {

    int speed = 0;
    int heal = 1;
    int positionX  = 0 , positionY  = 0;
    Random random;


    BufferedImage EnergyImage;

    public Energy(int positionX , int positionY){

        // set default position
        this.positionX = positionX;
        this.positionY = positionY;

         random = new Random();

         // get started random heal for spaceship
         this.heal =  random.nextInt(3) + 1;

         // get started random speed;
         this.speed = random.nextInt(3) + 1;

         // getting image
        EnergyImage = GetResourceImage.ImageCall("/images/energy.png");

    }


    public void draw(Graphics g2){

        g2.setColor(Color.green);
        g2.drawImage(EnergyImage, this.getPositionX(), this.getPositionY(), 40, 40, null);
        g2.drawString("Energy + " + this.getHeal(), this.getPositionX() + 5, (this.getPositionY() - 40 + 20));

    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.getPositionX(), this.getPositionY(), 40 + 10, 40);
    }


    // cal hit player
    public boolean intersects(SpaceShip sp) {
        return getBounds().intersects(sp.getBounds());
    }
}
