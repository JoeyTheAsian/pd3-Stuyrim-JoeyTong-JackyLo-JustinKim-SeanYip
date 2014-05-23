import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GUI{
    private JPanel JPanel;
    private JFrame frame;
    private JButton button;
    private int Height;
    private int Width;
    private boolean useKey; 
    private JTextArea textBox;
    //    public painting panel;
    public GUI(){
	
	frame = new JFrame("Stuyrim");
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double w = screenSize.getWidth();
	double h = screenSize.getHeight();
	if ((h/w)< .7){
	    h -= 32;
	    w = h*1.429;
	}else if (h/w > .7){
	    h = (w*.7)-32;
	}
	Height = (int)h;
	Width = (int)w;
	
	frame.setSize((int)w,(int)h);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel = new JPanel();
	//	panel = new painting();

	JPanel.setLayout(null);
	//Panel.setLayout(null);
	//Panel.setBounds(0,0,Width-10,(int)(Height*.754));
	
	textBox = new JTextArea();
        button = new JButton();


    //edit this to edit textbox in GUI etc.
	textBox.setBounds(0,(int)(Height*3/4),(int)(Width*.95),(int)(Height));
	textBox.setEditable(false);
	textBox.setRows(8);
	textBox.setLineWrap(true);
	textBox.setWrapStyleWord(true);

	button.setBounds((int)(Width*.95),(int)(Height*3/4),(int)(Width),(int)(Height));
    }
    public static void main(String[]args){
	new GUI();
    }
}
