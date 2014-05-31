import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.io.File;
import javax.imageio.ImageIO;

public class Screen extends Canvas implements Runnable {
    private Thread thread;
    private BufferedImage image, character;
    private int height = ((Toolkit.getDefaultToolkit().getScreenSize().height-37)/5*4);
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private boolean running;
    public int x = 0, y = 0;

    public Screen() {
		setSize(width, height);
		try {
			image = ImageIO.read(new File("success.jpg"));
			character = ImageIO.read(new File("Slime.png"));
		}
		catch (Exception e) {Utilities.showErrorMessage(this, e);}
		addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent e) {
					System.out.print(e + " ");
					System.out.println((e.getKeyCode() == KeyEvent.VK_W) || (e.getKeyCode() == KeyEvent.VK_S) || (e.getKeyCode() == KeyEvent.VK_A) || (e.getKeyCode() == KeyEvent.VK_D));
					if (e.getKeyCode() == KeyEvent.VK_W) {
						if (y == 0) {return;}
						y -= character.getHeight();
						getGraphics().drawImage(image, x, y, Color.WHITE, null);
					}
					if (e.getKeyCode() == KeyEvent.VK_S) {
						if (y >= (height - character.getHeight())) {return;}
						y += character.getHeight();
						getGraphics().drawImage(image, x, y, Color.WHITE, null);
					}
					if (e.getKeyCode() == KeyEvent.VK_A) {
						if (x == 0) {return;}
						x -= character.getWidth();
						getGraphics().drawImage(image, x, y, Color.WHITE, null);
					}
					if (e.getKeyCode() == KeyEvent.VK_D) {
						if (x >= (width - character.getWidth())) {return;}
						x += character.getWidth();
						getGraphics().drawImage(image, x, y, Color.WHITE, null);
					}
				}
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
	
    public void update() {}
 
    public void paint(Graphics g) {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
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
