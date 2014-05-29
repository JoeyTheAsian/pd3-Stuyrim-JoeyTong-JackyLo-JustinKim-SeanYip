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
	speed = 40;
	ATKspeed = 33;
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
	HP = HP + LVL*10;
	ATK = ATK + LVL*25;
	DEF = DEF + LVL*5;
    }

}
