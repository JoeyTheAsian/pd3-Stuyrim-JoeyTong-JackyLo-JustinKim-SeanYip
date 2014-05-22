import java.awt.Container;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.Toolkit;
public class Driver extends JFrame{
    public Driver() {
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    double w = screenSize.getWidth();
	    double h = screenSize.getHeight();
	    if ((h/w)< .7){
		h -= 36;
		w = h*1.429;
	    }else if (h/w > .7){
		h = (w*.7)-36;
	    }
	    setSize((int)w,(int)h);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setTitle("The Senior Scrolls: Stuyrim");
	    setLayout(null);
	    setVisible(true);
	    setResizable(false);
	    addKeyListener(new KeyListener(){
		    public void keyReleased (KeyEvent e){
			if (useKey){
			    //int id = e.getID();
			    int keycode = e.getKeyCode();
			    reader r = new reader();
			    int chosen = 0; 
			    int endChoice = 0;
			    if (keycode == KeyEvent.VK_w){
				
			    }
			    else if (keycode == KeyEvent.VK_a){

			    }
			    else if (keycode == KeyEvent.VK_3){

			    }
			    else if (keycode == KeyEvent.VK_4){

			    }
			    else {}
			}
		    }
		}
			
    }
     
    public void paint(Graphics g) {
	super.paint(g);
	
    }

    
    public static void main(String[]args){
	new Driver();
    }
}
