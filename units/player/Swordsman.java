import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Swordsman extends Player{
    protected Item shield = null;

    public Swordsman(String myName, int x, int y){
	super(x,y);
	name = myName;
	HP = 1500;
	mana = 500;
	ATK = 100;
	DEF = 50;
	EXP = 0;
	luk = 3;
	speed = 5;
	ATKspeed = 50;
	image = new ImageIcon("Swordsman.png").getImage();
    }
    
    public void setShield(Item shd){ shield = shd; }
    public Item getShield(){ return shield; }

    public void sAttack(){//circle slash, deals damage to monsters and decreases ATKspeed and DEF of monsters inside range of 100?
	for (Monster monster : getSurroundingMonsters(100)){
	    attack(monster);
	    monster.setDEF(monster.getDEF()-getDEF()*0.2);
	    monster.setATKspeed(monster.getATKspeed()-getATKspeed()*0.2);
	}
    }
  
    public void sAttack2(){//relentless pursuit, increases DEF, ATK, and luk, and heals you
	HP = HP + maxHP*0.25;
	if (HP > maxHP)
	    HP = maxHP;
	DEF = DEF + maxDEF*0.1;
	ATK = ATK + maxATK*0.1;
	luk = luk + maxLuk*0.1;
	haveBuff = true;
    }
  
    public void sAttack3(){//fatal slash, bleeds the surrounding enemies and decreases its speed

    }

    public void finishBuff(){
	ATK = maxATK;
	DEF = maxDEF;
	luk = maxLuk;
    }

    public void LVLup(){
	super.LVLup();
	LVLupStats();
    }

    public void LVLupStats(){
	setMaxHP(getMaxHP()+25);
	setMaxATK(getMaxATK()+10);
	setMaxDEF(getMaxDEF()+10);
	setMaxLuk(getMaxLuk()+1);
	if (getLVL()%2 == 0){
	    setMaxSpeed(getMaxSpeed()+1);
	    setMaxATKSpeed(getMaxATKSpeed()-1);
	}
    }
}
