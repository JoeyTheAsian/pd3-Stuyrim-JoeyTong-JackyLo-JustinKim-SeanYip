import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Gargoyle extends Monster{

    public Gargoyle(){
	name = "Gargoyle";
	HP = 2000;
	mana = 500;
	ATK = 200;
	DEF = 100;
	EXP = 250;
	speed = 35;
	ATKspeed = 50;
	image = new ImageIcon("Gargoyle.png").getImage();
    }

    public void setList(){
	
    }

    public void sAttack(){ //blast of air from its wings that deals damage and slows you

    }

}
