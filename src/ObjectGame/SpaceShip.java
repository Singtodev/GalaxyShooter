package ObjectGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Helper.*;
import UserInterface.GameScreen;


// Inheritance using method in ObjectAction
// this is abstract class go rewite method action example in this case spaceship can shoot


public class SpaceShip extends ObjectAction implements MouseMotionListener , MouseListener , KeyListener {

	
	
	// declare variable 
	public static int health = 100;
	public static int ammo = 50;
	public static int score = 0;
	public static int destory_enemies = 0;
	public static int positionX = 100;
	public static int positionY = 100;
	public static float base = 450;
	public static int moveBulletSpeed = 10;
	
	public static String alertMessage = "";
	
	private long lastReloadTime = 0;
	private long reloadCooldown = 3000; // Cooldown duration in milliseconds (3 seconds)
	private boolean isReloading = false;
	private Color colorAlertMessage = Color.red;
	

	BufferedImage spaceshipeImage;
	
	
	// Bullets 
	// in case use array list not work
	//	private ArrayList<Bullet> bullets = new ArrayList<>();
	
	// in case use List is work not erorr array index wrong or something ...
	public List<Bullet> bullets = new CopyOnWriteArrayList<>();
	
	// Create Constructor SpaceShip Initialized

	GameScreen gs;
	public SpaceShip(GameScreen gs) {
		this.gs = gs;
		spaceshipeImage = GetResourceImage.ImageCall("/images/spaceship.png");
	}
		
	
	// Create A Method For Draw SpaceShip
	
	public void draw(Graphics g2) {
		g2.setColor(Color.black);
		g2.drawImage(spaceshipeImage,(int)positionX,(int)positionY,100,100,null);
		this.drawAllBullet(g2);
	}
	
	
	// Create A Method For Check SpaceShip Movement
	
	
	public void update() {
		
		// SpaceShip out this map go fly to map

		if(positionY >= base) {
			positionY -= 10;
		}
		
		if(bullets.size() != 0) {
			this.moveAllBullet();
		}

		
	}
	
	public void moveAllBullet() {
		for(int i = bullets.size() - 1; i >= 0  ;i--) {
			Bullet bullet = bullets.get(i);
			bullet.setPositionY(bullet.getPositionY() - this.getMoveBulletSpeed());
			if(bullet.getPositionY() <= 50) {
				bullets.remove(i);
			}
		}
	}
	
	public void drawAllBullet(Graphics g2) {
	    for (Bullet bullet : bullets) {
	    	bullet.draw(g2);
	    }
	}
	
	

	public Rectangle getBounds() {
        return new Rectangle(this.getPositionX(), this.getPositionY(), 110, 100);
    }

    public boolean intersects(Enemy enemy) {
        return getBounds().intersects(enemy.getBounds());
    }
	
	public static int getDestory_enemies() {
		return destory_enemies;
	}


	public static void setDestory_enemies(int destory_enemies) {
		SpaceShip.destory_enemies = destory_enemies;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public boolean isReloading() {
		return isReloading;
	}


	public void setReloading(boolean isReloading) {
		this.isReloading = isReloading;
	}


	public static String getAlertMessage() {
		return alertMessage;
	}


	public static void setAlertMessage(String alertMessage) {
		SpaceShip.alertMessage = alertMessage;
	}


	public Color getColorAlertMessage() {
		return colorAlertMessage;
	}


	public void setColorAlertMessage(Color colorAlertMessage) {
		this.colorAlertMessage = colorAlertMessage;
	}


	public static int getMoveBulletSpeed() {
		return moveBulletSpeed;
	}


	public static void setMoveBulletSpeed(int moveBulletSpeed) {
		SpaceShip.moveBulletSpeed = moveBulletSpeed;
	}


	public int getAmmo() {
		return ammo;
	}


	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
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


	@Override
	public void mouseDragged(MouseEvent e) {

	}


	@Override
	public void mouseMoved(MouseEvent e) {
		
		// Update position spaceship by mouse move
		this.setPositionX(e.getX() - 50);
		this.setPositionY(e.getY() - 50);

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.gs.getShowMenuGame()) return;
		this.shoot();
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.shoot();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	// method shoot extends abstrac class write some thing in object...
	public void playShootSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("pew.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void shoot() {
		if(this.gs.getShowMenuGame()) return;
		if (this.getAmmo() != 0) {
			playShootSound(); // Play shooting sound
			this.setAmmo(this.getAmmo() - 1);
			bullets.add(new Bullet(this.positionX + 50, this.positionY));
		} else {
			this.setColorAlertMessage(Color.red);
			SpaceShip.setAlertMessage("Press R for Reload");
		}
	}



	@Override
	public void death() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void effect() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void rebirth() {
		// TODO Auto-generated method stub
		System.out.println("SpaceShip Rebirth");
		this.setColorAlertMessage(Color.green);
		this.setAlertMessage("SpaceShip is Rebirth");
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void playReloadSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("reload.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		if(this.gs.getShowMenuGame()) return;
		// keypress catch event user press keyboard  and to do
		// if user press r then..
	
		if(e.getKeyCode() == KeyEvent.VK_R) {
	        long currentTime = System.currentTimeMillis();
	        if (currentTime - lastReloadTime >= reloadCooldown) {
	            // Sufficient cooldown time has passed, allow reloading
	            lastReloadTime = currentTime;
	            this.setAmmo(50); // Reload ammo
	            this.setColorAlertMessage(Color.green);
	            SpaceShip.setAlertMessage("Reload Success");
				playReloadSound();
			} else {
	            // Inform the player that they need to wait for the cooldown
	            this.setColorAlertMessage(Color.red);
	            SpaceShip.setAlertMessage("Reloading is on cooldown .");
	        }
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}


