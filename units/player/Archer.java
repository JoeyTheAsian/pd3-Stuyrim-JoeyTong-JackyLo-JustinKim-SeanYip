import java.util.*;
import java.io.*;

public class Archer extends Player{

    public Archer(String myName){
	name = myName;
	HP = 1000;
	mana = 500;
	ATK = 100;
	DEF = 20;
	EXP = 0;
	luk = 5;
	speed = 7;
	ATKspeed = 50;
	range = 150;
	image = new ImageIcon("Archer.png").getImage();
    }
  
    public void sAttack(){
      
    }
  
    public void sAttack2(){
      
    }
  
    public void sAttack3(){
      
    }
  
    public void LVLup(){
	super.LVLup();
	LVLupStats();
    }

    public void LVLupStats(){
	setHP(getHP()+10);
	setATK(getATK()+25);
	setDEF(getDEF()+5);
	setLuk(getLuk()+2);
	setSpeed(getSpeed()+2);
	setATKSpeed(getATKSpeed()-2);
    }

}
