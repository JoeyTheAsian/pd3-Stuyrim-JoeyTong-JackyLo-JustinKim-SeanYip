import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Canvas;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import java.lang.InterruptedException;
import java.lang.Thread;


public class Screen extends Canvas implements Runnable{
    private Thread thread;
    private BufferedImage image, character;
    private int height = ((Toolkit.getDefaultToolkit().getScreenSize().height-37)/5*4);
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private boolean running;
    public int x = 0, y = 0;

    public Screen(){
	setSize(width, height);
	try {image = ImageIO.read(new File("success.jpg"));
	     character = ImageIO.read(new File("Slime.png"));}
	catch (Exception e) {Utilities.showErrorMessage(this, e);}
        setVisible(true);
    }

    public synchronized void start(){
	running = true;
	thread = new Thread(this);
	thread.start();
    }
    public synchronized void stop(){
	try{
	    thread.join();
	}catch(InterruptedException e){
	    e.printStackTrace();
	}
    }
    public void run(){
	/*	long prevTime = System.nanoTime();
	final double ns = 1000000000.0/60.0;
	double delta = 0;	

	while(running){
	    long now = System.nanoTime();
	    delta += (now-prevTime)/ns;
	    prevTime = now;
	    while(delta>=1){
		update();
		delta--;
		}}*/
	    repaint();
	    update();
	
    }
    public void update(){
	
    }
 
    public void paint(Graphics g){
	BufferStrategy bs = getBufferStrategy();
	if(bs == null){
	    createBufferStrategy(3);
	    return;
	}
	g = bs.getDrawGraphics();
	g.drawImage(image,0,0,width,height, null);
	g.drawImage(character, x,y,character.getHeight(), character.getWidth(), null);
	g.dispose();
	bs.show();
    }
}
