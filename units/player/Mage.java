import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Mage extends Player{
    protected int maxManaCost, manaCost;
    protected int maxCDS1, maxCDS3; //sAttack1 and sAttack3 will have CDR (cooldown reduction)

    public Mage(String myName, int x, int y){
	super(x,y);
	name = myName;
	maxHP = 1000;
	HP = maxHP;
	maxMana = 1000;
	mana = maxMana;
	maxManaCost = 250;
	manaCost = maxManaCost;
	maxATK = 150;
	ATK = maxATK;
	maxDEF = 20;
	DEF = maxDEF;
	EXP = 0;
	maxLuk = 3;
	luk = maxLuk;
	maxSpeed = 4;
	speed = maxSpeed;
	maxATKspeed = 100;
	ATKspeed = maxATKspeed;
	range = 120;
	maxCDS1 = CDS1;
	maxCDS3 = CDS3;
	setLeft("Mage_left.png");
	setRight("Mage_right.png");
	setUp("Mage_up.png");
	setDown("Mage_down.png");
    }
  
    public void setManaCost(int mncst){ manaCost = mncst; }
    public void getManaCost(){ return manaCost; }

    public void sAttack(){//health drain, takes percent of health from monsters in player's range and heals the player the sum of their health
	int tempHP = 0;
	for (Monster monster : getSurroundingMonsters(range)){
	    int tempMonsterHP = monster.getMaxHP()*(0.1 + (double)LVL*0.005);
	    tempHP = tempHP + tempMonsterHP;
	    monster.setHP(monster.getHP() - tempMonsterHP);
	}
	setHP(getHP() + tempHP);
	if (HP > maxHP)
	    HP = maxHP;
	mana -= manaCost;
	//start cooldownTime
    }

    public void sAttack2(){//hyper magic spam, increases ATK, decreases cooldown and mana cost (so u can spam kamehamehas all ova da prasez)
	ATK = ATK + maxATK*0.15;
	manaCost = 100;
	CDS1 = 200;
	CDS3 = 200;
	mana -= 250;
	haveBuff = true;
	//start buffTime
	//start cooldownTime
    }
  
    public void sAttack3(){//kamehameha that slows to monsters in the AoE
	//mouse position and coordinates
	//get distance from mouse position to player and divide x coord and y coord by 20 - 30
	//make variables for that
	Projectile p = new Projectile(mapX,mapY);
	while (/*p is inside the screen*/){
	    for (Monster monster : p.getSurroundingEnemies(range)){
		attack(monster);
		monster.setSpeed(monster.getSpeed()-monster.getMaxSpeed()*0.1);
	    }
	    //p.setMapX(p.getMapX() +- x variable)
	    //p.setMapY(p.getMapY() +- y variable)
	}	
    }
    
    private class Projectile extends Unit{ //for kamehameha
	
	public Projectile(int x, int y){
	    mapX = x;
	    mapY = y;
	}

	public ArrayList<Monster> getSurroundingEnemies(int rng){
	    ArrayList<Monster>() surroundingMonsters = new ArrayList<Monster>();
	    for (Monster monster : /*in a global monster list*/)
		if (monster.getDist(this) <= rng)
		    surroundingMonsters.add(monster);
	    return surroundingMonsters();
	}
	
	public void finishBuff(){}
	public void sAttack(){}
    }

    public void finishBuff(){
	ATK = maxATK;
	CDS1 = maxCDS1;
	CDS3 = maxCDS3;
    }

    public void LVLup(){
	super.LVLup();
	LVLupStats();
    }

    public void LVLupStats(){
	setMaxHP(getMaxHP()+10);
	setMaxATK(getMaxATK()+50);
	setMaxDEF(getMaxDEF()+5);
	if (getLVL()%2 == 0){
	    setMaxLuk(getMaxLuk()+1);
	    setMaxSpeed(getMaxSpeed()+1);
	}
	if (getLVL()%3 == 0){
	    setMaxATKSpeed(getMaxATKSpeed()-1);
	}
    }
}
