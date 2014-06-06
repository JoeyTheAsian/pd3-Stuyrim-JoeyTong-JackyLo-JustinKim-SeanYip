import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.imageio.ImageIO;

public class Screen extends Canvas implements Runnable {
    private static final int MAX_FPS = 60;
    private static final int FPS_SAMPLE_SIZE = 6;
	
    private boolean[] keysPressed = new boolean[256];
	
    private ArrayList<Character> characters = new ArrayList<>();
    private ArrayList<Character> ai = new ArrayList<>();
    private Thread thread;
    private BufferedImage image;

    //for testing putposes
    private Player slime = new Player("Slime.png", 100, 100);
    private Player bird = new Player("Bird.png", 250, 250);
    private Player giant = new Player("Giant.png", 250, 500);
    private Player swordsman = new Player("Swordsman.png", 500, 250);

    //screen dimensions
    private int height = ((Toolkit.getDefaultToolkit().getScreenSize().height-73)/5*4);
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
    private long prevTick = -1;
    private LinkedList<Long> frames = new LinkedList<>();
    private int averageFPS;
    private boolean running;


    public Screen() {
	setSize(width, height);
	try {image = ImageIO.read(new File("GUI Images/success.jpg"));}
	catch (Exception e) {Utilities.showErrorMessage(this, e);}
	characters.add(slime);
	ai.add(bird);
	ai.add(giant);
	ai.add(swordsman);
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
	start();
    }
	
    public void paint(Graphics g) {
	BufferStrategy bs = getBufferStrategy();
	if (bs == null) {
	    createBufferStrategy(3);
	    return;
	}
	g = bs.getDrawGraphics();
	g.drawImage(image,0,0,width,height, null);
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
        running = true;
	while (running) {
            repaint();
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
	//System.out.println("tick");
	if (keysPressed[KeyEvent.VK_W] && (slime.getY() > 0)) {slime.setY(slime.getY() - 1);}
	if (keysPressed[KeyEvent.VK_S] && (slime.getY() < height)) {slime.setY(slime.getY() + 1);}
	if (keysPressed[KeyEvent.VK_A] && (slime.getY() > 0)) {slime.setX(slime.getX() - 1);}
	if (keysPressed[KeyEvent.VK_D] && (slime.getY() > width)) {slime.setX(slime.getX() + 1);}
	//	System.out.println(slime.getX() + ", " + slime.getY());

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
	System.out.println(averageFPS);
        // Only if the time passed since the previous tick is less than one
        // second divided by the number of maximum FPS allowed do we delay
        // ourselves to give Time time to catch up to our rendering.
        if (pastTime < 1000.0 / MAX_FPS) {
            try {
                Thread.sleep((1000 / MAX_FPS) - pastTime);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
