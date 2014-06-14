import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;

public class Archer extends Player{
    protected Item shield = null;
    public Archer(){super();}
    public Archer(String imageLocation, int x, int y){
	super(imageLocation,x,y);
	try{
	 //get all the swordsman graphics
	 //if image isn't already that image, set it to the image
 	//to prevent unnecessary resetting of images (gifs would stutter)
	down = ImageIO.read(new File("sprites/swordsman down.png"));
	up = ImageIO.read(new File("sprites/swordsman up.png"));
	left = ImageIO.read(new File("sprites/swordsman left.png"));
	right = ImageIO.read(new File("sprites/swordsman right.png"));
	downAnimated = (new ImageIcon("sprites/swordsman down animated.gif").getImage());
	upAnimated = (new ImageIcon("sprites/swordsman up animated.gif").getImage());
	leftAnimated =(new ImageIcon("sprites/swordsman left animated.gif").getImage());
	rightAnimated =(new ImageIcon("sprites/swordsman right animated.gif").getImage());
	}catch(Exception e){
	    Utilities.showErrorMessage(null,e);
	}
    }

}
