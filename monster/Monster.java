import java.util.*;
import java.io.*;

public abstract class Monster{

    protected String name;
    protected String descript;
    protected int lvl;
    protected int HP;
    protected int ATK;
    protected int DEF;
    protected int luck;
    protected int speed;
    protected ArrayList<Item>() list = new ArrayList<Item>(); 

    public Monster(){ //base constructor
	name = "Monster";
	descript = "an evil character";
	lvl = 1;
	HP = 100;
	ATK = 1;
	DEF = 1;
	luck = 1;
	speed = 1;
    }

    public abstract void setList();

    public String getName(){ return name; }

    public String getDescript(){ return descript; }

    public int getLVL(){ return lvl; }

    public int getHP(){ return HP; }

    public int getATK(){ return ATK; }
    
    public int getDEF(){ return DEF; }

    public int getLuck(){ return luck; }

    public int getSpeed(){ return speed; }

    public void attack(){
	//attacks player
    }

    public abstract void sAttack(); //stands for special attack

}
