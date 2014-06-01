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

    public void setHead(Item hd){ head = hd; }

    public Item getHead(){ return head; }

    public void setTorso(Item tso){ torso = tso; }

    public Item getTorso(){ return torso; }

    public void setLegs(Item lgs){ legs = lgs };

    public Item getLegs(){ return legs; }

    public void setFeet(Item ft){ feet = ft; }

    public Item getFeet(){ return feet; }

    public void setHands(Item hnd){ hands = hnd; }

    public Item getHands(){ return hands; }

    public void setWeapon(Item wpn){ weapon = wpn; }

    public Item getWeapon(){ return weapon; }

    public void setAccessory(Item acs){ accessory = acs; }

    public Item getAccessory(){ return accessory; }

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
