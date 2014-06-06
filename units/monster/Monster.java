import java.util.*;
import java.io.*;

public abstract class Monster extends Unit{

    public Monster(int x, int y){
	mapX = x;
	mapY = y;
    }

    public ArrayList<Player> getSurroundingPlayers(int rng){ //only for bosses
	ArrayList<Player>() surroundingPlayers = new ArrayList<Players>();
	for (Player player : /*in a global player list*/) //for all players in the map
	    if (player.getDist(this) <= rng)
		surroundingPlayers.add(player);
	return surroundingPlayers;
    }

    public void attack(Player u){ //may need fix?
	if (isSet1){ //precondition: isReady1 = true && 250 <= mana
	    isSet1 = false;
	    sAttack(); //checks if sAttack is ready. If not, just attack
	}else{
	    if ((int)(Math.random()*100) <= getLuk()) //chance of crit
		u.setHP(u.getHP()+u.getDEF()-1.5*getATK());
	    else
		u.setHP(u.getHP()+u.getDEF()-getATK());
	}
    }

    public abstract ArrayList<Player> getPlayersInRange(int range);

}
