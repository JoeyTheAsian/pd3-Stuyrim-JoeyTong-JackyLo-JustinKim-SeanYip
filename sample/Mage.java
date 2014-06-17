import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;

public class Mage extends Player{
    protected Item shield = null;
    public Mage(){super();}
    public Mage(String imageLocation, int x, int y){
	super(imageLocation,x,y);
	try{
	    //get all the swordsman graphics
	    //if image isn't already that image, set it to the image
	    //to prevent unnecessary resetting of images (gifs would stutter)
	    down = ImageIO.read(new File("sprites/mage down.png"));
	    up = ImageIO.read(new File("sprites/mage up.png"));
	    left = ImageIO.read(new File("sprites/mage left.png"));
	    right = ImageIO.read(new File("sprites/mage right.png"));
	    downAnimated = (new ImageIcon("sprites/mage down animated.gif").getImage());
	    upAnimated = (new ImageIcon("sprites/mage up animated.gif").getImage());
	    leftAnimated =(new ImageIcon("sprites/mage left animated.gif").getImage());
	    rightAnimated =(new ImageIcon("sprites/mage right animated.gif").getImage());
	    downShield = ImageIO.read(new File("sprites/swordsman shield down.png"));
	    upShield = ImageIO.read(new File("sprites/mage shield up.png"));
	    leftShield = ImageIO.read(new File("sprites/swordsman left.png"));
	    rightShield = ImageIO.read(new File("sprites/swordsman right.png"));
	    downShieldAnimated = (new ImageIcon("sprites/swordsman shield down animated.gif").getImage());
	    upShieldAnimated = (new ImageIcon("sprites/mage shield up animated.gif").getImage());
	    //need to implement left and right shields
	    leftShieldAnimated =(new ImageIcon("sprites/swordsman left animated.gif").getImage());
	    rightShieldAnimated =(new ImageIcon("sprites/swordsman right animated.gif").getImage());
	}catch(Exception e){
	    Utilities.showErrorMessage(null,e);
	}
	maxHP = 250;
	HP = maxHP;
	maxDEF = 10;
	DEF = maxDEF;
	maxLuk = 0.1;
	luk = maxLuk;
	EXP = 0;
	maxSpeed = 2;
	speed = maxSpeed;
	maxATKspeed = 300;
	ATKspeed = maxATKspeed;
	maxMana = 1000;
	mana = maxMana;
	range = 800;
    }

    public void sAttack(Character c){
	attack(c);
	if (c.getMana() < 0) c.setMana(0);
	HP+=LVL*5;
	if (HP > maxHP) HP = maxHP;
	mana+=LVL*10;
	if (mana > maxMana) mana = maxMana;
    }
}
