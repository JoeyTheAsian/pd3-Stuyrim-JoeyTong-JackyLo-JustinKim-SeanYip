import java.util.*;
import java.io.*;

public class Plant extends Monster{

    public Plant(){
	name = "Plant";
	desc = "Mother Nature's Minion Of Death";
	lvl = Player.lvl;
	HP = 500 + (lvl-1) * 50;
	ATK = (int)(Math.random() * 50) + 50 + (lvl-1) * 15;
	DEF = (int)(Math.random() * 20) + 15 + (lvl-1) * 15;
	//luk = ;
	//speed = ;
	sprite = new ImageIcon("Plant.png").getImage();
	image = new ImageIcon("Plant.png").getImage();
	mana = 150;
    }
    
    public void setList(){
	
    }

    public void sAttack(){

    }

}	
