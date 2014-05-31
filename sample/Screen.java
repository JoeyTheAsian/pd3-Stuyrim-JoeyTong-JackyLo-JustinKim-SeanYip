import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Screen extends Canvas implements Runnable {
	private ArrayList<Character> characters = new ArrayList<>();
    private Thread thread;
    private BufferedImage image;
	private Character slime = new Character("Slime.png", 0, 0);
    private int height = ((Toolkit.getDefaultToolkit().getScreenSize().height-37)/5*4);
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private boolean running;

    public Screen() {
		setSize(width, height);
		try {image = ImageIO.read(new File("success.jpg"));}
		catch (Exception e) {Utilities.showErrorMessage(this, e);}
		characters.add(slime);
		addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {}
				public void keyTyped(KeyEvent e) {
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
					System.out.println(slime.getX() + ", " + slime.getY());
					repaint();
				}
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
		for (Character character : characters) {g.drawImage(character.getImage(), character.getX(), character.getY(), null);}
		g.dispose();
		bs.show();
    }
}
