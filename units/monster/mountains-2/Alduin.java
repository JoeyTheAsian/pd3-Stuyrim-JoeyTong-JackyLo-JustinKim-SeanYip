import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Alduin extends Monster{

    public Alduin(int x, int y){
	super(x,y);
	name = "Alduin the Great Dragon";
	HP = 10000;
	mana = 500;
	ATK = 500;
	DEF = 250;
	EXP = 10000;
	speed = 50;
	ATKspeed = 50;
	image = new ImageIcon("Alduin.png").getImage();
    }

    public void setList(){
	
    }

    public void sAttack(){ //heavy roar, stuns surrounding enemies and significantly decreases their stats

    }

    public void sAttack2(){ //framethrower breath, similar to kamehameha
	
    }

    public void sAttack3(){ //chomp, life drains the enemy, and increases speed and ATKspeed

    }

}
