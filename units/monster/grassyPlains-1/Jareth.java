import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Jareth extends Monster{ //Random name I googled. He's some actor from a 1980's film. I have no affection to him and that film. I just wanted a name. T_T -Jacky

    public Jareth(int x, int y){
	super(x,y);
	name = "Jareth the Goblin King";
	HP = 5000;
	mana = 500;
	ATK = 200;
	DEF = 150;
	EXP = 5000;
	luk = 10;
	speed = 7;
	ATKspeed = 120;
	image = new ImageIcon("Jareth.png").getImage();
    }

    public void setList(){

    }

    public void sAttack(){//calls three goblins
	
    }

    public void sAttack2(){//warcry, increases stats and decreases increases of enemies around him
	
    }

    public void sAttack3(){//giant mallet smash, deals true damage to the enemies in an area and stuns them

    }

}
