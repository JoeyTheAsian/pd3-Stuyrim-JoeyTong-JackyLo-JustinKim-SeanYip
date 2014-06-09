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
	setLeft("archer left.png");
	setRight("archer right.png");
	setUp("archer up.png");
	setDown("archer down.png");
    }

    public void setHaveBuff2(boolean bf2){ haveBuff2 = bf2; }
    public boolean getHaveBuff2(){ return haveBuff2; }

    public int getBuffTime2(){ return buffTime2; }
  
    public void sAttack(){ //blunt, giant arrows that knocks surrounding monsters away by 200?
	for (Monster monster : getSurroundingMonsters(range)){
	    if (monster.getMapX() < getMapX())
		monster.setMapX(getMapX()-141); //100*sqrt(2)
	    else if (monster.getMapX() > getMapX())
		monster.setMapX(getMapX()+141);
	    if (monster.getMapY() < getMapY())
		monster.setMapY(getMapY()-141);
	    else if (monster.getMapY() > getMapY())
		monster.setMapY(getMapX()+141);
	    monster.setSpeed(getSpeed()-2);
	    monster.haveDebuff = true;
	    //start debuffTime
	}
	mana -= 250;
	//start cooldownTime
    }
  
    public void sAttack2(){ //mercury shoes and a minigun bow, increases speed and ATKspeed
	speed = speed + maxSpeed*0.25;
	ATKspeed = ATKspeed + maxATKspeed*0.3;
	mana -= 250;
	haveBuff = true;
	//start buffTime
	//start cooldownTime
    }
  
    public void sAttack3(){ //deadly arrows, increases ATK and luk
	ATK = ATK + maxATK*0.15;
	luk = luk + maxLuk*0.15;
	mana -= 250;
	haveBuff2 = true;
	//start buffTime2
	//start cooldownTime
    }

    public void finishBuff(){
	speed = maxSpeed;
	ATKspeed = maxATKspeed;
    }

    public void finishBuff2(){
	ATK = maxATK;
	luk = maxLuk;
    }

    public void LVLup(){
	super.LVLup();
	LVLupStats();
    }

    public void LVLupStats(){
	setMaxHP(getMaxHP()+10);
	setMaxATK(getMaxATK()+25);
	setMaxDEF(getMaxDEF()+5);
	if (getMaxLuk() < 50)
	    setMaxLuk(getMaxLuk()+2);
	if (getMaxSpeed() < 50)
	    setMaxSpeed(getMaxSpeed()+2);
	if (getMaxATKSpeed() > 10)
	    setMaxATKSpeed(getMaxATKSpeed()-2);
    }
}
