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
import javax.imageio.ImageIO;
import javax.swing.*;

public class Screen extends Canvas implements Runnable, ActionListener {
	private ArrayList<Character> characters = new ArrayList<>();
        private ArrayList<Character> ai = new ArrayList<>();
	private Thread thread;
	private BufferedImage image;
	private Player slime = new Player("Slime.png", 100, 100);
        private Player bird = new Player("Bird.png", 250, 250);
        private Player giant = new Player("Giant.png", 250, 500);
        private Player swordsman = new Player("Swordsman.png", 500, 250);
	private int height = ((Toolkit.getDefaultToolkit().getScreenSize().height-37)/5*4);
	private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	private boolean running;
        private Timer timer = new Timer(100,this);

	public Screen() {
		setSize(width, height);
		try {image = ImageIO.read(new File("success.jpg"));}
		catch (Exception e) {Utilities.showErrorMessage(this, e);}
		characters.add(slime);
		ai.add(bird);
		ai.add(giant);
		ai.add(swordsman);
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
					repaint();
				}
		    });
		timer.start();
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
		for (Character character : ai) {g.drawImage(character.getImage(), character.getX(), character.getY(), null);}
		for (Character character : characters) {g.drawImage(character.getImage(), character.getX(), character.getY(), null);}
		g.dispose();
		bs.show();
	}
		

    
        public void actionPerformed(ActionEvent q){
	    for (Character character : ai){
		character.setY(character.getY() + (int)(Math.random() * 10 - 5));
		character.setX(character.getX() + (int)(Math.random() * 10 - 5));
	    }
	    repaint();
	}
}
