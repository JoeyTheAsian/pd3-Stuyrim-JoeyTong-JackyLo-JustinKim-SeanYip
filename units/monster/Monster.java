import java.util.*;
import java.io.*;

public abstract class Monster extends Unit{

    public Monster(int x, int y){
	mapX = x;
	mapY = y;
    }

    public ArrayList<Player> getSurroundingPlayers(){ //only for bosses
	ArrayList<Player>() surroundingPlayers = new ArrayList<Players>();
	for (Player player : /*in a global player list*/) //for all players in the map
	    if (player.getDist(this) <= range)
		surroundingPlayers.add(player);
	return surroundingPlayers;
    }

}
