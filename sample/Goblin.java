import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;

public class Goblin extends Player{
    protected Item shield = null;
    public Goblin(){super();}
    public Goblin(String imageLocation, int x, int y){
	super(imageLocation,x,y);
	try{
	 //get all the swordsman graphics
	 //if image isn't already that image, set it to the image
 	//to prevent unnecessary resetting of images (gifs would stutter)
	down = ImageIO.read(new File("sprites/goblin down.png"));
	up = ImageIO.read(new File("sprites/goblin up.png"));
	left = ImageIO.read(new File("sprites/goblin left.png"));
	right = ImageIO.read(new File("sprites/goblin right.png"));
	downAnimated = (new ImageIcon("sprites/goblin down animated.gif").getImage());
	upAnimated = (new ImageIcon("sprites/goblin up animated.gif").getImage());
	leftAnimated =(new ImageIcon("sprites/goblin left animated.gif").getImage());
	rightAnimated =(new ImageIcon("sprites/goblin right animated.gif").getImage());
	}catch(Exception e){
	    Utilities.showErrorMessage(null,e);
	}
	maxHP = 1000;
	HP = maxHP;
	maxMana = 500;
	mana = maxMana;
	maxATK = 200;
	ATK = maxATK;
	maxDEF = 100;
	DEF = maxDEF;
	EXP = 100;
	maxLuk = 0.15;
	luk = maxLuk;
	maxSpeed = 2;
	speed = maxSpeed;
	maxATKspeed = 50;
	ATKspeed = maxATKspeed;
    }
}
