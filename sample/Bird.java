import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;

public class Bird extends Player{
    protected Item shield = null;
    public Bird(){super();}
    public Bird(String imageLocation, int x, int y){
	super(imageLocation,x,y);
	try{
	 //get all the swordsman graphics
	 //if image isn't already that image, set it to the image
 	//to prevent unnecessary resetting of images (gifs would stutter)
	down = ImageIO.read(new File("sprites/bird down.png"));
	up = ImageIO.read(new File("sprites/bird up.png"));
	left = ImageIO.read(new File("sprites/bird left.png"));
	right = ImageIO.read(new File("sprites/bird right.png"));
	downAnimated = (new ImageIcon("sprites/bird down animated.gif").getImage());
	upAnimated = (new ImageIcon("sprites/bird up animated.gif").getImage());
	leftAnimated =(new ImageIcon("sprites/bird left animated.gif").getImage());
	rightAnimated =(new ImageIcon("sprites/bird right animated.gif").getImage());
	}catch(Exception e){
	    Utilities.showErrorMessage(null,e);
	}
	maxHP = 1000;
	HP = maxHP;
	maxMana = 500;
	mana = maxMana;
	maxATK = 150;
	ATK = maxATK;
	maxDEF = 50;
	DEF = maxDEF;
	EXP = 50;
	maxLuk = 0.1;
	luk = maxLuk;
	maxSpeed = 3;
	speed = maxSpeed;
	maxATKspeed = 33;
	ATKspeed = maxATKspeed;
    }
}
