import java.util.*;
import java.io.*;

public class Slime extends Monster{

    public Slime(){
	name = "Slime";
	desc = "Giant Amoeba Of Death";
	lvl = 1; //depend on player's level
	HP = 1000;
	ATK = (int)(Math.random() * 50) + 75;
	DEF = (int)(Math.random() * 50) + 25;
	//luck = ;
	//speed = ;
	sprite = new ImageIcon("").getImage();
	image = new ImageIcon("").getImage();
	mana = 100;
    }

    public void setList(){
	
    }
	
    public void sAttack(){
	
    }

}