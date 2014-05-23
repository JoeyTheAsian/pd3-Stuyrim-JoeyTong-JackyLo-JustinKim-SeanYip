import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener{

    static ArrayList<Unit> unit = new ArrayList<Unit>();
    static ArrayList<Unit> enemy = new ArrayList<Unit>();
    static ArrayList<Unit> test = new ArrayList<Unit>();
    static Base red = new Base(0);
    static Base blue = new Base(0);
    static Wall redw = new Wall(0);
    static Wall bluew = new Wall(0);
    static int coord,coord2;
    static double gold = 0, gold2 = 0;
    static int time = 0, time2 = 0;
    static int hour = 0;
    static int minute = 0;
    static int second = 0;
    protected static int upcost1,upcost2,upcost3,upcost4;
    protected static int cost1,cost2,cost3,cost4;
	
	
    Container buy,up = new Container();
    Image background, wall, wall2;
    Timer t;

    public Game(){
	background = new ImageIcon("BackGround.gif").getImage();
	
	upcost1 = 100;
	upcost2 = 200;
	upcost3 = 300;
	upcost4 = 750;
	coord = 853;
	coord2 = 173;

	Timer t = new Timer(34,this);
	t.start();
    }

    public void paint(Graphics g){
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(background,0,0,this);
	if (red.hp < 0){
	    unit.clear();
	    enemy.clear();
	    g2d.setFont(new Font("TimesRoman",Font.PLAIN,100));
	    g2d.drawString("BLUE TEAM WINS",150,250);
	}else if (blue.hp < 0){
	    unit.clear();
	    enemy.clear();
	    g2d.setFont(new Font("TimesRoman",Font.PLAIN,100));
	    g2d.drawString("RED TEAM WINS",150,250);
	}else{
	    g2d.drawImage(redw.getPic(),118,251,this);
	    g2d.drawImage(bluew.getPic2(),908,251,this);
	    g2d.drawString("Gold: " + (int)gold + " (" + (0.15 + ((double)enemy.size() * 0.005)) * 20 + " per sec)", 10, 25);
	    g2d.drawString("Gold: " + (int)gold2 + " (" + (0.15 + ((double)unit.size() * 0.005)) * 20 + " per sec)", 945, 25);
	    g2d.drawString("Time left for Giant: " + String.valueOf(time / 20) + " sec",10,45);
	    g2d.drawString("Time left for Giant: " + String.valueOf(time2 / 20) + " sec",945,45);
	    g2d.drawString("Unit size: " + unit.size(),10,65);
	    g2d.drawString("Unit size: " + enemy.size(),945,65);
	    g2d.drawString("Health: " + String.valueOf(red.hp), 10, 250);
	    g2d.drawString("Health: " + String.valueOf(blue.hp), 988, 250);
	    g2d.drawString("Time: " + String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second / 20),500,25);

	    if (redw.getHP() > 0) g2d.drawString(String.valueOf(redw.hp),118,250);
	    if (bluew.getHP() > 0) g2d.drawString(String.valueOf(bluew.hp),908,250);

	    for (int n = 0; n < enemy.size(); n++){
		Unit e = enemy.get(n);
		g2d.drawImage(e.getPic2(),(int)e.x2,e.getH(),this);
		g2d.drawString(String.valueOf(e.hp),e.getX2(),200);
	    }
	    for (int i = 0; i < unit.size(); i++){
		Unit u = unit.get(i);
		g2d.drawImage(u.getPic(),u.getX(),u.getH(),this);
		g2d.drawString(String.valueOf(u.hp),u.getX(),200);
	    }
	}
	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }

    public void actionPerformed(ActionEvent q){	
	for (int i = 0; i < unit.size(); i++){
	    Unit u = unit.get(i);
	    if (u.getX() > coord && u.getHP() > 0){
		if (u instanceof Sword){
		    u.setPic("Gif/RedSwordAttack.gif");
		    if (u.getLVL() >= 1) u.setPic("Gif/RedSwordAttack" + u.getLVL() + ".gif");
		}else if (u instanceof Bow){
		    u.setPic("Gif/RedBowAttack.gif");
		    if (u.getLVL() >= 1) u.setPic("Gif/RedBowAttack" + u.getLVL() + ".gif");
		}else if (u instanceof Shield){
		    u.setPic("Gif/RedShieldAttack.gif");
		    if (u.getLVL() >= 1) u.setPic("Gif/RedShieldAttack" + u.getLVL() + ".gif");
		}else if (u instanceof Giant){
		    u.setPic("Gif/RedGiantAttack.gif");
		}
		if (bluew.getHP() <= 0){
		    bluew.setPic2("BlueWallDie.gif");
		    coord = 935;
		    if (u.getATKTime() <= 0){
			u.attack(blue,u);
			u.setATKTime(20);
		    }else u.atkTimeCD();
		}else{
		    if (u.getATKTime() <= 0){
			u.attack(bluew,u);
			u.setATKTime(20);
		    }else u.atkTimeCD();
		}
		u.loseHP();
		u.setWalk(false);
	    }else if (u.getHP() <= 0){
		if (u instanceof Sword){
		    u.setPic("Gif/RedSwordDie.gif");
		    if (u.getLVL() >= 1) u.setPic("Gif/RedSwordDie" + u.getLVL() + ".gif");
		    
		}else if (u instanceof Bow){
		    u.setPic("Gif/RedBowDie.gif");
		    if (u.getLVL() >= 1) u.setPic("Gif/RedBowDie" + u.getLVL() + ".gif");
		    
		}else if (u instanceof Shield){
		    u.setPic("Gif/RedShieldDie.gif");
		    if (u.getLVL() >= 1) u.setPic("Gif/RedShieldDie" + u.getLVL() + ".gif");
		 
		}else if (u instanceof Giant){
		    u.setPic("Gif/RedGiantDie.gif");
		}
		unit.remove(i);
		if (u.getX() > coord)
		    gold2 += (double)(u.getReward() * 3 / 4);
		else
		    gold2 += (double)u.getReward();
		if (bluew.hp < bluew.maxHP && bluew.hp > 0)
		    bluew.hp += 100;
		if (bluew.hp > bluew.maxHP)
		    bluew.hp = bluew.maxHP;
		u.setWalk(false);
	    }else if (enemy.size() >= 1){
		for (int n = 0; n < enemy.size(); n++){
		    if (Math.abs(enemy.get(n).getX2() - u.getX()) <= u.getDist() && u.getHP() > 0){
			if (u instanceof Sword){
			    u.setPic("Gif/RedSwordAttack.gif");
			    if (u.getLVL() >= 1) u.setPic("Gif/RedSwordAttack" + u.getLVL() + ".gif");
			}else if (u instanceof Bow){
			    u.setPic("Gif/RedBowAttack.gif");
			    if (u.getLVL() >= 1) u.setPic("Gif/RedBowAttack" + u.getLVL() + ".gif");
			}else if (u instanceof Shield){
			    u.setPic("Gif/RedShieldAttack.gif");
			    if (u.getLVL() >= 1) u.setPic("Gif/RedShieldAttack" + u.getLVL() + ".gif");
			}else if (u instanceof Giant){
			    u.setPic("Gif/RedGiantAttack.gif");
			}
			if (u.getATKTime() <= 0){
			    u.attack(enemy.get(n),u);
			    u.setATKTime(20);
			}else u.atkTimeCD();
			u.setWalk(false);
			break;
		    }else{
			u.setWalk(true);
		    }
		}
	    }else{
		u.setWalk(true);
	    }
	    if (u.getWalk()){
		if (u instanceof Sword){
		    u.setPic("Gif/RedSwordWalk.gif");
		    if (u.getLVL() >= 1) u.setPic("Gif/RedSwordWalk" + u.getLVL() + ".gif");
		}else if (u instanceof Bow){
		    u.setPic("Gif/RedBowWalk.gif");
		    if (u.getLVL() >= 1) u.setPic("Gif/RedBowWalk" + u.getLVL() + ".gif");
		}else if (u instanceof Shield){
		    u.setPic("Gif/RedShieldWalk.gif");
		    if (u.getLVL() >= 1) u.setPic("Gif/RedShieldWalk" + u.getLVL() + ".gif");
		}else if (u instanceof Giant){
		    u.setPic("Gif/RedGiantWalk.gif");
		}
		unit.get(i).move();
	    }
	}
	for (int i = 0; i < enemy.size(); i++){
	    Unit e = enemy.get(i);
	    if (e.getX2() < coord2 && e.getHP() > 0){
		if (e instanceof Sword){
		    e.setPic2("Gif/BlueSwordAttack.gif");
		    if (e.getLVL() >= 1) e.setPic2("Gif/BlueSwordAttack" + e.getLVL() + ".gif");
		}else if (e instanceof Bow){
		    e.setPic2("Gif/BlueBowAttack.gif");
		    if (e.getLVL() >= 1) e.setPic2("Gif/BlueBowAttack" + e.getLVL() + ".gif");
		}else if (e instanceof Shield){
		    e.setPic2("Gif/BlueShieldAttack.gif");
		    if (e.getLVL() >= 1) e.setPic2("Gif/BlueShieldAttack" + e.getLVL() + ".gif");
		}else if (e instanceof Giant){
		    e.setPic2("Gif/BlueGiantAttack.gif");
		}
		if (redw.getHP() <= 0){
		    redw.setPic("RedWallDie.gif");
		    coord2 = 91;
		    if (e.getATKTime() <= 0){
			e.attack(red,e);
			e.setATKTime(20);
		    }else e.atkTimeCD();
		}else{
		    if (e.getATKTime() <= 0){
			e.attack(redw,e);
			e.setATKTime(20);
		    }else e.atkTimeCD();
		}
		e.loseHP();
		e.setWalk(false);
	    }else if (e.getHP() <= 0){
		if (e instanceof Sword){
		    e.setPic2("Gif/BlueSwordDie.gif");
		    if (e.getLVL() >= 1) e.setPic2("Gif/BlueSwordDie" + e.getLVL() + ".gif");
		    
		}else if (e instanceof Bow){
		    e.setPic2("Gif/BlueBowDie.gif");
		    if (e.getLVL() >= 1) e.setPic2("Gif/BlueBowDie" + e.getLVL() + ".gif");
		  
		}else if (e instanceof Shield){
		    e.setPic2("Gif/BlueShieldDie.gif");
		    if (e.getLVL() >= 1) e.setPic2("Gif/BlueShieldDie" + e.getLVL() + ".gif");
		   
		}else if (e instanceof Giant){
		    e.setPic2("Gif/BlueGiantDie.gif");
		}
		enemy.remove(i);
		if (e.getX() < coord2)
		    gold += (double)(e.getReward() * 3 / 4);
		else
		    gold += (double)e.getReward();
		if (redw.hp < redw.maxHP && redw.hp > 0)
		    redw.hp += 100;
		if (redw.hp > redw.maxHP)
		    redw.hp = redw.maxHP;
		e.setWalk(false);
	    }else if (unit.size() >= 1){
		for (int n = 0; n < unit.size(); n++){
		    if (Math.abs(e.getX2() - unit.get(n).getX()) <= e.getDist() && e.getHP() > 0){
			if (e instanceof Sword){
			    e.setPic2("Gif/BlueSwordAttack.gif");
			    if (e.getLVL() >= 1) e.setPic2("Gif/BlueSwordAttack" + e.getLVL() + ".gif");
			}else if (e instanceof Bow){
			    e.setPic2("Gif/BlueBowAttack.gif");
			    if (e.getLVL() >= 1) e.setPic2("Gif/BlueBowAttack" + e.getLVL() + ".gif");
			}else if (e instanceof Shield){
			    e.setPic2("Gif/BlueShieldAttack.gif");
			    if (e.getLVL() >= 1) e.setPic2("Gif/BlueShieldAttack" + e.getLVL() + ".gif");
			}else if (e instanceof Giant){
			    e.setPic2("Gif/BlueGiantAttack.gif");
			}
			if (e.getATKTime() <= 0){
			    e.attack(unit.get(n),e);
			    e.setATKTime(20);
			}else e.atkTimeCD();
			e.setWalk(false);
			break;
		    }else{
			e.setWalk(true);
		    }
		}
	    }else{
		e.setWalk(true);
	    }
	    if (e.getWalk()){
		if (e instanceof Sword){
		    e.setPic2("Gif/BlueSwordWalk.gif");
		    if (e.getLVL() >= 1) e.setPic2("Gif/BlueSwordWalk" + e.getLVL() + ".gif");
		}else if (e instanceof Bow){
		    e.setPic2("Gif/BlueBowWalk.gif");
		    if (e.getLVL() >= 1) e.setPic2("Gif/BlueBowWalk" + e.getLVL() + ".gif");
		}else if (e instanceof Shield){
		    e.setPic2("Gif/BlueShieldWalk.gif");
		    if (e.getLVL() >= 1) e.setPic2("Gif/BlueShieldWalk" + e.getLVL() + ".gif");
		}else if (e instanceof Giant) {
		    e.setPic2("Gif/BlueGiantWalk.gif");
		}
		enemy.get(i).move2();
	    }
	}
	gold += 0.15 + ((double)enemy.size() * 0.005);
	gold2 += 0.15 + ((double)unit.size() * 0.005);
	if (time > 0)
	    time--;
	if (time2 > 0)
	    time2--;
	second++;
	if (second == 1200){
	    second = 0;
	    minute++;
	}
	if (minute == 60){
	    minute = 0;
	    hour++;
	}
	repaint();
    }
}
