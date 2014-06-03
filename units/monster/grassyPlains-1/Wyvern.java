import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Wyvern extends Monster{
    
    public Wyvern(int x, int y){
	super(x,y);
	name = "Wyvern";
	HP = 2000;
	mana = 500;
	ATK = 150;
	DEF = 100;
	EXP = 200;
	luk = 5;
	speed = 15;
	ATKspeed = 50;
	image = new ImageIcon("Wyvern.png").getImage();
    }

    public void setList(){

    }

    public void sAttack(){ //scratches and bites that bleeds and slows you, then flies 200? away
  
    }

}
