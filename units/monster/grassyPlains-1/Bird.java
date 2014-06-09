import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Bird extends Monster{

    public Bird(int x, int y){
	super(x,y);
	name = "Bird";
	HP = 500;
	mana = 500;
	ATK = 20;
	DEF = 5;
	EXP = 20;
	luk = 5;
	speed = 10;
	ATKspeed = 50;
	setLeft("bird left.png");
	setRight("bird right.png");
	setUp("bird up.png");
	setDown("bird down.png");
    }

    public void setList(){

    }

    public void sAttack(){ //peck, deals true damage (ignores DEF)
	
    }

}
