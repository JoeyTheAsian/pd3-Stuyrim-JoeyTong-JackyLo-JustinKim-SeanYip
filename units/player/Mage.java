import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Mage extends Player{
    protected int maxManaCost, manaCost;

    public Mage(String myName, int x, int y){
	super(x,y);
	name = myName;
	maxHP = 1000;
	HP = maxHP;
	maxMana = 1000;
	mana = maxMana;
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
	image = new ImageIcon("Mage.png").getImage();
    }
  
    public void sAttack(){//life and mana drain, takes percent of health and mana from monsters in player's range and heals the player the sum of their health and gives the player the sum of their mana
	if (isReady1 && manaCost <= mana){
	    int tempHP = 0;
	    int tempMana = 0;
	    for (Monster monster : surroundingMonsters){
		int tempMonsterHP = monster.getMaxHP()*(0.1 + (double)LVL*0.005);
		int tempMonsterMana = monster.getMaxMana()*(0.1 + (double)LVL*0.005);
		tempHP = tempHP + tempMonsterHP;
		monster.setHP(monster.getHP() - tempMonsterHP);
		tempMana = tempMana + tempMonsterMana;
		monster.setHP(monster.getMana() - tempMonsterMana);
	    }
	    setHP(getHP() + tempHP);
	    if (HP > maxHP)
		HP = maxHP;
	    setMana(getMana() + tempMana);
	    if (mana > maxMana)
		mana = maxMana;
	}
    }

    public void sAttack2(){//manly buffness, increases ATK and mana, decreases cooldown and mana cost (so u can spam kamehamehas all ova da prasez)
  
    }
  
    public void sAttack3(){//kamehameha, deals burn for affected monsters
    
    }
    
    public void LVLup(){
	super.LVLup();
	LVLupStats();
    }

    public void LVLupStats(){
	setHP(getHP()+10);
	setATK(getATK()+50);
	setDEF(getDEF()+5);
	if (getLVL()%2 == 0){
	    setLuk(getLuk+1);
	    setSpeed(getSpeed()+1);
	}
	if (getLVL()%3 == 0){
	    setATKSpeed(getATKSpeed()-1);
	}
    }

}
