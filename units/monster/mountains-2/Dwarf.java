import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Dwarf extends Monster{

    public Dwarf(int x, int y){
	super(x,y);
	name = "Dwarf";
	HP = 1500;
	mana = 500;
	ATK = 200;
	DEF = 100;
	EXP = 150;
	speed = 5;
	ATKspeed = 50;
	image = new ImageIcon("Dwarf.png").getImage();
    }

    public void setList(){

    }

    public void sAttack(){//smacks you with his pickaxe idk

    }

}
