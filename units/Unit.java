import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public abstract class Unit{
    protected String name;
    protected int HP;
    protected int ATK;
    protected int DEF;
    protected int EXP;
    protected int luk;
    protected int speed;
    protected int ATKspeed; //hit per x CENTIseconds
    protected ArrayList<Item>() list = new ArrayList<Item>();
    protected Image image;
    protected int mana;
    protected double screenX, mapX;
    protected double screenY, mapY;

    public String getName(){ return name; }

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

    public void setSpeed(int sped){ speed = sped; }

    public int getSpeed(){ return speed; }

    public void setATKspeed(int atkspd){ ATKspeed = atkspd; }

    public int getATKspeed(){ return ATKspeed; }

    public void setImage(String img){ image = new ImageIcon(img).getImage(); }

    public Image getImage(){ return image; };
    
    public void setMana(int mn) { mana = mn; }

    public int getMana(){ return mana; }

    public void setScreenX(double xcor){ screenX = xcor; }

    public int getScreenX(){ return (int) screenX; }
    
    public void setMapX(double xcor){ mapX = xcor; }

    public int getMapX(){ return mapX; }

    public void setScreenY(double ycor){ screenY = ycor; }
    
    public int getScreenY(){ return (int) screenY; }

    public void setMapY(double ycor){ mapY = ycor; }

    public int getMapY(){ return mapY; }

    public void charge(){ mana+=10; }

    public void attack(){ }

    public abstract void sAttack(); //stands for special attack

}
