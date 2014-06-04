import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Archer extends Player{

    public Archer(String myName, int x, int y){
	super(x,y);
	name = myName;
	HP = 1000;
	mana = 500;
	ATK = 100;
	DEF = 20;
	EXP = 0;
	luk = 5;
	speed = 7;
	ATKspeed = 50;
	range = 150;
	image = new ImageIcon("Archer.png").getImage();
    }
  
    public void sAttack(){ //blunt, giant arrows that knocks surrounding monsters away by 200?

    }
  
    public void sAttack2(){ //mercury shoes and a minigun bow, increases speed and ATKspeed

    }
  
    public void sAttack3(){ //deadly arrows, increases ATK and luk and slows monsters
      
    }
  
    public void LVLup(){
	super.LVLup();
	LVLupStats();
    }

    public void LVLupStats(){
	setHP(getHP()+10);
	setATK(getATK()+25);
	setDEF(getDEF()+5);
	setLuk(getLuk()+2);
	setSpeed(getSpeed()+2);
	setATKSpeed(getATKSpeed()-2);
    }

}
