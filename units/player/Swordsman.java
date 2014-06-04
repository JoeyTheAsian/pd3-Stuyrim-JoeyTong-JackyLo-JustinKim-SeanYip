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
    
    }
  
    public void sAttack2(){//relentless pursuit, increases DEF, ATK, and luk
    
    }
  
    public void sAttack3(){//fatal slash, bleeds the surrounding enemies and decreases its speed

    }

    public void LVLup(){
	super.LVLup();
	LVLupStats();
    }

    public void LVLupStats(){
	setHP(getHP()+25);
	setATK(getATK()+10);
	setDEF(getDEF()+10);
	setLuk(getLuk()+1);
	if (getLVL()%2 == 0){
	    setSpeed(getSpeed()+1);
	    setATKSpeed(getATKSpeed()-1);
	}
    }

}
