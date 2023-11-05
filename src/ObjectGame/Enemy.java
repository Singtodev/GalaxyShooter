package ObjectGame;

import Helper.GetResourceImage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;


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

	BufferedImage enemyPicture;


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
		
		int size = random.nextInt(40) + 20;
		
		this.width = size;
		this.height = size;

		enemyPicture = randomEnemyPicture();
	}



	public BufferedImage randomEnemyPicture(){
		int ran = random.nextInt(5)  + 1;

		switch (ran){
			case 1:
				return GetResourceImage.ImageCall("/images/meteor.png");
			case 2:
				return GetResourceImage.ImageCall("/images/meteor_2.png");
			case 3:
				return GetResourceImage.ImageCall("/images/alien.png");
			case 4:
				return GetResourceImage.ImageCall("/images/spaceship_1.png");
			case 5:
				return GetResourceImage.ImageCall("/images/spaceship_2.png");
			default:
				break;
		}

		return null;
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
		// Load the enemy image

		g2.drawImage(enemyPicture, this.getPositionX(), this.getPositionY(), this.width, this.height, null);

		// Draw health
		g2.setColor(Color.red);
		g2.drawString("" + this.getHealth(), this.getPositionX() + 5, (this.getPositionY() - this.height + 20));
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
