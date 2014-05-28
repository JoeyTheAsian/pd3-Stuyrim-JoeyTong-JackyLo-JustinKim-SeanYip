import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public abstract class Unit{
    protected String name;
    protected int maxHP, HP;
    protected int ATK;
    protected int DEF;
    protected int EXP;
    protected int luk;
    protected int range = 20;
    protected int speed;
    protected int ATKspeed; //hit per x CENTIseconds
    protected ArrayList<Item>() list = new ArrayList<Item>();
    protected Image image;
    protected int maxMana, mana;
    protected double screenX, screenY;
    protected int mapX, mapY;

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

    public void setImage(String img){ image = new ImageIcon(img).getImage(); }

    public Image getImage(){ return image; };
    
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

    public void charge(){ mana+=10; }

    public void attack(Unit u){
	u.setHP(u.getHP()+u.getDEF()-getATK());
    }

    public abstract void sAttack(); //stands for special attack
}
