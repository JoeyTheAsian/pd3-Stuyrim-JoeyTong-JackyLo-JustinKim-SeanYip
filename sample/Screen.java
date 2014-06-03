import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Screen extends Canvas implements Runnable{

    private static final int MAX_FPS = 60;
    private static final int FPS_SAMPLE_SIZE = 6;

    private ArrayList<Character> characters = new ArrayList<>();
    private ArrayList<Character> ai = new ArrayList<>();
    private Thread thread;
    private BufferedImage image;

    //for testing putposes
    private Player slime = new Player("Slime.png", 100, 100);
    private Player bird = new Player("Bird.png", 250, 250);
    private Player giant = new Player("Giant.png", 250, 500);
    private Player swordsman = new Player("Swordsman.png", 500, 250);
    //JFrame frame = (JFrame)this.getParent().getTopLevelAncestor();  

    //screen dimensions
    private int height = ((Toolkit.getDefaultToolkit().getScreenSize().height-37)/5*4);
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;

    private long prevTick;
    private LinkedList<Long> frames;
    private int averageFPS;
    private boolean running;
    //    private Timer timer = new Timer(100,this);

    public Screen() {
        prevTick = -1;
        frames = new LinkedList<Long>();
	setSize(width, height);
	try {image = ImageIO.read(new File("GUI Images/success.jpg"));}
	catch (Exception e) {Utilities.showErrorMessage(this, e);}
	characters.add(slime);
	ai.add(bird);
	ai.add(giant);
	ai.add(swordsman);
	addKeyListener(new KeyListener() {
		public void keyPressed(KeyEvent e) {
		    //don't put control logic into screen class,do it in player and game engine
		    //create movement loop while key is pressed set move boolean to true in keypressed, set to false in keyreleased
		    if (e.getKeyChar() == 'w') {
			if (slime.getY() == 0) {return;}
			slime.setY(slime.getY() - 5);
		    }
		    if (e.getKeyChar() == 's') {
			if (slime.getY() >= (height - slime.getHeight())) {return;}
			slime.setY(slime.getY() + 5);
		    }
		    if (e.getKeyChar() == 'a') {
			if (slime.getX() == 0) {return;}
			slime.setX(slime.getX() - 5);
		    }
		    if (e.getKeyChar() == 'd') {
			if (slime.getX() >= (width - slime.getWidth())) {return;}
			slime.setX(slime.getX() + 5);
		    }
		    repaint();}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	    });	
	setVisible(true);
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
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public void run() {
        running = true;
	while(running) {
            repaint();
            tick();
	}
    }
	
    public void update() {}
 
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

}
