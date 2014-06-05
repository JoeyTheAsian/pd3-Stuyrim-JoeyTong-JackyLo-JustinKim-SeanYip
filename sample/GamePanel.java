import java.awt.Color;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
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
    private int windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height-37;
    private int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int height = windowHeight / 6;
    private int width = windowWidth;
    private BufferedImage bg;
    public Screen screen = new Screen();
    public GamePanel() {
	setLayout(null);
	setBounds(0, 0 , windowWidth, windowHeight);
	try {bg = ImageIO.read(new File("GUI Images/wood background.png"));}
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
	InventButton.setBounds(windowWidth/6*5,windowHeight/5*4,width/6,height/3);
	//get button texture
	Image i1 = new ImageIcon("GUI Images/Button.png").getImage().getScaledInstance
	    (InventButton.getWidth(),InventButton.getHeight(),java.awt.Image.SCALE_SMOOTH);
	InventButton.setIcon(new ImageIcon(i1));
	InventButton.setForeground(Color.white);
	InventButton.addActionListener(e -> {


	    });

	JButton PartyButton = new JButton("Party");
	PartyButton.setOpaque(false);
	PartyButton.setBorderPainted(false);
	PartyButton.setContentAreaFilled(false);
	PartyButton.setVerticalTextPosition(SwingConstants.CENTER);
	PartyButton.setHorizontalTextPosition(SwingConstants.CENTER);
	PartyButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	PartyButton.setBounds(windowWidth/6*5,windowHeight/5*4+height/3,width/6,height/3);
	PartyButton.setIcon(new ImageIcon(i1));
	PartyButton.setForeground(Color.white);
	PartyButton.addActionListener(e -> {

	    });
	JButton MenuButton = new JButton("Menu");
	MenuButton.setOpaque(false);
	MenuButton.setBorderPainted(false);
	MenuButton.setContentAreaFilled(false);
	MenuButton.setVerticalTextPosition(SwingConstants.CENTER);
	MenuButton.setHorizontalTextPosition(SwingConstants.CENTER);
	MenuButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	MenuButton.setBounds(windowWidth/6*5,windowHeight/5*4+(height/3*2),width/6,height/3);
	MenuButton.setIcon(new ImageIcon(i1));
	MenuButton.setForeground(Color.white);
	MenuButton.addActionListener(e -> {

	    });
	//GRABS ALL PARTY DATA INCLUDING HP, MANA, ETC
	JTextArea PlayerData = new JTextArea();
	PlayerData.setSize(width/6,height);
	PlayerData.setLocation(0,windowHeight/5*4);
	PlayerData.append("Player 1: \nHP: gethp()    |    Mana: getMana()     |    otherstuff");
	PlayerData.setOpaque(false);
	PlayerData.setVisible(true);
	//PUT THE PLAYERDATA IN HERE

	screen = new Screen();

	add(PlayerData);
	add(MenuButton);
	add(InventButton);
	add(PartyButton);
	add(screen);
	revalidate();
    }
	
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(bg,0,windowHeight/5*4,width,height, null);
    }
    //SCREEN CLASS
    public class Screen extends Canvas implements Runnable{
	private static final int MAX_FPS = 60;
	private static final int FPS_SAMPLE_SIZE = 6;
	
	private boolean[] keysPressed = new boolean[256];
	
	private ArrayList<Character> characters = new ArrayList<>();
	private ArrayList<Character> ai = new ArrayList<>();
	private Thread thread;
	private BufferedImage bg;

	//for testing putposes
	private Player slime = new Player("Slime.png", 100, 100);
	private Player bird = new Player("Bird.png", 250, 250);
	private Player giant = new Player("Giant.png", 250, 500);
	private Player swordsman = new Player("Swordsman.png", 500, 250);

	//screen dimensions
	private int screenHeight = ((Toolkit.getDefaultToolkit().getScreenSize().height-37)/5*4);
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private long prevTick = -1;
	private LinkedList<Long> frames = new LinkedList<>();
	private int averageFPS;
	private boolean running;


	public Screen() {
	    setSize(screenWidth, screenHeight);
	    try {bg = ImageIO.read(new File("GUI Images/success.jpg"));}
	    catch (Exception e) {Utilities.showErrorMessage(this, e);}
	    characters.add(slime);
	    //	ai.add(bird);
	    //ai.add(giant);
	    //	ai.add(swordsman);
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
	
	public void render(){
	    BufferStrategy bs = getBufferStrategy();
	    if(bs  == null){
		createBufferStrategy(3);
		return;
	    }
	    Graphics g= bs.getDrawGraphics();
	    g.drawImage(bg,0,0,screenWidth,screenHeight, null);
	    g.drawString(String.valueOf(averageFPS), 0, 0);
	    for (Character character : ai){
		character.setY(character.getY() + (int)(Math.random() * 10 - 5));
		character.setX(character.getX() + (int)(Math.random() * 10 - 5));
	    }
	    for (Character character : ai) {g.drawImage(character.getImage(), character.getX(), character.getY(), null);}
	    for (Character character : characters) {g.drawImage(character.getImage(), character.getX(), character.getY(), null);}
	    g.dispose();
	    bs.show();
	}
    
	public void run() {
	    while (running) {
		render();
		tick();
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
	
	public void tick() {
	    if (keysPressed[KeyEvent.VK_W] && (slime.getY() > 0)) {slime.setY(slime.getY() - 1);}
	    if (keysPressed[KeyEvent.VK_S] && (slime.getY() < screenHeight)) {slime.setY(slime.getY() + 1);}
	    if (keysPressed[KeyEvent.VK_A] && (slime.getY() > 0)) {slime.setX(slime.getX() - 1);}
	    if (keysPressed[KeyEvent.VK_D] && (slime.getY() < screenWidth)) {slime.setX(slime.getX() + 1);}
	
	    long pastTime = System.currentTimeMillis() - prevTick;
	    prevTick = System.currentTimeMillis();

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

	    // Only if the time passed since the previous tick is less than one
	    // second divided by the number of maximum FPS allowed do we delay
	    // ourselves to give Time time to catch up to our rendering.
	    if (pastTime < 1000.0 / MAX_FPS) {
		try {
		    Thread.sleep((1000 / MAX_FPS) - pastTime);
		    System.out.print(averageFPS + " ");
		} catch (InterruptedException e) {
		    Utilities.showErrorMessage(this, e);
		}
	    }
	}
    }
}
