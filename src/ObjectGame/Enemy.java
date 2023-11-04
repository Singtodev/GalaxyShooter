package ObjectGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy {
	
	
	// declare variable
	private int health = 10;
	private int positionX = 0;
	private int positionY = 0;
	private int speed = 0;
	private int width = 20;
	private int height = 20;
	
	
	int r , g , b  = 0;
	
	public Random random;


	// constructor
	public Enemy(int positionX , int positionY , int speed , int health) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.speed = speed;
		this.health = health;

		this.random = new Random();
		this.r = random.nextInt(256);
		this.g = random.nextInt(256);
		this.b = random.nextInt(256);
		
		int size = random.nextInt(10) + 20;
		
		this.width = size;
		this.height = size;
	}

	// get health
	public int getHealth() {
		return health;
	}

	// set health
	public void setHealth(int health) {
		this.health = health;
	}

	// get speed
	public int getSpeed() {
		return speed;
	}

	// set speed
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	// get position
	public int getPositionX() {
		return positionX;
	}
	
	// draw 
	
	public void draw(Graphics g2) {

		g2.setColor(new Color(this.r,this.g,this.b));
	    g2.fillRect(this.getPositionX(), this.getPositionY(), this.width, this.height);
	    g2.setColor(Color.green);
	    g2.drawString(""+ this.getHealth(), this.getPositionX() + 5 , (this.getPositionY() - this.height + 20));
	}

	
	// set position x
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}


	// get position y
	public int getPositionY() {
		return positionY;
	}


	// get position y
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	
	// cal Rectangle getBound for cal hit player
    public Rectangle getBounds() {
        return new Rectangle(this.getPositionX(), this.getPositionY(), this.width + 10, this.height);
    }
    
    
    // cal hit player
    public boolean intersects(SpaceShip sp) {
        return getBounds().intersects(sp.getBounds());
    }

}
