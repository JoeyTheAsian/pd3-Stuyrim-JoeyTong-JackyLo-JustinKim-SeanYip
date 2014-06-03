import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Mage extends Player{

    public Mage(String myName){
	name = myName;
	HP = 1000;
	mana = 1000;
	ATK = 150;
	DEF = 20;
	EXP = 0;
	luk = 3;
	speed = 4;
	ATKspeed = 100;
	range = 120;
	image = new ImageIcon("Mage.png").getImage();
    }
  
    public void sAttack(){//life and mana drain, takes percent of health and mana from monsters in a range of 100? and heals the player the sum of their health and gives the player the sum of their mana
    
    }

    public void sAttack2(){//buffness, increases ATK and mana, decreases cooldown and mana cost (so u can spam kamehamehas all ova da prasez)
  
    }
  
    public void sAttack3(){//kamehameha, deals burn in the AoE
    
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
