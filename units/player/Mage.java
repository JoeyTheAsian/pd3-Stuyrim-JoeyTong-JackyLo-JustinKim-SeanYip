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
	image = new ImageIcon("Mage.png").getImage();
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
  
    public void sAttack3(){//kamehameha that deals burns to monsters in the AoE
	
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
