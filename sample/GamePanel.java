import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Font;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class GamePanel extends JPanel{
    /*
    BUTTON FRAMEWORK
    Button.setOpaque(false);
    Button.setBorderPainted(false);
    Button.setContentAreaFilled(false);
    Button.setSize(width/10,height/20);
    Button.setVerticalTextPosition(SwingConstants.CENTER);
    Button.setHorizontalTextPosition(SwingConstants.CENTER);
    Button.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    Button.setLocation((width/3)-(width/20), (height/7*6)-(height/40));
    Image i1 = new ImageIcon("").getImage().getScaledInstance
	(Button.getWidth(),Button.getHeight(),java.awt.Image.SCALE_SMOOTH);
    Button.setIcon(new ImageIcon(img));
    Button.setForeground(Color.white);
    Button.addActionListener(e -> {
	});
    */
    private int windowHeight =Toolkit.getDefaultToolkit().getScreenSize().height-37;
    private int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int height = windowHeight/6;
    private int width = windowWidth;
    private Image bg;

    public GamePanel(){
	setBounds(0,windowHeight/5*4,windowWidth,windowHeight);
	try {bg = ImageIO.read(new File("GUI images/wood background.png"));}
	catch (Exception e) {Utilities.showErrorMessage(this, e);}
	setVisible(true);
	repaint();		     
    }

    public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.drawImage(bg,0,0,width,height,null);
	g.dispose();
    }
}
