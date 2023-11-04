package ObjectGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	
	// declare variable
	private int positionX = 0;
	private int positionY = 0;
	
	// constructor
	public Bullet(int positionX , int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	// draw method
	public void draw(Graphics g2) {
		g2.setColor(Color.RED);
	    g2.fillRect(this.getPositionX(), this.getPositionY(), 5, 10);
	}
	
	// get position x
	public int getPositionX() {
		return positionX;
	}


	// set position x
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}


	// get position y
	public int getPositionY() {
		return positionY;
	}


	// set position y
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	
	
	// cal Rectangle getBound for cal hit enemy
    public Rectangle getBounds() {
        return new Rectangle(this.getPositionX(), this.getPositionY(), 30, 20);
    }
    
    
    // cal hit enemy

    public boolean intersects(Enemy enemy) {
        return getBounds().intersects(enemy.getBounds());
    }


}
