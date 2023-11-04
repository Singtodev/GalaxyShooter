package Helper;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import UserInterface.GameWindow;

public class GetResourceImage {
	
	//  Method for load static image in your project
	//  getting image with buffer image and return image to use;
	
	public static BufferedImage ImageCall(String path) {
		
		// declare 
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(GameWindow.class.getResource(path));
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		
		return img;
	}

}
