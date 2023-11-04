package UserInterface;

import java.awt.Cursor;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	
	// Global GameScreen Variable
	
	GameScreen gs;
	
	//  Create Constructor
	
	public GameWindow() {
		
		// Set Title Frame using SuperClass
		
		super("GalaxyShooter");
		
		//  Setting 
		
		// FrameSize
		this.setSize(600,600);
		
		// Exit On Close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set Location Frame to Center Your Screen 
		this.setLocationRelativeTo(null);
		
		// Set Resizable to false  -- user cannot use resize this
		this.setResizable(false);
		
		
		// Create Object GameScreen And Insert GameScreen Panel To this Frame
		gs = new GameScreen();
		
		
		// Go Adding GameScreen to frame
		this.add(gs);
		
		
	}

	public static void main(String[] args) {
		
		// Create Object this class
		
		GameWindow gw = new GameWindow();
		
		// Show GameWindow
		
		gw.setVisible(true);
		
		// Object GameScreen Call Method Start Game in this Class
		
		gw.startGame();

	}
	
	
	// Method trigger use GameScreen for call function run thread  ( StartGame )
	public void startGame() {
		gs.startGame();
	}

}
