import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;

public class Swordsman extends Player{
    protected Item shield = null;


    public Swordsman(){super();}
    public Swordsman(String imageLocation, int x, int y){
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
	    downShield = ImageIO.read(new File("sprites/swordsman shield down.png"));
	    upShield = ImageIO.read(new File("sprites/swordsman shield up.png"));
	    leftShield = ImageIO.read(new File("sprites/swordsman left.png"));
	    rightShield = ImageIO.read(new File("sprites/swordsman right.png"));
	    downShieldAnimated = (new ImageIcon("sprites/swordsman shield down animated.gif").getImage());
	    upShieldAnimated = (new ImageIcon("sprites/swordsman shield up animated.gif").getImage());
	    //need to implement left and right shields
	    leftShieldAnimated =(new ImageIcon("sprites/swordsman left animated.gif").getImage());
	    rightShieldAnimated =(new ImageIcon("sprites/swordsman right animated.gif").getImage());
	}catch(Exception e){
	    Utilities.showErrorMessage(null,e);
	}
	maxDEF = 25;
	DEF = maxDEF;
	maxLuk = 0.1;
	luk = maxLuk;
	maxSpeed = 3;
	speed = maxSpeed;
	maxATKspeed = 50;
	ATKspeed = maxATKspeed;
    }

    public void sAttack(Character c){
	int i = (int)c.getChangeX();
	int j = (int)c.getChangeY();
	if (i == 0) i = 1;
	if (j == 0) j = 1;
	attack(c);
	c.setX(c.getX()-1000/i);
	c.setY(c.getY()-1000/j);
	c.setSpeed(1);
	c.setDEF(c.getDEF()-LVL);
	if (c.getDEF() < 10) c.setDEF(10);
	c.setATK(c.getATK()-2*LVL);
	if (c.getATK() < 10) c.setATK(10);
	ATK = ATK + 5*LVL;
	DEF = DEF + LVL;
    }
}
