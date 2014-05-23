import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Bird extends Monster{

    public Bird(){
	name = "Bird";
	desc = "Wings Of Death";
	//lvl = Player.lvl; //someone make the Player class T_T
	HP = 750 + (lvl-1) * 25;
	ATK = (int)(Math.random() * 50) + 25 + (lvl-1) * 10;
	DEF = (int)(Math.random() * 50) + 25 + (lvl-1) * 10;
	//luk = ;
	//speed = ;					       
	x = 500;
	y = 150;
	sprite = new ImageIcon("BirdSpr.png").getImage();
	image = new ImageIcon("Bird.png").getImage();
	mana = 100;
    }

    public void setList(){

    }

    public void sAttack(){
	
    }

}
