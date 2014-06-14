import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Bug extends Player{
  public Bug(){super();}
    public Bug(String imageLocation, int x, int y){
	super(imageLocation,x,y);
	try{
	 //get all the swordsman graphics
	 //if image isn't already that image, set it to the image
 	//to prevent unnecessary resetting of images (gifs would stutter)
	down = ImageIO.read(new File("sprites/bug down.png"));
	up = ImageIO.read(new File("sprites/bug up.png"));
	left = ImageIO.read(new File("sprites/bug left.png"));
	right = ImageIO.read(new File("sprites/bug right.png"));
	downAnimated = (new ImageIcon("sprites/bug down animated.gif").getImage());
	upAnimated = (new ImageIcon("sprites/bug up animated.gif").getImage());
	leftAnimated =(new ImageIcon("sprites/bug left animated.gif").getImage());
	rightAnimated =(new ImageIcon("sprites/bug right animated.gif").getImage());
	}catch(Exception e){
	    Utilities.showErrorMessage(null,e);
	}
	maxHP = 750;
	HP = maxHP;
	maxATK = 100;
	ATK = maxATK;
	maxDEF = 25;
	DEF = maxDEF;
	EXP = 50;
	maxLuk = 0.1;
	luk = maxLuk;
	maxSpeed = 3;
	speed = maxSpeed;
	maxATKspeed = 50;
	ATKspeed = maxATKspeed;
    }
}
