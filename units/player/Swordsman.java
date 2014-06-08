import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Swordsman extends Player{
    protected Item shield = null;

    public Swordsman(, int x, int y){
	super(x,y);
	setLeft("swordsman left.png");
	setRight("swordsman right.png");
	setUp("swordsman up.png");
	setDown("swordsman down.png");
    }
}
