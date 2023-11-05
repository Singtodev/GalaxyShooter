package UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Point;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import javax.swing.JPanel;

import ObjectGame.Enemy;
import ObjectGame.ManagerEnemy;
import ObjectGame.SpaceShip;




public class GameScreen extends JPanel implements Runnable , KeyListener {


	// Declare variable
	
	private Thread thread , timerThread;
	private SpaceShip spaceship;
	private StatusBar statusbar;
	private ManagerEnemy manager_enemies;

	
	// Runnable want to unique serial generate this
	
	private static final long serialVersionUID = 1L;


	boolean showMenuGame = true;


	GameMenu gm;


	public void playBackgroundSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("bg.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the background sound continuously
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public GameScreen() {
		
		// Create thread in this class using global variable
		
		thread = new Thread(this);


		// Create Game Menu Screen

		gm = new GameMenu(this);
		
		// I want to Create Galaxy Space Same this Panel
		// Set GameScreen Background to Color Black
		
		this.setBackground(Color.black);
		
		// Creat Object SpaceShip
		spaceship = new SpaceShip(this);
		
		// Create Object StatusBar
		statusbar = new StatusBar(this , spaceship);
		
		// Create Object Manager Enemies
		manager_enemies = new ManagerEnemy(spaceship);

		playBackgroundSound(); // background.wav
		
		this.setFocusable(true);  // Ensure the component can receive focus
		this.requestFocusInWindow(); 
		
		
		// add event trigger
		this.addKeyListener(this);
		this.addKeyListener(spaceship);
		this.addMouseListener(spaceship);
		this.addMouseMotionListener(spaceship);

		//invisibleCursor
		Cursor invisibleCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "invisibleCursor"
		);
		this.setCursor(invisibleCursor);

		this.addMouseListener(gm);

		
	}
	
	// Method for run this game
	public void startGame() {
		this.showMenuGame = true; // Show the game menu initially
		this.repaint(); // Repaint the screen to show the menu

		// controll thread go run....
		thread.start();
		
	}

	public void playGameOverSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("gameover.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private boolean gameoverSoundPlayed = false;

	public boolean gameIsOver() {
		if (this.spaceship.getHealth() <= 0 && !gameoverSoundPlayed) {
			playGameOverSound(); // Play game over sound
			gameoverSoundPlayed = true; // Set the flag to true to indicate the sound has been played
			return true;
		}
		return this.spaceship.getHealth() <= 0;
	}

	
	// If startGame is running call method thread.start()
	// is mean go run function run below ...

	@Override
	public void run() {	
		
		
		// Create Loop For Update Repaint or Todo Something..
		
		while(true) {
				try {


					// check game is over ?
					
					if(!this.gameIsOver() && !this.showMenuGame) {

						// Game is running not end 
						// TODO
						
						// update rule object
						spaceship.update();
						
						// update enemies auto respawn
						manager_enemies.respawnEnemy();
						
						// update and move enemy 
						manager_enemies.moveAllEnemy();
						
						// update check bullet hit enemy
						
						manager_enemies.hitEnemy();
						
						// updat check enemy hit spaceship
						manager_enemies.enemyHitSpaceShip();
					}
					

					
					Thread.sleep(20);
					
					// Repaint 
					
					repaint();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
		}

	}
	
	// Method this is mean paint everything inside this
	// Example SpaceShip or Alien or Item if you want to paint this..
	
	@Override
	protected void paintComponent(Graphics g2){
		

		
        g2.setColor(Color.black);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());



		// Go draw some thing...

		if (this.getShowMenuGame()) {
			this.gm.drawGameMenu(g2);
			return;
		}else{
			this.removeMouseListener(gm);
		}
        
        // check game is over ?
		if(!this.gameIsOver() && !this.showMenuGame) {


			// Game is running not end 
			// TODO
			
	        // draw status bar
	        statusbar.draw(g2);
	        
			// draw space ship
			spaceship.draw(g2);
			
			// draw enemies
			manager_enemies.drawAllEnemy(g2);
			
		}else {
			
			// draw screen game over 
			
			
			g2.setColor(Color.red);
			g2.setFont(new Font("Courier New",1,30));
			g2.drawString("Game Over", 200, 150);
			
			g2.setColor(Color.white);
			g2.setFont(new Font("Courier New",1,12));

			g2.drawString("Your Score : " + this.spaceship.getScore(), 200, 200);
			g2.drawString("Destory Enemies : " + this.spaceship.getDestory_enemies(), 200, 240);
			g2.drawString("Press Enter to play again !.", 200, 300);

			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}

		
		// Repaint
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// check is game over and check user wana play again ?
		// user press key enter for play
		
		if(this.gameIsOver() && !this.showMenuGame && e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.spaceship.setHealth(100);
			this.spaceship.setScore(0);
			this.spaceship.setDestory_enemies(0);
			this.spaceship.setAmmo(50);
			this.spaceship.setAlertMessage("");
			this.manager_enemies.reset();
			this.manager_enemies.respawnEnemy();
			this.spaceship.rebirth();
			this.gameoverSoundPlayed = false;
			Cursor invisibleCursor = Toolkit.getDefaultToolkit().createCustomCursor(
					new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "invisibleCursor"
			);
			this.setCursor(invisibleCursor);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public boolean getShowMenuGame() {
		return this.showMenuGame;
	}

	public void setShowMenuGame(boolean showMenuGame) {
		this.showMenuGame = showMenuGame;
	}
}

