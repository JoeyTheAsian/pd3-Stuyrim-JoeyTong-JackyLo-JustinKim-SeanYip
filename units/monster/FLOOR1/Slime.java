import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Slime extends Monster{

    public Slime(){
	name = "Slime";
	desc = "Giant Amoeba Of Death";
	//lvl = Player.lvl; //depend on player's level
	HP = 1000 + (lvl-1) * 50;
	ATK = (int)(Math.random() * 50) + 75 + (lvl-1) * 15;
	DEF = (int)(Math.random() * 50) + 25 + (lvl-1) * 15;
	//luk = ;
	//speed = ;
	x = 550;
	y = 150;
	sprite = new ImageIcon("SlimeSpr.png").getImage();
	image = new ImageIcon("Slime.png").getImage();
	mana = 100;
    }

    public void setList(){
	
    }
	
    public void sAttack(){
	
    }

}
