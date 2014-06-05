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
    protected ArrayList<Item>() list = new ArrayList<Item>(); //item list
    protected Image image;
    protected int maxMana, mana;
    //protected double screenX, screenY;
    protected int mapX, mapY; //coordinates
    protected int CDS1 = 1000; //cooldown time for special attack in CENTIseconds
    protected boolean haveDebuff = false, haveBuff = false; //debuffs don't stack, yet
    protected int debuffTime = 500, buffTime = 800; //lasting time for debuffs and buffs in CENTIseconds
    protected boolean isReady1 = true; //determines if sAttack is ready
    protected boolean isSet1 = false; //determines if sAttack is set

    public String getName(){ return name; }

    public void setMaxHP(int health){ maxHP = health; }
	
    public int getMaxHP(){ return maxHP; }
	
    public void setHP(int health){ HP = health; }

    public int getHP(){ return HP; }
    
    public void setATK(int attack){ ATK = attack; }

    public int getATK(){ return ATK; }
    
    public void setDEF(int defense){ DEF = defense; }
    
    public int getDEF(){ return DEF; }

    public void setEXP(int xp){ EXP = xp; }

    public int getEXP(){ return EXP; }
    
    public void setLuk(int luck){ luk = luck; }

    public int getLuk(){ return luk; }

    public void setRange(int rg){ range = rg; }
    
    public int getRange(){ return range; }

    public void setSpeed(int sped){ speed = sped; }

    public int getSpeed(){ return speed; }

    public void setATKspeed(int atkspd){ ATKspeed = atkspd; }

    public int getATKspeed(){ return ATKspeed; }

    public void setList(ArrayList<Item> lst)( list = lst; }

    public ArrayList<Item> getList(){ return list; }

    public void setImage(String img){ image = new ImageIcon(img).getImage(); }

    public Image getImage(){ return image; };
    
    public void setMaxMana(int mn){ maxMana = mn; }
    
    public int getMaxMana(){ return maxMana; }
    
    public void setMana(int mn) { mana = mn; }

    public int getMana(){ return mana; }
    
    /*
      public void setScreenX(double xcor){ screenX = xcor; }

      public int getScreenX(){ return (int) screenX; }
    */

    public void setMapX(int xcor){ mapX = xcor; }

    public int getMapX(){ return mapX; }

    /*
      public void setScreenY(double ycor){ screenY = ycor; }
    
      public int getScreenY(){ return (int) screenY; }
    */

    public void setMapY(int ycor){ mapY = ycor; }

    public int getMapY(){ return mapY; }

    public void setCDS1(int cds1){ CDS1 = cds1; }

    public void getCDS1(){ return CDS1; }

    public int getDist(Unit u){ //gets the distance from Unit u
	return Math.sqrt( Math.pow((getMapX()-u.getMapX()),2) +
			  Math.pow((getMapY()-u.getMapY()),2));
	//distance formula
    }

    public void charge(){
	if (mana < maxMana)
	    mana+=10; 
    }

    public void attack(Unit u){ //may need fix?
	if (isSet1){ //precondition: isReady1 = true && 250 <= mana
	    isSet1 = false;
	    sAttack(); //checks if sAttack is ready. If not, just attack
	}else{
	    if ((int)(Math.random()*100) <= getLuk()) //chance of crit
		u.setHP(u.getHP()+u.getDEF()-1.5*getATK());
	    else
		u.setHP(u.getHP()+u.getDEF()-getATK());
	}
    }

    public abstract void sAttack(); //stands for special attack

    public abstract void finishDebuff(); //once debuff is done

    public abstract void finishBuff(); //once buff is done
}
