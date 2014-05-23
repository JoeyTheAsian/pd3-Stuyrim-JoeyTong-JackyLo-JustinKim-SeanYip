import java.util.*;
import java.io.*;

public class Goblin extends Monster{

    public Goblin(){
	name = "Goblin";
	desc = "Mutated Humanoids of Death";
	lvl = Player.lvl;
	HP = 1000 + (lvl-1) * 50;
	ATK = (int)(Math.random() * 30) + 135 + (lvl-1) * 20;
	DEF = (int)(Math.random() * 50) + 75 + (lvl-1) * 15;
	//luk = ;
	//speed = ;
	sprite = new ImageIcon("GoblinSpr.png").getImage();
	image = new ImageIcon("Goblin.png").getImage();
	mana = 100;
    }

    public void setList(){

    }

    public void sAttack(){

    }

}
