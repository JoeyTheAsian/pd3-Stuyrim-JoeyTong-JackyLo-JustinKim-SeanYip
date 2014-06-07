import java.awt.Color;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_W;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
	private boolean[] keysPressed = new boolean[256];
	private BufferedImage bg; //background
    private int windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height-37;
    private int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    //dimensions of the bottom portion of the screen with all the buttons
    private int height = windowHeight /5+10;
    private int width = windowWidth;
    //player inventory will probably be removed in the future after testing
    private Inventory inventory = new Inventory();
    private InventoryPanel invent = new InventoryPanel(inventory);
    private PartyPanel party = new PartyPanel();
    Screen screen = new Screen();
    
    public GamePanel() {
	setLayout(null);
	setBounds(0, 0 , windowWidth, windowHeight);
	try {bg = ImageIO.read(new File("GUI Images/trimmed paper background.png"));}
	catch (Exception e) {Utilities.showErrorMessage(this, e);}
	setVisible(true);

	//add buttons
	JButton inventoryButton = new JButton("Inventory");
	inventoryButton.setOpaque(false);
	inventoryButton.setBorderPainted(false);
	inventoryButton.setContentAreaFilled(false);
	inventoryButton.setVerticalTextPosition(SwingConstants.CENTER);
	inventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
	inventoryButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	inventoryButton.setBounds(windowWidth/6*5,windowHeight-height+10,width/6,height/4);
	//get button textures
	Image i1 = new ImageIcon("GUI Images/Button.png").getImage().getScaledInstance(inventoryButton.getWidth(),inventoryButton.getHeight(),java.awt.Image.SCALE_SMOOTH);
	Image i2 = new ImageIcon("GUI Images/Button1.png").getImage().getScaledInstance(inventoryButton.getWidth(),inventoryButton.getHeight(),java.awt.Image.SCALE_SMOOTH);
	inventoryButton.setIcon(new ImageIcon(i1));
	inventoryButton.setForeground(Color.white);
	inventoryButton.addActionListener(e -> {
		keysPressed[VK_W] = false;
		keysPressed[VK_S] = false;
		keysPressed[VK_A] = false;
		keysPressed[VK_D] = false;
		if (invent.isVisible()) {
		    invent.setVisible(false);
		    screen.requestFocusInWindow();
		    inventoryButton.setIcon(new ImageIcon(i1));
		}else if(!invent.isVisible()){
		    screen.pause();
		    invent.setVisible(true);
		    invent.requestFocusInWindow();
		    //invent.updateInventory(inventory);
		    inventoryButton.setIcon(new ImageIcon(i2));
		}
	    });
	JButton partyButton = new JButton("Party");
	partyButton.setOpaque(false);
	partyButton.setBorderPainted(false);
	partyButton.setContentAreaFilled(false);
	partyButton.setVerticalTextPosition(SwingConstants.CENTER);
	partyButton.setHorizontalTextPosition(SwingConstants.CENTER);
	partyButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	partyButton.setBounds(windowWidth/6*5,(windowHeight-height)+(height/4)+10,width/6,height/4);
	partyButton.setIcon(new ImageIcon(i1));
	partyButton.setForeground(Color.white);
	partyButton.addActionListener(e -> {
		if(party.isVisible()){
		    party.setVisible(false);
		    screen.requestFocusInWindow();
		    partyButton.setIcon(new ImageIcon(i1));
		    screen.resume();
		}else if(!party.isVisible()){
		    screen.pause();
		    party.setVisible(true);
		    party.requestFocusInWindow();
		    partyButton.setIcon(new ImageIcon(i2));		       	            
		}
	    });
	JButton menuButton = new JButton("Menu");
	menuButton.setOpaque(false);
	menuButton.setBorderPainted(false);
	menuButton.setContentAreaFilled(false);
	menuButton.setVerticalTextPosition(SwingConstants.CENTER);
	menuButton.setHorizontalTextPosition(SwingConstants.CENTER);
	menuButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	menuButton.setBounds(windowWidth/6*5,(windowHeight-height)+(height/4*2)+10,width/6,height/4);
	menuButton.setIcon(new ImageIcon(i1));
	menuButton.setForeground(Color.white);
	menuButton.addActionListener(e -> {
		screen.requestFocusInWindow();
	    });

	//GRABS ALL PARTY DATA INCLUDING HP, MANA, ETC
	JTextArea playerData = new JTextArea();
	playerData.setSelectedTextColor(Color.WHITE);
	playerData.setSize(width/6,height);
	playerData.setLocation(0,windowHeight/5*4);
	playerData.setOpaque(false);
	playerData.setVisible(true);
	playerData.setForeground(Color.WHITE);
	playerData.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	//PUT THE PLAYERDATA IN HER
	playerData.append("Player 1: \nHP: gethp()    |    Mana: getMana()     |    otherstuff");

	//creates inventory panel
	invent.setSize(windowWidth/2, windowHeight/2);
	invent.setLocation(windowWidth/4, windowHeight/4);
	

	screen = new Screen();

	add(party);
	add(invent);
	add(playerData);
	add(menuButton);
	add(inventoryButton);
	add(partyButton);
	add(screen);
	revalidate();
    }
	
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(bg,0,windowHeight-height,width,height,null);
    }

    public class Screen extends Canvas implements Runnable{
	//FPS counter variables
	private static final int MAX_FPS = 60;
	private static final int FPS_SAMPLE_SIZE = 6;
	// The width and height of each tile in pixels
	private static final int TILE_SCALE = 60;
	//arraylists containing all entities on screen, painted by while loop in screen
	private ArrayList<Character> characters = new ArrayList<>();
	private ArrayList<Character> ai = new ArrayList<>();
	private boolean running;
	private BufferedImage flickerStop;
	private int averageFPS;
	private int mapX = 0;
	private int mapY = 0;
	//SCREEN dimensions
	private int screenHeight = ((Toolkit.getDefaultToolkit().getScreenSize().height-37)/5*4-10);
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private LinkedList<Long> frames = new LinkedList<>();
	private long prevTick = -1;
	private Map currentMap;
	//for testing putposes
	private Player slime = new Player("Slime.png", screenWidth/2, screenHeight/2);
	private Player bird = new Player("Bird.png", 250, 250);
	private Player giant = new Player("Giant.png", 250, 500);
	private Player swordsman = new Player("Swordsman.png", 500, 250);
	private Thread thread;
	

	public Screen() {
	    setSize(screenWidth, screenHeight);
	    try{flickerStop =ImageIO.read(new File("GUI Images/flickerStop.png"));
	    }catch(Exception e){Utilities.showErrorMessage(this,e);}
	    characters.add(slime);
	    //ai.add(bird);
	    //ai.add(giant);
	    //ai.add(swordsman);
            // Temporary code until tile textures are done
            currentMap = new Map();
            currentMap.setTile(5, 5, Tile.WALL);
		addKeyListener(new KeyListener() {
		    public void keyPressed(KeyEvent e) {keysPressed[e.getKeyCode()] = true;}
		    public void keyReleased(KeyEvent e) {keysPressed[e.getKeyCode()] = false;}
		    public void keyTyped(KeyEvent e) {}
		});
	    addMouseListener(new MouseListener() {
		    public void mouseClicked(MouseEvent e) {}
		    public void mouseEntered(MouseEvent e) {}
		    public void mouseExited(MouseEvent e) {}
		    public void mousePressed(MouseEvent e) {}
		    public void mouseReleased(MouseEvent e) {}
		});
	    addMouseMotionListener(new MouseMotionListener() {
		    public void mouseDragged(MouseEvent e) {}
		    public void mouseMoved(MouseEvent e) {}
		});
	    addMouseWheelListener(new MouseWheelListener() {
		    public void mouseWheelMoved(MouseWheelEvent e) {}
		});
	    setVisible(true);
	}

	//renders the screen
	public void render(){
	    BufferStrategy bs = getBufferStrategy();
	    if(bs  == null){
		createBufferStrategy(3);
		return;
	    }
	    Graphics g= bs.getDrawGraphics();
	    //draws black screen to prevent layered images and flicker
	    g.drawImage(flickerStop,0,0,screenWidth,screenHeight, null);
	    // Draw the current map
	    drawMap(g);
	    //draw fps
	    g.setColor(Color.GREEN);
	    g.drawString("FPS: " + averageFPS, 0, 20);

	    //loops and draws all the entities players/monsters
	    for (Character character : ai){
		double changeX = slime.getX() - character.getX();
		double changeY = slime.getY() - character.getY();
		double distance = Math.sqrt( changeX*changeX + changeY*changeY );
		if (distance < 250.0 || distance > 750){}
		else{
		    character.setX(character.getX() + (int)(2*changeX/distance));
		    character.setY(character.getY() + (int)(2*changeY/distance));
		}
	    }
	    for (Character character : ai) {
		g.drawImage(character.getImage(), character.getX(), character.getY(), null);
	    }
	    for (Character character : characters) {
		g.drawImage(character.getImage(), character.getX(), character.getY(), null);
	    }
	    g.dispose();
	    bs.show();
	}

        // Renders the tilemap of the current map to the screen
        private void drawMap(Graphics g) {
            Tile[][] tilemap = currentMap.getTileMap();
            for (int i = 0; i < tilemap.length; i++) {
                for (int j = 0; j < tilemap[i].length; j++) {
                    drawTile(i, j, tilemap[i][j], g);
                }
            }
        }

        private void drawTile(int x, int y, Tile tile, Graphics g) {
            g.setColor(tile.color);
            g.fillRect(x * TILE_SCALE + mapX, y * TILE_SCALE + mapY, TILE_SCALE, TILE_SCALE);
        }
    
	public void run() {
	    while (running) {
		tick();
		render();
	    }

	}
	
	public synchronized void start() {
	    running = true;
	    thread = new Thread(this);
	    thread.start();
	}
	
	public synchronized void stop() {
	    try {thread.join();}
	    catch (InterruptedException e) {Utilities.showErrorMessage(this, e);}
	}
	public void pause(){
	    //running = false;
	}
	public void resume(){
	    //    running = true;
	}
	//chance of spawning a monster
	public void chanceOfSpawn(){
	    int[][]side = {{0,(int)(Math.random()*screenHeight)},
			   {screenWidth,(int)(Math.random()*screenHeight)},
			   {(int)(Math.random()*screenWidth),0},
			   {(int)(Math.random()*screenWidth),screenHeight}};
	    double chance = Math.random();
	    int temp = (int)(Math.random()*4);
	    if (chance > 0.003)
		return;
	    else if (chance > 0.002)
		ai.add(new Player("Swordsman.png",side[temp][0],side[temp][1]));
	    else if (chance > 0.001)
		ai.add(new Player("Bird.png",side[temp][0],side[temp][1]));
	    else
		ai.add(new Player("Giant.png",side[temp][0],side[temp][1]));
	}

	//updates game screen data
	public void tick() {
	    if (keysPressed[VK_W] && (slime.getY() > 0)) {
		//	slime.setY(slime.getY() - 1);
		mapY+=2;
		for (Character monster : ai)
		    monster.setY(monster.getY()+2);
		chanceOfSpawn();
	    }
	    if (keysPressed[VK_S] && (slime.getY() < screenHeight)) {
		//	slime.setY(slime.getY() + 1);
		mapY-=2;
		for (Character monster : ai)
		    monster.setY(monster.getY()-2);
		chanceOfSpawn();
	    }
	    if (keysPressed[VK_A] && (slime.getY() > 0)) {
		//	slime.setX(slime.getX() - 1);
		mapX +=2;
		for (Character monster : ai)
		    monster.setX(monster.getX()+2);
		chanceOfSpawn();
	    }
	    if (keysPressed[VK_D] && (slime.getY() < screenWidth)) {
		//	slime.setX(slime.getX() + 1);
		mapX-=2;
		for (Character monster : ai)
		    monster.setX(monster.getX()-2);
		chanceOfSpawn();
	    }
	
	    long pastTime = System.currentTimeMillis() - prevTick;

	    if (frames.size() == FPS_SAMPLE_SIZE) {
		frames.remove();
	    }
	    frames.add(pastTime);

	    // Calculate average FPS
	    long sum = 0;
	    for (long frame : frames) {
		sum += frame;
	    }
	    long averageFrame = sum / FPS_SAMPLE_SIZE;
	    averageFPS = (int)(1000 / averageFrame);
	    prevTick = System.currentTimeMillis();
	    // Only if the time passed since the previous tick is less than one
	    // second divided by the number of maximum FPS allowed do we delay
	    // ourselves to give Time time to catch up to our rendering.
	    if (pastTime < 1000.0 / MAX_FPS) {
		try {
		    Thread.sleep((long)(1000.0 / MAX_FPS)-pastTime);
		} catch (InterruptedException e) {
		    Utilities.showErrorMessage(this, e);
		}
	    }
	}
    }
}
