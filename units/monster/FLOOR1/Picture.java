import java.awt.*;
import java.awt.event.*;
import java.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Picture extends JPanel implements ActionListener{

    static ArrayList<Monster> monsterList = new ArrayList<Monster>();

    public Picture(){
	Timer t = new Timer(100,this);
	t.start();
    }

    public void paint(Graphics g){
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(Color.WHITE);
	g2d.fillRect(0,0,getWidth(),getHeight());
	for (Monster mon: monsterList)
	    g2d.drawImage(mon.getSprite(),mon.getX(),mon.getY(),this);
	Toolkit.getDefaultToolkit().sync();
	g.dispose();
    }

    public void actionPerformed(ActionEvent q){
	for (Monster mon: monsterList){
	    mon.setX(mon.getX() + (int)(Math.random() * 2) - 1);
	    mon.setY(mon.getY() + (int)(Math.random() * 2) - 1);
	}
	repaint();
    }

}
