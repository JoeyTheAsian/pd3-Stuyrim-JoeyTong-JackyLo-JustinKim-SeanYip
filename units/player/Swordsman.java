import java.util.*;
import java.io.*;

public class Swordsman extends Player{

    public Swordsman(String myName){
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
    
    public void sAttack(){//slash, deals damage to monsters and decreases ATKspeed and DEF of monsters inside range of 100?
    
    }
  
    public void sAttack2(){//relentless pursuit, increases dEF, ATK, and luk
    
    }
  
    public void sAttack3(){//

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
