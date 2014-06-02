import java.util.*;
import java.io.*;

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
  
    public void sAttack(){//heal
    
    }

    public void sAttack2(){//slows monsters in an area
  
    }
  
    public void sAttack3(){//kamehameha
    
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
