import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Swordsman extends Player{
    protected Item shield = null;

    public Swordsman(int x, int y){
	super(x,y);
	setLeft("swordsman left.png");
	setRight("swordsman right.png");
	setUp("swordsman up.png");
	setDown("swordsman down.png");
    }

    public void setShield(Item shld){shield = shld;}
    public Item getShield(){return shield;}

    public void sAttack2(){}

    public void sAttack3(){}

    public void finishBuff(){}

    public void LVLupStats(){}
}
