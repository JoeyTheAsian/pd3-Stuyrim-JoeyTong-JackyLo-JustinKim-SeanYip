import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Paint extends Driver{
    public void paint(Graphics g){
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(player,300,300,this);
	Toolkit.getDefaultToolkit().sync();
	repaint();
	//	g.dispose();
    }
}
