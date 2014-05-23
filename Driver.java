import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.util.Random;
import sun.audio.*;
import java.io.*;
  
public class Driver extends JFrame implements ActionListener{ 

    Container but = new Container(), lab = new Container();
    JButton sw,b,sh,g,usw,ub,ush,uba;
    JLabel busw,bub,bush,bug,uusw,uub,uush,uuba,
	besw,beb,besh,beg,uesw,ueb,uesh,ueba;
    static int usw2=0,ub2=0,ush2=0,uba2=0;
    static int esw2=0,eb2=0,esh2=0,eba=0;
    Unit a;
    Timer timer;
    int ctd = 0, ctd2 = 0;
    int cd = 0,cd2 = 0;
    Random rand = new Random();

    public Driver(){
	this.setLayout(new GridLayout(1,1));
	this.setTitle("War");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(1106,590);
	this.setResizable(false);
	this.setLocationRelativeTo(null);
	this.addKeyListener(new KeyEvents());
	this.add(new Game());
	//addButtons();
	this.setVisible(true);
	timer = new Timer(1000,this);
	timer.start();
	int random = rand.nextInt(4);
	if (random == 0) music1();
	if (random == 1) music2();
	if (random == 2) music3();
	if (random == 3) music4();
    }

    public void addButtons(){
	but.setLayout(new GridLayout(2,4));
	a = new Sword(0);
	sw = new JButton("Buy Swordsman for \n" + a.getCost());
	a = new Bow(0);
	b = new JButton("Buy Archer for \n" + a.getCost());
	a = new Shield(0);
	sh = new JButton("Buy Tank for \n" + a.getCost());
	a = new Giant();
	g = new JButton("Buy Giant for \n" + a.getCost());

	but.add(sw);
	but.add(b);
	but.add(sh);
	but.add(g);

	sw.addActionListener(new AbstractAction("bsw"){
		public void actionPerformed(ActionEvent e){
		    a = new Sword(usw2);
		    if (Game.gold >= a.getCost()){
			Game.unit.add(a);
			Game.gold -= a.getCost();
		    }
		}
	    });
	b.addActionListener(new AbstractAction("bb"){
		public void actionPerformed(ActionEvent e){
		    a = new Bow(ub2);
		    if (Game.gold >= a.getCost()){
			Game.unit.add(a);
			Game.gold -= a.getCost();
		    }
		}
	    });
	sh.addActionListener(new AbstractAction("bsh"){
		public void actionPerformed(ActionEvent e){
		    a = new Shield(ush2);
		    if (Game.gold >= a.getCost()){
			Game.unit.add(a);
			Game.gold -= a.getCost();
		    }
		}
	    });
	g.addActionListener(new AbstractAction("bg"){
		public void actionPerformed(ActionEvent e){
		    a = new Giant();
		    if (Game.gold >= a.getCost() && Game.time <= 0){
			Game.unit.add(a);
			Game.gold -= a.getCost();
			Game.time = 1200;
		    }
		}
	    });

	a = new Sword(0);
	usw = new JButton("Upgrade Swordsman for \n" + a.getUpCost());
	a = new Bow(0);
	ub = new JButton("Upgrade Archer for \n" + a.getUpCost());
	a = new Shield(0);
	ush = new JButton("Upgrade Tank for \n" + a.getUpCost());
	a = new Base(0);
	uba = new JButton("Upgrade Base and Wall for \n" + a.getUpCost());
	
	usw.addActionListener(new AbstractAction("usw"){
		public void actionPerformed(ActionEvent e){
		    a = new Sword(usw2);
		    if (Game.gold >= a.getUpCost()){
			if (usw2 < 4){
			    usw2++;
			    Game.gold -= a.getUpCost();
			    Game.upcost1 *= 2;
			    Game.cost1 *=2;
			    a = new Sword(usw2);
			    sw.setText("Buy Swordsman for \n " + a.getCost());
			    usw.setText("Upgrade Swordsman for \n " + a.getUpCost());
			    if (usw2 == 4){
				usw.setText("Swordsman is at max level!");
				usw.setEnabled(false);
			    }
			}
		    } 
		}
	    });
	ub.addActionListener(new AbstractAction("ub"){
		public void actionPerformed(ActionEvent e){
		    a = new Bow(ub2);
		    if (Game.gold >= a.getUpCost()){
			if (ub2 < 4) {
			    ub2++;
			    Game.gold -= a.getUpCost();
			    Game.upcost2 *= 2;
			    Game.cost2 *= 2;
			    a = new Bow(ub2);
			    b.setText("Buy Archer for \n " + a.getCost());
			    ub.setText("Upgrade Archer for \n " + a.getUpCost());
			    if (ub2 == 4){
				ub.setText("Archer is at max level!");
				ub.setEnabled(false);
			    }
			}
		    }
		}
	    });
	ush.addActionListener(new AbstractAction("ush"){
		public void actionPerformed(ActionEvent e){
		    a = new Shield(ush2);
		    if (Game.gold >= a.getUpCost()){
			if (ush2 < 4){
			    ush2++;
			    Game.gold -= a.getUpCost();
			    Game.upcost3 *= 2;
			    Game.cost3 *= 2;
			    a = new Shield(ush2);
			    sh.setText("Buy Tank for \n " + a.getCost());
			    ush.setText("Upgrade Tank for \n " + a.getUpCost());
			    if (ush2 == 4){
				ush.setText("Tank is at max level!");
				ush.setEnabled(false);
			    }
			}
		    }
		}
	    });
	uba.addActionListener(new AbstractAction("uba"){
		public void actionPerformed(ActionEvent e){
		    a = new Base(uba2);
		    if (Game.gold >= a.getUpCost()){
			if (uba2 < 2){
			    uba2++;
			    Game.gold -= a.getUpCost();
			    Game.upcost4 *= 2;
			    Game.cost4 *= 2;
			    Game.red = new Base(uba2);
			    if (Game.redw.getHP() > 0)
				Game.redw = new Wall(uba2);
			    a = new Base(uba2);
			    uba.setText("Upgrade Base and Wall for " + a.getUpCost());
			    if (uba2 == 2){
				uba.setText("Base is at max level!");
				uba.setEnabled(false);
			    }
			}
		    }
		}
	    });

	but.add(usw);
	but.add(ub);
	but.add(ush);
	but.add(uba);

	this.add(but);
    }

    class KeyEvents extends KeyAdapter{
	public void keyPressed(KeyEvent e){
	    if (e.getKeyCode() == KeyEvent.VK_Q){
		a = new Sword(usw2);
		if (Game.gold >= a.getCost() && Game.unit.size() < Game.max && ctd == 0){
		    Game.unit.add(a);
		    Game.gold -= a.getCost();
		    ctd = 1;
		}
	    }
	    if (e.getKeyCode() == KeyEvent.VK_W){
		a = new Bow(ub2);
		if (Game.gold >= a.getCost() && Game.unit.size() < Game.max && ctd == 0){
		    Game.unit.add(a);
		    Game.gold -= a.getCost();
		    ctd = 1;
		}
	    }
	    if (e.getKeyCode() == KeyEvent.VK_E){
		a = new Shield(ush2);
		if (Game.gold >= a.getCost() && Game.unit.size() < Game.max && ctd == 0){
		    Game.unit.add(a);
		    Game.gold -= a.getCost();
		    ctd = 2;
		}
	    }
	    if (e.getKeyCode() == KeyEvent.VK_R){
		a = new Giant();
		if (Game.gold >= a.getCost() && Game.time <= 0 && Game.unit.size() < Game.max && ctd == 0){
		    Game.unit.add(a);
		    Game.gold -= a.getCost();
		    Game.time = 1200;
		    ctd = 4;
		}
	    }
	    try{
		if (e.getKeyCode() == KeyEvent.VK_A){
		    a = new Sword(usw2);
		    if (Game.gold >= a.getUpCost()){
			if (usw2 < 4){
			    usw2++;
			    Game.gold -= a.getUpCost();
			    a = new Sword(usw2);
			    busw.setText("Buy Swordsman for " + a.getCost());
			    uusw.setText("Upgrade Swordsman for " + a.getUpCost());
			    if (usw2 == 4){
				uusw.setText("Swordsman is at max level!");
			    }
			}
		    }
		}
		if (e.getKeyCode() == KeyEvent.VK_S){
		    a = new Bow(ub2);
		    if (Game.gold >= a.getUpCost()){
			if (ub2 < 4) {
			    ub2++;
			    Game.gold -= a.getUpCost();
			    a = new Bow(ub2);
			    bub.setText("Buy Archer for " + a.getCost());
			    uub.setText("Upgrade Archer for " + a.getUpCost());
			    if (ub2 == 4){
				uub.setText("Archer is at max level!");
			    }
			}
		    }
		}
		if (e.getKeyCode() == KeyEvent.VK_D){
		    a = new Shield(ush2);
		    if (Game.gold >= a.getUpCost()){
			if (ush2 < 4){
			    ush2++;
			    Game.gold -= a.getUpCost();
			    a = new Shield(ush2);
			    bush.setText("Buy Tank for " + a.getCost());
			    uush.setText("Upgrade Tank for " + a.getUpCost());
			    if (ush2 == 4){
				uush.setText("Tank is at max level!");
			    }
			}
		    }
		}
		if (e.getKeyCode() == KeyEvent.VK_F){
		    a = new Base(uba2);
		    if (Game.gold >= a.getUpCost()){
			if (uba2 < 2){
			    uba2++;
			    Game.gold -= a.getUpCost();
			    Game.red = new Base(uba2);
			    if (Game.redw.getHP() > 0)
				Game.redw = new Wall(uba2);
			    a = new Base(uba2);
			    uuba.setText("Upgrade Base for " + a.getUpCost());
			    if (uba2 == 2){
				uuba.setText("Base is at max level!");
			    }
			}
		    }
		}
	    }catch (Exception ex){}
	    if (e.getKeyCode() == KeyEvent.VK_U){
		a = new Sword(esw2);
		if (Game.gold2 >= a.getCost() && Game.enemy.size() < Game.max && ctd2 == 0){
		    Game.enemy.add(a);
		    Game.gold2 -= a.getCost();
		    ctd2 = 1;
		}
	    }
	    if (e.getKeyCode() == KeyEvent.VK_I){
		a = new Bow(eb2);
		if (Game.gold2 >= a.getCost() && Game.enemy.size() < Game.max && ctd2 == 0){
		    Game.enemy.add(a);
		    Game.gold2 -= a.getCost();
		    ctd2 = 1;
		}
	    }
	    if (e.getKeyCode() == KeyEvent.VK_O){
		a = new Shield(esh2);
		if (Game.gold2 >= a.getCost() && Game.enemy.size() < Game.max && ctd2 == 0){
		    Game.enemy.add(a);
		    Game.gold2 -= a.getCost();
		    ctd2 = 2;
		}
	    }
	    if (e.getKeyCode() == KeyEvent.VK_P){
		a = new Giant();
		if (Game.gold2 >= a.getCost() && Game.time2 <= 0 && Game.enemy.size() < Game.max && ctd2 == 0){
		    Game.enemy.add(a);
		    Game.gold2 -= a.getCost();
		    Game.time2 = 1200;
		    ctd2 = 4;
		}
	    }
	    try{
		if (e.getKeyCode() == KeyEvent.VK_J){
		    a = new Sword(esw2);
		    if (Game.gold2 >= a.getUpCost()){
			if (esw2 < 4){
			    esw2++;
			    Game.gold2 -= a.getUpCost();
			    a = new Sword(esw2);
			    besw.setText("Buy Swordsman for " + a.getCost());
			    uesw.setText("Upgrade Swordsman for " + a.getUpCost());
			    if (esw2 == 4){
				uesw.setText("Swordsman is at max level!");
			    }
			}
		    }
		}
		if (e.getKeyCode() == KeyEvent.VK_K){
		    a = new Bow(eb2);
		    if (Game.gold2 >= a.getUpCost()){
			if (eb2 < 4) {
			    eb2++;
			    Game.gold2 -= a.getUpCost();
			    a = new Bow(eb2);
			    beb.setText("Buy Archer for " + a.getCost());
			    ueb.setText("Upgrade Archer for " + a.getUpCost());
			    if (eb2 == 4){
				ueb.setText("Archer is at max level!");
			    }
			}
		    }
		}
		if (e.getKeyCode() == KeyEvent.VK_L){
		    a = new Shield(esh2);
		    if (Game.gold2 >= a.getUpCost()){
			if (esh2 < 4){
			    esh2++;
			    Game.gold2 -= a.getUpCost();
			    a = new Shield(esh2);
			    besh.setText("Buy Tank for " + a.getCost());
			    uesh.setText("Upgrade Tank for " + a.getUpCost());
			    if (esh2 == 4){
				uesh.setText("Tank is at max level!");
			    }
			}
		    }
		}
		if (e.getKeyCode() == KeyEvent.VK_SEMICOLON){
		    a = new Base(eba);
		    if (Game.gold2 >= a.getUpCost()){
			if (eba < 2){
			    eba++;
			    Game.gold2 -= a.getUpCost();
			    Game.blue = new Base(eba);
			    if (Game.bluew.getHP() > 0)
				Game.bluew = new Wall(eba);
			    a = new Base(eba);
			    ueba.setText("Upgrade Base for " + a.getUpCost());
			    if (eba == 2){
				ueba.setText("Base is at max level!");
			    }
			}
		    }
		}
	    }catch (Exception ex){}
	}

	public void keyTyped(KeyEvent e){
	}

	public void keyReleased(KeyEvent e){
	}
    }

    public void CPU(){
	for (int i = 0; i < 2; i++){
	    int a = rand.nextInt(4);
	    if (a == 0) {
		Unit lol = new Sword(usw2);
		if (Game.gold >= lol.getCost()){
		    Game.unit.add(lol);
		    Game.gold -= lol.getCost();
		    break;
		}
	    }else if (a == 1) {
		Unit lol = new Bow(ub2);
		if (Game.gold >= lol.getCost()){
		    Game.unit.add(lol);
		    Game.gold -= lol.getCost();
		    break;
		}
	    }else if (a == 2) {
		Unit lol = new Shield(ush2);
		if (Game.gold >= lol.getCost()){
		    Game.unit.add(lol);
		    Game.gold -= lol.getCost();
		    break;
		}
	    }else if (a == 3) {
		Unit lol = new Giant();
		if (Game.gold >= lol.getCost() && cd2 == 0){
		    Game.unit.add(lol);
		    Game.gold -= lol.getCost();
		    cd2 = 60;
		    break;
		}
	    }   
	}
	int b = rand.nextInt(4);
	if (b == 0) {
	    Unit lol = new Sword(usw2);
	    if (usw2 < 4 && Game.gold >= lol.getUpCost()){
		usw2++;
		Game.gold -= lol.getUpCost();
	    }
	}else if (b == 1) {
	    Unit lol = new Bow(ub2);
	    if (ub2 < 4 && Game.gold >= lol.getUpCost()){
		ub2++;
		Game.gold -= lol.getUpCost();
	    }
	}else if (b == 2) {
	    Unit lol = new Shield(ush2);
	    if (ush2 < 4 && Game.gold >= lol.getUpCost()){
		ush2++;
		Game.gold -= lol.getUpCost();
	    }
	}else if (b == 3) {
	    Unit lol = new Base(uba2);
	    if (uba2 < 2 && Game.gold >= lol.getUpCost()){
		uba2++;
		Game.gold -= lol.getUpCost();
		Game.red = new Base(uba2);
		if (Game.redw.getHP() > 0)
		    Game.redw = new Wall(uba2);
	    }
	}
	for (int i = 0; i < 2; i++){
	    int a = rand.nextInt(4);
	    if (a == 0) {
		Unit lol = new Sword(esw2);
		if (Game.gold2 >= lol.getCost()){
		    Game.enemy.add(lol);
		    Game.gold2 -= lol.getCost();
		    break;
		}
	    }else if (a == 1) {
		Unit lol = new Bow(eb2);
		if (Game.gold2 >= lol.getCost()){
		    Game.enemy.add(lol);
		    Game.gold2 -= lol.getCost();
		    break;
		}
	    }else if (a == 2) {
		Unit lol = new Shield(esh2);
		if (Game.gold2 >= lol.getCost()){
		    Game.enemy.add(lol);
		    Game.gold2 -= lol.getCost();
		    break;
		}
	    }else if (a == 3) {
		Unit lol = new Giant();
		if (Game.gold2 >= lol.getCost() && cd == 0){
		    Game.enemy.add(lol);
		    Game.gold2 -= lol.getCost();
		    cd = 60;
		    break;
		}
	    }   
	}
	b = rand.nextInt(4);
	if (b == 0) {
	    Unit lol = new Sword(esw2);
	    if (esw2 < 4 && Game.gold2 >= lol.getUpCost()){
		esw2++;
		Game.gold2 -= lol.getUpCost();
	    }
	}else if (b == 1) {
	    Unit lol = new Bow(eb2);
	    if (eb2 < 4 && Game.gold2 >= lol.getUpCost()){
		eb2++;
		Game.gold2 -= lol.getUpCost();
	    }
	}else if (b == 2) {
	    Unit lol = new Shield(esh2);
	    if (esh2 < 4 && Game.gold2 >= lol.getUpCost()){
		esh2++;
		Game.gold2 -= lol.getUpCost();
	    }
	}else if (b == 3) {
	    Unit lol = new Base(eba);
	    if (eba < 2 && Game.gold2 >= lol.getUpCost()){
		eba++;
		Game.gold2 -= lol.getUpCost();
		Game.blue = new Base(eba);
		if (Game.bluew.getHP() > 0)
		    Game.bluew = new Wall(eba);
	    }
	}
	if (cd > 0) cd--;
	if (cd2 > 0) cd2--;
    }

    public void actionPerformed(ActionEvent e){
	Unit trol = new Base(uba2);
	Unit trolo = new Base(eba);
	if (Game.red.hp > 0){
	    if (Game.red.hp < trol.getHP())
		Game.red.hp+=(int)(Math.random() * 100);
	    if (Game.red.hp > trol.getHP())
		Game.red.hp = trol.getHP();
	}
	if (Game.blue.hp > 0){
	    if (Game.blue.hp < trolo.getHP())
		Game.blue.hp+=(int)(Math.random()*100);
	    if (Game.blue.hp > trolo.getHP())
		Game.blue.hp = trolo.getHP();
	}
	if (ctd > 0) ctd--;
	if (ctd2 > 0) ctd2--;
	timer.restart();
    }

    public void music1(){
	AudioPlayer MGP = AudioPlayer.player;
	AudioStream BGM;
	AudioData MD;
	ContinuousAudioDataStream loop = null;
	try{
	    InputStream test = new FileInputStream("./Wav/Protectors of the Earth.wav");
	    BGM = new AudioStream(test);
	    AudioPlayer.player.start(BGM);
	}catch(Exception e){
	    e.printStackTrace();
	}
	try{
	    Thread.sleep(165000);
	    int randm = rand.nextInt(3);
	    if (randm == 0) music2();
	    if (randm == 1) music3();
	    if (randm == 2) music4();
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    public void music2(){
	AudioPlayer MGP = AudioPlayer.player;
	AudioStream BGM;
	AudioData MD;
	ContinuousAudioDataStream loop = null;
	try{
	    InputStream test = new FileInputStream("./Wav/To Glory.wav");
	    BGM = new AudioStream(test);
	    AudioPlayer.player.start(BGM);
	}catch(Exception e){
	    e.printStackTrace();
	}
	try{
	    Thread.sleep(273000);
	    int randm = rand.nextInt(3);
	    if (randm == 0) music1();
	    if (randm == 1) music3();
	    if (randm == 2) music4();
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    public void music3(){
	AudioPlayer MGP = AudioPlayer.player;
	AudioStream BGM;
	AudioData MD;
	ContinuousAudioDataStream loop = null;
	try{
	    InputStream test = new FileInputStream("./Wav/Infinite Legends.wav");
	    BGM = new AudioStream(test);
	    AudioPlayer.player.start(BGM);
	}catch(Exception e){
	    e.printStackTrace();
	}
	try{
	    Thread.sleep(120000);
	    int randm = rand.nextInt(3);
	    if (randm == 0) music1();
	    if (randm == 1) music2();
	    if (randm == 2) music4();
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    public void music4(){
	AudioPlayer MGP = AudioPlayer.player;
	AudioStream BGM;
	AudioData MD;
	ContinuousAudioDataStream loop = null;
	try{
	    InputStream test = new FileInputStream("./Wav/Archangel.wav");
	    BGM = new AudioStream(test);
	    AudioPlayer.player.start(BGM);
	}catch(Exception e){
	    e.printStackTrace();
	}
	try{
	    Thread.sleep(151000);
	    int randm = rand.nextInt(3);
	    if (randm == 0) music1();
	    if (randm == 1) music2();
	    if (randm == 2) music3();
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

    public static void main(String[] args){ 
	new Driver();
    } 
} 
