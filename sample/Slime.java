import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;

public class Slime extends Player{
    protected Item shield = null;
    public Slime(){super();}
    public Slime(String imageLocation, int x, int y){
	super(imageLocation,x,y);
	try{
	 //get all the swordsman graphics
	 //if image isn't already that image, set it to the image
 	//to prevent unnecessary resetting of images (gifs would stutter)
	down = ImageIO.read(new File("sprites/slime down.png"));
	up = ImageIO.read(new File("sprites/slime up.png"));
	left = ImageIO.read(new File("sprites/slime left.png"));
	right = ImageIO.read(new File("sprites/slime right.png"));
	downAnimated = (new ImageIcon("sprites/slime down animated.gif").getImage());
	upAnimated = (new ImageIcon("sprites/slime up animated.gif").getImage());
	leftAnimated =(new ImageIcon("sprites/slime left animated.gif").getImage());
	rightAnimated =(new ImageIcon("sprites/slime right animated.gif").getImage());
	}catch(Exception e){
	    Utilities.showErrorMessage(null,e);
	}
	maxHP = 500;
	HP = maxHP;
	maxMana = 500;
	mana = maxMana;
	maxATK = 50;
	ATK = maxATK;
	maxDEF = 5;
	DEF = maxDEF;
	EXP = 10;
	maxLuk = 0.01;
	luk = maxLuk;
	maxSpeed = 2;
	speed = maxSpeed;
	maxATKspeed = 50;
	ATKspeed = maxATKspeed;
    }

}
