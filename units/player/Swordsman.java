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
	speed = 20;
	ATKspeed = 50;
	image = new ImageIcon("Swordsman.png").getImage();
    }
    
    public void sAttack(){
    
    }
  
    public void sAttack2(){
    
    }
  
    public void sAttack3(){

    }

    public void LVLup(){
	super.LVLup();
	LVLupStates();
    }

    public void LVLupStats(){
	HP = HP + LVL*25;
	ATK = ATK + LVL*10;
	DEF + DEF + LVL*10;
    }

}
