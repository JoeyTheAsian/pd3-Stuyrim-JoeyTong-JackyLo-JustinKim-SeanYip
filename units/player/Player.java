import java.util.*;
import java.io.*;

public abstract class Player extends Unit/* implements Serializable*/{
 
    //player should have three unique special attacks 
    //NOTE: sAttack1() = sAttack() from Unit class
  
    protected int LVL = 1;
    protected int LVLreq = (int)(Math.pow(2,1)) * 50;
    protected Item head = null,
	torso = null,
	legs = null,
	feet = null,
	hands = null,
	weapon = null,
	accessory = null;

    public void setLVL(int level){ lvl = level; }

    public int getLVL(){ return LVL; }

    public abstract void sAttack2();
  
    public abstract void sAttack3();
    
    public void LVLup(){
	while (EXP >= LVLreq){
	    EXP = EXP-LVLreq;
	    LVL++;
	    LVLReq = (int)(Math.pow(2,LVL)) * 50;
	}
    }

    public abstract void LVLupStats();

}
