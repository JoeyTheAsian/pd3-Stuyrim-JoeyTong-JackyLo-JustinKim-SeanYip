import java.util.*;
import java.io.*;

public class Dragon extends Monster{

    public Dragon(){
	name = "Dragon";
	descript = "a flying monster";
	lvl = 1; //should depend on the lvl;
	HP = 100 * lvl;
	ATK = ((int)(Math.random() * 50) + 100) * lvl; //random, but should depend on the lvl
	DEF = ((int)(Math.random() * 50) + 50) * 1vl; //def < atk, and should depend on the lvl
	luck = 100 * lvl;
	speed = 9001; //they can fly >.>
    }

    public void setList(){
	//random items that depend on lvl
    }

    public void sAttack(){
	//fireballs that actually burn you (maybe)
    }

}
