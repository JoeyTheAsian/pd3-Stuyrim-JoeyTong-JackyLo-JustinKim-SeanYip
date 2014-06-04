import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Giant extends Monster{

    public Giant(int x, int y){
	super(x,y);
	name = "Giant";
	HP = 3000;
	mana = 500;
	ATK = 250;
	DEF = 100;
	EXP = 400;
	speed = 5;
	ATKspeed = 100;
	image = new ImageIcon("Giant.png").getImage();
    }

    public void setList(){

    }

    public void sAttack(){ //knocks you away like a baseball, and decreases your stats

    }

}
