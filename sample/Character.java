import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Character implements Drawable{  
    protected Image image, down, up, left, right, downAnimated, upAnimated, leftAnimated, rightAnimated
	,downShield = null, upShield = null,leftShield = null ,rightShield = null,
	downShieldAnimated = null ,upShieldAnimated = null,leftShieldAnimated = null,rightShieldAnimated = null;
    protected HashMap<String, Double> drops = new HashMap<>(); //The drop table (RuneScape PvM style). Maps the item name to the item's chance of dropping. Why is the key of type String? That avoids mutable keys and having instances of items in every Character. To get the actual item, have another HashMap<String, Item> that maps the name of the item to the actual item, probably in GamePanel. For maximum performance, use constructor HashMap(int initalCapacity, float loadFactor) to overwrite this instance of drops here, where initalCapacity equals to the number of entries in the collection and loadFactor equals 1, if the capacity of the HashMap is known, which it is because the HashMap will be initalized in the constructors of Character's subclasses: Bird, Swordsman, etc.
    private int x, y;
    private String imageLocation;
    protected int maxHP, HP;
    protected int maxATK, ATK;
    protected int maxDEF, DEF;
    protected double maxLuk, luk;
    protected int range = 100; //for testing purposes
    protected int maxSpeed, speed; //10 is super fast
    protected int maxATKspeed, ATKspeed; //hit per x CENTIseconds
    protected int maxMana, mana;
    //protected double screenX, screenY;
    //protected int mapX, mapY;
    protected int EXP;
    protected int LVL = 1;
    protected int LVLreq = 50;
    protected Inventory inventory = new Inventory();
    protected double changeX;
    protected double changeY;
    protected double distance;
    protected long timeStarted;
    protected Character target = null;

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
	EXP = 0;
	maxSpeed = 1;
	speed = maxSpeed;
	maxATKspeed = 100;
	ATKspeed = maxATKspeed;
	maxMana = 500;
	mana = 500;
	gold = 0;
	try {inventory.add(new Item(ImageIO.read(new File("items/Cake.png")), "Cake", "It's a lie.", 9001, 9001, 9001));}
	catch (Exception e) {Utilities.showErrorMessage(null, e);}
	drops.put("Cake", 1.0);
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
    public final void setEXP(int xp){EXP = xp;}
    public final void setLVL(int lvl){LVL = lvl;}
    public final void setRange(int range){this.range = range;}
    public final void setMaxSpeed(int maxSpeed){this.maxSpeed = maxSpeed;}
    public final void setSpeed(int speed){this.speed = speed;}
    public final void setMaxATKspeed(int maxATKspeed){this.maxATKspeed = maxATKspeed;}
    public final void setATKspeed(int ATKspeed){this.ATKspeed = ATKspeed;}
    public final void setMaxMana(int maxMana){this.maxMana = maxMana;}
    public final void setMana(int mana){this.mana = mana;}
    public final void setGold(int gold){this.gold = gold;}
    public final void setTarget(Character tgt){target = tgt;}

    public final int getMaxHP(){return maxHP;}
    public final int getHP(){return HP;}
    public final int getMaxATK(){return maxATK;}
    public final int getATK(){return ATK;}
    public final int getMaxDEF(){return maxDEF;}
    public final int getDEF(){return DEF;}
    public final HashMap<String, Double> getDrops() {return drops;}
    public final double getMaxLuk(){return maxLuk;}
    public final double getLuk(){return luk;}
    public final int getEXP(){return EXP;}
    public final int getLVL(){return LVL;}
    public final int getLVLreq(){return LVLreq;}
    public final int getRange(){return range;}
    public final int getMaxSpeed(){return maxSpeed;}
    public final int getSpeed(){return speed;}
    public final int getMaxATKspeed(){return maxATKspeed;}
    public final int getATKspeed(){return ATKspeed;}
    public final int getMaxMana(){return maxMana;}
    public final int getMana(){return mana;}
    public final int getGold(){return gold;}
    public final Character getTarget(){return target;}

    public final Inventory getInventory(){return inventory;}

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
    public final void setUpShield(){image = upShield;}
    public final void setDownShield(){image = downShield;}
    public final void setLeftShield(){image =leftShield;}
    public final void setRightShield(){image = rightShield;}
    public final void setUpShieldAnimated(){image = upShieldAnimated;}
    public final void setDownShieldAnimated(){image = downShieldAnimated;}
    public final void setLeftShieldAnimated(){image = leftShieldAnimated;}
    public final void setRightShieldAnimated(){image = rightShieldAnimated;}
    //for testing purposes
    public void attack(Character character){
	if (character.getDEF() > ATK){
	    character.setHP(character.getHP() - (int)(0.5*ATK));
	    return;
	}
	if (Math.random() < luk){
	    character.setHP(character.getHP() + character.getDEF() - (int)(1.5*ATK));
	}else{
	    character.setHP(character.getHP() + character.getDEF() - ATK);
	}
    }

    public void slowStatRestore(){
	if (HP < maxHP) HP++;
	if (HP > maxHP) HP--;
	if (mana < maxMana) mana+=5;
	if (mana > maxMana) mana-=5;
	if (ATK < maxATK) ATK++;
	if (ATK > maxATK) ATK--;
	if (DEF < maxDEF) DEF++;
	if (DEF > maxDEF) DEF--;
	if (luk < maxLuk) luk++;
	if (luk > maxLuk) luk--;
	if (ATKspeed > maxATKspeed) ATKspeed--;
	if (ATKspeed < maxATKspeed) ATKspeed++;
	if (speed < maxSpeed) speed++;
	if (speed > maxSpeed) speed--;
    }

    public void LVLupStat(){
	maxHP+=25;
	maxMana+=50;
	maxATK+=5;
	maxDEF+=2;
	maxLuk+=0.01;
	maxATKspeed-=0.01;
    }
	
    public void LVLup(){
	while (EXP >= LVLreq){
	    EXP = EXP-LVLreq;
	    LVLupStat();
	    LVL++;
	    LVLreq = LVL*LVL+50;
	}
    }
}
