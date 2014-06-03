import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

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
	accessory = null,
	consumables = null;
    protected int CDS2 = 100, CDS3 = 100;

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

    public void setConsumables(Item csm){ consumables = csm; }

    public Item getConsumables(){ return consumables; }

    public void setCDS2(int cds2){ CDS2 = cds2; }

    public int getCDS2(){ return CDS2; }

    public void setCDS3()(int cds3){ CDS3 = cds3; }

    public int getCDS3(){ return CDS3; }

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
