import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Silverfish extends Monster{

    public Silverfish(int x, int y){
	super(x,y);
	name = "Silverfish";
	HP = 500;
	mana = 500;
	ATK = 50;
	DEF = 5;
	EXP = 50;
	speed = 25;
	ATKspeed = 35;
	image = new ImageIcon("Silverfish.png").getImage();
    }

    public void setList(){

    }

    public void sAttack(){//calls 2 more silverfish to help their friend

    }

}
