import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public abstract class Unit{
    protected String name;
    protected int maxHP, HP;
    protected int maxATK, ATK;
    protected int maxDEF, DEF;
    protected int EXP;
    protected int maxLuk, luk; //chance of hitting a critical strike
    protected int range = 20; //distance that allows the unit to attack
    protected int maxSpeed, speed = 10; //how fast the unit can move
    protected int maxATKspeed, ATKspeed; //hit per x CENTIseconds
    protected ArrayList<Item>() list = new ArrayList<>(); //item list
    protected Image left, right, up, down; //directions of the units for the images
    protected int maxMana, mana;
    protected double screenX, screenY;
    protected int mapX, mapY; //coordinates
    protected int CDS1 = 1000; //cooldown time for special attack in CENTIseconds
    protected boolean haveDebuff = false, haveBuff = false; //debuffs don't stack, they replace each other
    protected int debuffTime = 500, buffTime = 800; //lasting time for debuffs and buffs in CENTIseconds
    protected boolean isReady1 = true; //determines if sAttack is ready
    protected boolean isSet1 = false; //determines if sAttack is set
    protected int gold;

    public String getName(){ return name; }

    public void setMaxHP(int health){ maxHP = health; }	
    public int getMaxHP(){ return maxHP; }
	
    public void setHP(int health){ HP = health; }
    public int getHP(){ return HP; }

    public void setMaxATK(int attack){ maxATK = attack;}
    public int getMaxATK(){ return maxATK; }

    public void setATK(int attack){ ATK = attack; }
    public int getATK(){ return ATK; }
    
    public void setMaxDEF(int defense){ maxDEF = defense; }
    public int getMaxDEF(){ return maxDEF; }

    public void setDEF(int defense){ DEF = defense; }
    public int getDEF(){ return DEF; }

    public void setEXP(int xp){ EXP = xp; }
    public int getEXP(){ return EXP; }

    public void setMaxLuk(int luck){ maxLuk = luk; }
    public int getMaxLuk(){ return maxLuk; }
    
    public void setLuk(int luck){ luk = luck; }
    public int getLuk(){ return luk; }

    public void setRange(int rg){ range = rg; }
    public int getRange(){ return range; }

    public void setMaxSpeed(int sped){ speed = sped; }
    public int getMaxSpeed(){ return speed; }

    public void setSpeed(int sped){ speed = sped; }
    public int getSpeed(){ return speed; }

    public void setMaxATKspeed(int atkspd){ maxATKspeed = atkspd; }
    public int getMaxATKspeed(){ return maxATKspeed; }

    public void setATKspeed(int atkspd){ ATKspeed = atkspd; }
    public int getATKspeed(){ return ATKspeed; }

    public void setList(ArrayList<Item> lst)( list = lst; }

    public ArrayList<Item> getList(){ return list; }

    public void setLeft(String img){ left = new ImageIcon(img).getImage(); }
    public Image getLeft(){ return left; }

    public void setRight(String img){ right = new ImageIcon(img).getImage(); }
    public Image getRight(){ return right; }

    public void setUp(String img){ up = new ImageIcon(img).getImage(); }
    public Image getUp(){ return up; }

    public void setDown(String img){ down = new ImageIcon(img).getImage(); }
    public Image getDown(){ return down; }
    
    public void setMaxMana(int mn){ maxMana = mn; }
    public int getMaxMana(){ return maxMana; }
    
    public void setMana(int mn) { mana = mn; }
    public int getMana(){ return mana; }
    
    
    public void setScreenX(double xcor){ screenX = xcor; }
    public int getScreenX(){ return (int) screenX; }
    

    public void setMapX(int xcor){ mapX = xcor; }
    public int getMapX(){ return mapX; }

    
    public void setScreenY(double ycor){ screenY = ycor; }
    public int getScreenY(){ return (int) screenY; }
    

    public void setMapY(int ycor){ mapY = ycor; }
    public int getMapY(){ return mapY; }

    public void setCDS1(int cds1){ CDS1 = cds1; }
    public void getCDS1(){ return CDS1; }

    public void setHaveDebuff(boolean dbf){ haveDebuff = dbf; }
    public boolean getHaveDebuff(){ return haveDebuff; }

    public void setHaveBuff(boolean bf){ haveBuff = bf; }
    public boolean getHaveBuff(){ return haveBuff; }

    public int getDebuffTime(){ return debuffTime; }

    public int getBuffTime(){ return buffTime; }

    public void setIsReady1(boolean ir1){ isReady1 = ir1; }
    public boolean getIsReady1(){ return isReady1; }

    public void setIsSet1(boolean is1){ isSet1 = is1; }
    public boolean getIsSet1(){ return isSet1; }

    public void setGold(int gd){ gold = gd; }
    public int getGold(){ return gold; }

    public double getDist(Unit) {return Math.hypot(Math.abs(getMapX() - u.getMapX()), Math.abs(getMapY() - u.getMapY()));}

    public void charge(){
	if (mana < maxMana)
	    mana+=10;
	else
	    mana = maxMana;
    }

    public void finishDebuff(){ //once debuff is done or if another debuff is added on top of the debuff
	if (ATK != maxATK)
	    ATK = maxATK;
	if (DEF != maxDEF)
	    DEF = maxDEF;
	if (speed != maxSpeed)
	    speed = maxSpeed;
	if (ATKspeed != maxATKspeed)
	    ATKspeed = maxATKspeed;
    }

    public abstract void finishBuff(); //once buff is done

    public abstract void sAttack(); //stands for special attack
}
