package ObjectGame;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class ManagerEnemy {
	
	
	// declare variable
	private List<Enemy> enemies = new CopyOnWriteArrayList<>();

	private List<Energy> energies = new CopyOnWriteArrayList<>();

	private Random random;
	private long lastSpawnTime = 0;
	private long Cooldown = 3000; // Cooldown duration in milliseconds (3 seconds)
	private boolean isSpawnTime = false;
	
	
	// Object SpaceShip
	private SpaceShip sp;
	
	
	
	// constructor
	public ManagerEnemy(SpaceShip sp) {
		
		random = new Random();
		this.sp = sp;
	}
	
	
	// method reset enemy
	
	public void reset() {
		this.enemies.clear();
		this.energies.clear();
	}
	
	
	// respawn enemy run away ...
	public void respawnEnemy() {
		
        long currentTime = System.currentTimeMillis();
        
        
        // check cooldown for spawn
        
        if (currentTime - lastSpawnTime >= Cooldown) {
        	
            lastSpawnTime = currentTime;
            
    		if(enemies.size() == 0) {

    			for(int i = 0; i < random.nextInt((this.sp.getDestory_enemies() + 10) / 10 ) + random.nextInt(10); i++) {
    				enemies.add(new Enemy(random.nextInt(500), 65, (random.nextInt(3) + 1) , (random.nextInt(15) + 1)));
    			}
    			
    		}else {
    			enemies.add(new Enemy(random.nextInt(500), 65 , (random.nextInt(3) + 1) , (random.nextInt(25) + 1)));
    		}
    		
			System.out.println("Enemies Respawn ...");
        }
		
	}
	
	
	// move enemy to player


	public void moveAllEnergy(){
		if(energies.size() == 0) return;

		for(int i = energies.size() - 1; i >= 0  ;i--) {

			try {
				Energy energy = energies.get(i);

				if(energy != null) {
					energy.setPositionY(energy.getPositionY() + 3);
					if(energy.getPositionY() >= 550) {
						int rand = random.nextInt(4) + 1;
						this.sp.setScore(this.sp.getScore() - rand );
						this.sp.setHealth(this.sp.getHealth() - rand );
						this.sp.setColorAlertMessage(Color.red);
						this.sp.setAlertMessage("-" + rand + "HP");
						energies.remove(i);
					}
				}
			} catch (ArrayIndexOutOfBoundsException err) {
				return;
			}


		}

	}
	public void moveAllEnemy() {
		
        if(enemies.size() == 0) return;
        
		for(int i = enemies.size() - 1; i >= 0  ;i--) {
			
			try {
				Enemy enemy = enemies.get(i);
				
				if(enemy != null) {
					enemy.setPositionY(enemy.getPositionY() + enemy.getSpeed());
					if(enemy.getPositionY() >= 550) {
						
						int rand = random.nextInt(4) + 1;
						this.sp.setScore(this.sp.getScore() - rand );
						this.sp.setHealth(this.sp.getHealth() - rand );
						this.sp.setColorAlertMessage(Color.red);
						this.sp.setAlertMessage("-" + rand + "HP");
						enemies.remove(i);
					}
				}
			} catch (ArrayIndexOutOfBoundsException err) {
				return;
			}


		}
	}


	public void playDeadSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("dead.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//  hit enemy  player score pluse +++;
	
	public void hitEnemy() {
		  for (int i = this.sp.bullets.size() - 1; i >= 0; i--) {
			  
			  
					try {
			            	Bullet bullet = this.sp.bullets.get(i);
				            if(enemies.size() == 0) return;
				            
				            for (int j = enemies.size() - 1; j >= 0; j--) {
				                Enemy enemy = enemies.get(j);
				                
				                if(enemy != null) {
					               if (bullet.intersects(enemy)) {
					                	
					                    this.sp.bullets.remove(i);
					                    
					    				enemy.setHealth(enemy.getHealth() - (random.nextInt(3) + 1));
					    				
					    				if(enemy.getHealth() <= 0) {
					    					enemies.remove(j);
						                    this.sp.setScore(this.sp.getScore() + 10);
						                    this.sp.setDestory_enemies(this.sp.getDestory_enemies() + 1);
											playDeadSound();

											double rand = random.nextDouble(10) + 1;
											if( rand >= (double)8.20 ){
												System.out.println("Energy Spawn ...");
												energies.add(new Energy(enemy.getPositionX() , enemy.getPositionY()));
											}

					    				}
					               }
				                }
				                

				            }
						} catch (ArrayIndexOutOfBoundsException err)
					{
						return;
					}
		            

		  }

	}


	public void playBombSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("bomb.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void energyHitSpaceShip(){
		if(energies.size() == 0 ) return;

		for (int i = energies.size() - 1; i >= 0; i--) {
			Energy energy = energies.get(i);
			if (energy.intersects(this.sp)){

				if(this.sp.getHealth() + energy.getHeal() >= 100){
					this.sp.setHealth(100);
					this.sp.setColorAlertMessage(Color.green);
					this.sp.setAlertMessage("Your hp is full !");
				}else{
					this.sp.setHealth(this.sp.getHealth() + energy.getHeal());
					this.sp.setColorAlertMessage(Color.green);
					this.sp.setAlertMessage("+" + energy.getHeal() + "HP");
				}
				energies.remove(i);
			}
		}
	}



	// enemy hit  player hp decrease

	public void enemyHitSpaceShip() {
		if (enemies.size() == 0) return;

		for (int i = enemies.size() - 1; i >= 0; i--) {
			Enemy enemy = enemies.get(i);

			if (enemy.intersects(this.sp)) {
				int scoreChange = random.nextInt(4) + 1;
				int newScore = this.sp.getScore() - scoreChange;
				if (newScore < 0) {
					newScore = 0;  // Ensure score doesn't go below 0
				}
				this.sp.setScore(newScore);

				int healthChange = random.nextInt(4) + 20;
				int newHealth = this.sp.getHealth() - healthChange;
				if (newHealth < 0) {
					newHealth = 0;  // Ensure health doesn't go below 0
				}
				this.sp.setHealth(newHealth);

				enemies.remove(i);
				int rand = random.nextInt(10) + 1;
				this.sp.setColorAlertMessage(Color.red);
				this.sp.setAlertMessage("-" + rand + "HP");
				playBombSound();
			}
		}
	}



	// draw object 
	
	public void drawAllEnemy(Graphics g2) {
		
	    for (Enemy enemy : enemies) {
	    	enemy.draw(g2);
	    }

		for(Energy energy : energies){
			energy.draw(g2);
		}
	    
	}
	

}
