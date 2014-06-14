import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;

public class PartyPanel extends JPanel{
    private int height = (Toolkit.getDefaultToolkit().getScreenSize().height-45)/5*3;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width/4;
    private BufferedImage image;
    private JTextArea partyInfo;
    public PartyPanel(){
	try{image = ImageIO.read(new File("GUI Images/trimmed wood background.png"));
	}catch(Exception e){
	    Utilities.showErrorMessage(this,e);
	}			 
	
	setLayout(null);
	setSize(width,height);
	setLocation(width*3,height/5/2);
	addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			public void focusLost(FocusEvent e) {requestFocusInWindow();}	
	});
	setSize(width,height/2);
	partyInfo.setOpaque(false);
	partyInfo.setVisible(true);
	partyInfo.setEditable(false);
	partyInfo.setHighlighter(null);
        partyInfo.setDragEnabled(False);
	partyInfo.setForeground(Color.BLACK);
	partyInfo.setFont(new Font("TimesRoman", Font.PLAIN, height/2/30));
	setVisible(false);
    }
    public void updatePartyInfo(ArrayList <Player> players){
	for(int i = 0; i < players.Size(); i++){
	    players.get(i);
    }
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.drawImage(image,0,0,width,height,null);
    }
}
