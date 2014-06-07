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
    //dimensions of the window
    private int windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height-37;
    private int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    //dimensions of the bottom portion of the screen with all the buttons
    private int height = windowHeight /5+10;
    private int width = windowWidth;
	
    private boolean[] keysPressed = new boolean[256];
	
    private BufferedImage bg;//background
    //player inventory will probably be removed in the future after testing
    private Inventory inventory = new Inventory();

    public Screen screen = new Screen();
    public PartyPanel party = new PartyPanel();
    public InventoryPanel invent = new InventoryPanel(inventory);
    
    
    public GamePanel() {
	setLayout(null);
	setBounds(0, 0 , windowWidth, windowHeight);
	try {bg = ImageIO.read(new File("GUI Images/trimmed paper background.png"));}
	catch (Exception e) {Utilities.showErrorMessage(this, e);}
	setVisible(true);


	//add buttons
	JButton InventButton = new JButton("Inventory");
	InventButton.setOpaque(false);
	InventButton.setBorderPainted(false);
	InventButton.setContentAreaFilled(false);
	InventButton.setVerticalTextPosition(SwingConstants.CENTER);
	InventButton.setHorizontalTextPosition(SwingConstants.CENTER);
	InventButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	InventButton.setBounds(windowWidth/6*5,windowHeight-height+10,width/6,height/4);
	//get button textures
	Image i1 = new ImageIcon("GUI Images/Button.png").getImage().getScaledInstance           
	    (InventButton.getWidth(),InventButton.getHeight(),java.awt.Image.SCALE_SMOOTH);
	Image i2 = new ImageIcon("GUI Images/Button1.png").getImage().getScaledInstance
	    (InventButton.getWidth(),InventButton.getHeight(),java.awt.Image.SCALE_SMOOTH);
	InventButton.setIcon(new ImageIcon(i1));
	InventButton.setForeground(Color.white);
	InventButton.addActionListener(e -> {
		keysPressed[VK_W] = false;
		keysPressed[VK_S] = false;
		keysPressed[VK_A] = false;
		keysPressed[VK_D] = false;
		if (invent.isVisible()) {
		    invent.setVisible(false);
		    screen.requestFocusInWindow();
		    InventButton.setIcon(new ImageIcon(i1));
		}else if(!invent.isVisible()){
		    screen.pause();
		    invent.setVisible(true);
		    invent.requestFocusInWindow();
		    //invent.updateInventory(inventory);
		    InventButton.setIcon(new ImageIcon(i2));
		}
	    });
	JButton PartyButton = new JButton("Party");
	PartyButton.setOpaque(false);
	PartyButton.setBorderPainted(false);
	PartyButton.setContentAreaFilled(false);
	PartyButton.setVerticalTextPosition(SwingConstants.CENTER);
	PartyButton.setHorizontalTextPosition(SwingConstants.CENTER);
	PartyButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	PartyButton.setBounds(windowWidth/6*5,(windowHeight-height)+(height/4)+10,width/6,height/4);
	PartyButton.setIcon(new ImageIcon(i1));
	PartyButton.setForeground(Color.white);
	PartyButton.addActionListener(e -> {
		if(party.isVisible()){
		    party.setVisible(false);
		    screen.requestFocusInWindow();
		    PartyButton.setIcon(new ImageIcon(i1));
		    screen.resume();
		}else if(!party.isVisible()){
		    screen.pause();
		    party.setVisible(true);
		    party.requestFocusInWindow();
		    PartyButton.setIcon(new ImageIcon(i2));		       	            
		}
	    });
	JButton MenuButton = new JButton("Menu");
	MenuButton.setOpaque(false);
	MenuButton.setBorderPainted(false);
	MenuButton.setContentAreaFilled(false);
	MenuButton.setVerticalTextPosition(SwingConstants.CENTER);
	MenuButton.setHorizontalTextPosition(SwingConstants.CENTER);
	MenuButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	MenuButton.setBounds(windowWidth/6*5,(windowHeight-height)+(height/4*2)+10,width/6,height/4);
	MenuButton.setIcon(new ImageIcon(i1));
	MenuButton.setForeground(Color.white);
	MenuButton.addActionListener(e -> {
		screen.requestFocusInWindow();
	    });

	//GRABS ALL PARTY DATA INCLUDING HP, MANA, ETC
	JTextArea PlayerData = new JTextArea();
	PlayerData.setSelectedTextColor(Color.WHITE);
	PlayerData.setSize(width/6,height);
	PlayerData.setLocation(0,windowHeight/5*4);
	PlayerData.setOpaque(false);
	PlayerData.setVisible(true);
	PlayerData.setForeground(Color.WHITE);
	PlayerData.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	//PUT THE PLAYERDATA IN HER
	PlayerData.append("Player 1: \nHP: gethp()    |    Mana: getMana()     |    otherstuff");

	//creates inventory panel
	invent.setSize(windowWidth/2, windowHeight/2);
	invent.setLocation(windowWidth/4, windowHeight/4);
	

	screen = new Screen();

	add(party);
	add(invent);
	add(PlayerData);
	add(MenuButton);
	add(InventButton);
	add(PartyButton);
	add(screen);
	revalidate();
    }
	
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(bg,0,windowHeight-height,width,height,null);
    }

    //SCREEN CLASS
    public class Screen extends Canvas implements Runnable{
	//FPS counter variables
	private static final int MAX_FPS = 60;
	private static final int FPS_SAMPLE_SIZE = 6;

	//arraylists containing all entities on screen, painted by while loop in screen
	private ArrayList<Character> characters = new ArrayList<>();
	private ArrayList<Character> ai = new ArrayList<>();
	private Thread thread;

	private BufferedImage flickerStop;

	//SCREEN dimensions
	private int screenHeight = ((Toolkit.getDefaultToolkit().getScreenSize().height-37)/5*4-10);
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

	//for testing putposes
	private Player slime = new Player("Slime.png", screenWidth/2, screenHeight/2);
	private Player bird = new Player("Bird.png", 250, 250);
	private Player giant = new Player("Giant.png", 250, 500);
	private Player swordsman = new Player("Swordsman.png", 500, 250);
	private int mapX = 0;
	private int mapY = 0;

	// The width and height of each tile in pixels
	private static final int TILE_SCALE = 60;
	private Map currentMap;

	//more FPS stuff
	private long prevTick = -1;
	private LinkedList<Long> frames = new LinkedList<>();
	private int averageFPS;
	private boolean running;
	

	public Screen() {
	    setSize(screenWidth, screenHeight);
	    try{flickerStop =ImageIO.read(new File("GUI Images/flickerStop.png"));
	    }catch(Exception e){Utilities.showErrorMessage(this,e);}
	    characters.add(slime);
	    ai.add(bird);
	    ai.add(giant);
	    ai.add(swordsman);
            // Temporary code until tile textures are done
            currentMap = new Map();
            currentMap.setTile(5, 5, Tile.WALL);

	    //placeholder input listeners
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
		if (distance < 100.0){}
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
	//updates game screen data
	public void tick() {
	    if (keysPressed[VK_W] && (slime.getY() > 0)) {
		//	slime.setY(slime.getY() - 1);
		mapY+=2;
		for (Character monster : ai)
		    monster.setY(monster.getY()+2);
	    }
	    if (keysPressed[VK_S] && (slime.getY() < screenHeight)) {
		//	slime.setY(slime.getY() + 1);
		mapY-=2;
		for (Character monster : ai)
		    monster.setY(monster.getY()-2);
	    }
	    if (keysPressed[VK_A] && (slime.getY() > 0)) {
		//	slime.setX(slime.getX() - 1);
		mapX +=2;
		for (Character monster : ai)
		    monster.setX(monster.getX()+2);
	    }
	    if (keysPressed[VK_D] && (slime.getY() < screenWidth)) {
		//	slime.setX(slime.getX() + 1);
		mapX-=2;
		for (Character monster : ai)
		    monster.setX(monster.getX()-2);
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
