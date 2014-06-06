import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.io.File;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public class PartyPanel extends JPanel{
    private int height = (Toolkit.getDefaultToolkit().getScreenSize().height-45)/5*4;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width/4;
    private BufferedImage image; 
    public PartyPanel(){
	try{image = ImageIO.read(new File("GUI Images/trimmed wood background.png"));
	}catch(Exception e){
	    Utilities.showErrorMessage(this,e);
	}			 
				
	setLayout(null);
	setSize(width,height);
	setLocation(width*3,0);
	setVisible(false);
    }
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.drawImage(image,0,0,width,height,null);
    }
}
