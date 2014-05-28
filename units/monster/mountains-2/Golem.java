import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Golem extends Monster{

    public Golem(){
	name = "Golem";
	HP = 2500;
	mana = 500;
	ATK = 100;
	DEF = 200;
	EXP = 300;
	speed = 5;
	ATKspeed = 150;
	image = new ImageIcon("Golem.png").getImage();
    }

    public void setList(){

    }

    public void sAttack(){

    }
}
