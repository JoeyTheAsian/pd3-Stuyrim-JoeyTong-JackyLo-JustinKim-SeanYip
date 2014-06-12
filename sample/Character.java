import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Image;

public class Character implements Drawable{  
    protected Image image, down, up, left, right, downAnimated, upAnimated, leftAnimated, rightAnimated;
    private int x, y;
    private String imageLocation;
    protected int maxHP, HP;
    protected int maxATK, ATK;
    protected int maxDEF, DEF;
    protected double maxLuk, luk;
    protected int range = 100; //for testing purposes
    protected double maxSpeed, speed; //10 is super fast
    protected int maxATKspeed, ATKspeed; //hit per x CENTIseconds
    protected int maxMana, mana;
    //protected double screenX, screenY;
    //protected int mapX, mapY;
    protected int gold;
    protected Inventory inv = new Inventory();
    protected double changeX;
    protected double changeY;
    protected double distance;
    protected long timeStarted;

    public Character(){}
    public Character(String imageLocation, int x, int y) {
	setImage(imageLocation);
	setX(x);
	setY(y);
	maxHP = 1000;
	HP = maxHP;
	maxATK = 200;
	ATK = maxATK;
	maxDEF = 50;
	DEF = maxDEF;
	maxLuk = 0.05;
	luk = maxLuk;
	maxSpeed = 1.0;
	speed = maxSpeed;
	maxATKspeed = 100;
	ATKspeed = maxATKspeed;
	maxMana = 500;
	mana = 500;
	gold = 0;
    }
    
    public final Image getImage() {return image;}
    public final String getImageLocation() {return imageLocation;}
    public final int getHeight() {return image.getHeight(null);}
    public final int getWidth() {return image.getWidth(null);}
    public final int getX() {return x;}
    public final int getY() {return y;}
    public final void setImage(String imageLocation) {
	try {image = ImageIO.read(new File(imageLocation));}
	catch (Exception e) {Utilities.showErrorMessage(null, e);}
    }

    public final void setX(int x) {this.x = x;}
    public final void setY(int y) {this.y = y;}

    public final void setMaxHP(int maxHP){this.maxHP = maxHP;}
    public final void setHP(int HP){this.HP = HP;}
    public final void setMaxATK(int maxATK){this.maxATK = maxATK;}
    public final void setATK(int ATK){this.ATK = ATK;}
    public final void setMaxDEF(int maxDEF){this.maxDEF = maxDEF;}
    public final void setDEF(int DEF){this.DEF = DEF;}
    public final void setMaxLuk(double maxLuk){this.maxLuk = maxLuk;}
    public final void setLuk(double luk){this.luk = luk;}
    public final void setRange(int range){this.range = range;}
    public final void setMaxSpeed(double maxSpeed){this.maxSpeed = maxSpeed;}
    public final void setSpeed(double speed){this.speed = speed;}
    public final void setMaxATKspeed(int maxATKspeed){this.maxATKspeed = maxATKspeed;}
    public final void setATKspeed(int ATKspeed){this.ATKspeed = ATKspeed;}
    public final void setMaxMana(int maxMana){this.maxMana = maxMana;}
    public final void setMana(int mana){this.mana = mana;}
    public final void setGold(int gold){this.gold = gold;}

    public final int getMaxHP(){return maxHP;}
    public final int getHP(){return HP;}
    public final int getMaxATK(){return maxATK;}
    public final int getATK(){return ATK;}
    public final int getMaxDEF(){return maxDEF;}
    public final int getDEF(){return DEF;}
    public final double getMaxLuk(){return maxLuk;}
    public final double getLuk(){return luk;}
    public final int getRange(){return range;}
    public final double getMaxSpeed(){return maxSpeed;}
    public final double getSpeed(){return speed;}
    public final int getMaxATKspeed(){return maxATKspeed;}
    public final int getATKspeed(){return ATKspeed;}
    public final int getMaxMana(){return maxMana;}
    public final int getMana(){return mana;}
    public final int getGold(){return gold;}

    public final double getChangeX(){return changeX;}
    public final double getChangeY(){return changeY;}
    
    public final void setTimeStarted(long tm){timeStarted = tm;}
    public final long getTimeStarted(){return timeStarted;}

    public final double getDist(Character ch) {
	changeX = ch.getX()-getX();
	changeY = ch.getY()-getY();
	distance = Math.sqrt(changeX*changeX+changeY*changeY);
	return distance;
    }

    public final void setImage(Image i) {image = i;}

   //set current display image to sprite animations
    public final void setDown() {if(image != down) setImage(down);}
    public final void setUp(){if(image!= up) setImage (up);}
    public final void setLeft() {if(image != left) setImage (left);}
    public final void setRight() {if(image!= right) setImage( right);}
    public final void setDownAnimated(){if(image != downAnimated) setImage(downAnimated);}
    public final void setUpAnimated(){if(image != upAnimated) setImage(upAnimated);}
    public final void setLeftAnimated(){if(image != leftAnimated) setImage(leftAnimated);}
    public final void setRightAnimated(){if(image != rightAnimated) setImage(rightAnimated);}
    //for testing purposes
    public void attack(Character character){
	if (Math.random() < luk){
	    character.setHP(character.getHP() + character.getDEF() - (int)(1.5*ATK));
	}else{
	    character.setHP(character.getHP() + character.getDEF() - ATK);
	}
    }
}
