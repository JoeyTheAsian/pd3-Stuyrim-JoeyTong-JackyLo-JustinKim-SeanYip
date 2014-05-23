import java.util.*;
import java.io.*;

public abstract class Unit{
    protected String name;
    protected String desc;
    protected int lvl;
    protected int HP;
    protected int ATK;
    protected int DEF;
    protected int luk;
    protected int speed;
    protected ArrayList<Item>() list = new ArrayList<Item>();
    protected Image sprite, image;
    protected int mana;
    protected int x;
    protected int y;

    public String getName(){ return name; }

    public String getDesc(){ return desc; }

    public int getLVL(){ return lvl; }

    public void setHP(int result){ hp = result; }

    public int getHP(){ return HP; }

    public int getATK(){ return ATK; }
    
    public int getDEF(){ return DEF; }

    public int getLuk(){ return luk; }

    public int getSpeed(){ return speed; }

    public Image getSprite(){ return sprite; };

    public Image getImage(){ return image; };

    public int getMana(){ return mana; }

    public int getX(){ return x; }
    
    public int getY(){ return y; }

    public void charge(){ mana+=10; }

    public void attack(){ }

    public abstract void sAttack(); //stands for special attack


}