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
import java.lang.InterruptedException;
import java.lang.Thread;

public class Screen extends Canvas implements Runnable{
    private Thread Thread;
    private BufferedImage image;
    private int height = ((Toolkit.getDefaultToolkit().getScreenSize().height-37)/5*4);
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private boolean running;
    public Screen(){
	setSize(width, height);
	try {image = ImageIO.read(new File("success.jpg"));}
	catch (Exception e) {Utilities.showErrorMessage(this, e);}
        setVisible(true);
    }
    public synchronized void start(){
	running = true;
	Thread = new Thread(this);
	Thread.start();
    }
    public synchronized void stop(){
	try{
	    Thread.join();
	}catch(InterruptedException e){
	    e.printStackTrace();
	}
    }
    public void run(){
	while(running){
	    paint(getGraphics());
	}
    }
    public void paint(Graphics g){
	    BufferStrategy bs = getBufferStrategy();
	    if(bs == null){
		createBufferStrategy(3);
		return;
	    }
	    g = bs.getDrawGraphics();
	    g.drawImage(image,0,0,width,height, null);
	    g.dispose();
	    bs.show();
    }
}
