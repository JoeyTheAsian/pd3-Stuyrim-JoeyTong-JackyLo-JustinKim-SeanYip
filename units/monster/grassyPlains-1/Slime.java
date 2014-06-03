import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Slime extends Monster{

    public Slime(int x, int y){
	super(x,y);
	name = "Slime";
	HP = 500;
	mana = 500;
	ATK = 50;
	DEF = 0;
	EXP = 10;
	luk = 1;
	speed = 2;
	ATKspeed = 100;
	image = new ImageIcon("Slime.png").getImage();
    }

    public void setList(){
	
    }
	
    public void sAttack(){//slows your speed and ATKspeed
	
    }

}
