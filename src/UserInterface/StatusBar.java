package UserInterface;

import java.awt.Color;
import java.awt.Graphics;

import ObjectGame.SpaceShip;

public class StatusBar {
	
	
	// this is a class for show status of the game 
	// show player detail (hp ,score , destory_enemy )

	// declare variable
	private GameScreen gs;
	private SpaceShip sp;

	
	// declare constructor
	public StatusBar(GameScreen gs , SpaceShip sp) {
		this.gs = gs;
		this.sp = sp;
	}
	
	
	// method draw for write ui
	public void draw(Graphics g2) {
		
		//set color white
		g2.setColor(Color.white);
		
		// write line 
		g2.drawLine(0, 60 , gs.getWidth(), 60);
		
		// write content 
		g2.drawString("Destory Enemy : " + this.sp.getDestory_enemies() , 20, 25);
		g2.drawString("Hp : " + this.sp.getHealth() , 20, 45);
		g2.drawString("Ammo : " + this.sp.getAmmo() , 100, 45);
		g2.drawString("Score : " + this.sp.getScore(), 500, 25);
		g2.drawString("Level : " + (int)((this.sp.getScore() + 223) / 223), 500, 45);
		
		// check player show message if have message then go write else go pass
		// note enemy hit player show damage ...
		
		if(!this.sp.getAlertMessage().equals("")) {
			g2.setColor(this.sp.getColorAlertMessage());
			g2.drawString(this.sp.getAlertMessage(), 20, 80);
		}
	}

}
