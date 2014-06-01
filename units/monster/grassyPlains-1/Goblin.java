import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Goblin extends Monster{

    public Goblin(){
	name = "Goblin";
	HP = 1500;
	mana = 500;
	ATK = 100;
	DEF = 75;
	EXP = 50;
	luk = 5;
	speed = 5;
	ATKspeed = 75;
	image = new ImageIcon("Goblin.png").getImage();
    }

    public void setList(){

    }

    public void sAttack(){

    }

}
