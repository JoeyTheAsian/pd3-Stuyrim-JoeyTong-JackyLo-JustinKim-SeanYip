import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Archer extends Player{
    protected haveBuff2 = false; //for sAttack3
    protected int buffTime2 = 800; //for sAttack3

    public Archer(String myName, int x, int y){
	super(x,y);
	name = myName;
	maxHP = 1000;
	HP = maxHP;
	maxMana = 500;
	mana = maxMana;
	maxATK = 100;
	ATK = maxATK;
	maxDEF = 20;
	DEF = maxDEF;
	EXP = 0;
	maxLuk = 5;
	luk = maxLuk;
	maxSpeed = 7;
	speed = maxSpeed;
	maxATKspeed = 50;
	ATKspeed = maxATKspeed;
	range = 150;
	image = new ImageIcon("Archer.png").getImage();
    }
  
    public void sAttack(){ //blunt, giant arrows that knocks surrounding monsters away by 200?
	for (Monster monster : getSurroundingMonsters()){
	    if (monster.getMapX() < getMapX())
		monster.setMapX(getMapX()-141); //100*sqrt(2)
	    else if (monster.getMapX() > getMapX())
		monster.setMapX(getMapX()+141);
	    if (monster.getMapY() < getMapY())
		monster.setMapY(getMapY()-141);
	    else if (monster.getMapY() > getMapY())
		monster.setMapY(getMapX()+141);
	    monster.setSpeed(speed-2);
	    monster.haveDebuff = true;
	    //start debuffTime
	}
	mana -= 250;
	//start cooldownTime
    }
  
    public void sAttack2(){ //mercury shoes and a minigun bow, increases speed and ATKspeed
	speed = speed + speed*(0.25+(double)LVL*0.01);
	ATKspeed = ATKspeed + ATKspeed*(0.3+(double)LVL*0.01);
	mana -= 250;
	haveBuff = true;
	//start buffTime
	//start cooldownTime
    }
  
    public void sAttack3(){ //deadly arrows, increases ATK and luk
	ATK = ATK + ATK*(0.15+(double)LVL*0.01);
	luk = luk + luk*(0.15+(double)LVL*0.01);
	mana -= 250;
	haveBuff2 = true;
	//start buffTime2
	//start cooldownTime
    }

    public void LVLup(){
	super.LVLup();
	LVLupStats();
    }

    public void LVLupStats(){
	setHP(getHP()+10);
	setATK(getATK()+25);
	setDEF(getDEF()+5);
	if (getLuk() < 50)
	    setLuk(getLuk()+2);
	if (getSpeed() < 50)
	    setSpeed(getSpeed()+2);
	if (getATKSpeed() > 10)
	    setATKSpeed(getATKSpeed()-2);
    }

}
