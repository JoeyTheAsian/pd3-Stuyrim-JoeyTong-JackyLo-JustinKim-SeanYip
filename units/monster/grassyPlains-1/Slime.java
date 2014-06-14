import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Slime extends Monster{

    public Slime(int x, int y){
	super(x,y);
	setLeft("slime left.png");
	setRight("slime right.png");
	setUp("slime up.png");
	setDown("slime down.png");
    }

    public void setList(){}

    //slows your speed and ATKspeed
    public void sAttack(){}

}
