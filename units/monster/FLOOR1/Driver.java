import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Driver extends JFrame{
    Container buttons = new Container();
    
    public Driver(){
	this.setLayout(new GridLayout(2,1));
	this.setTitle("Demo");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(1000,600);
	this.setLocationRelativeTo(null);
	this.add(new Picture());
	addButtons();
	this.setVisible(true);
    }

    public void addButtons(){
	buttons.setLayout(new GridLayout(2,4));
	bird = new JButton("Bird");
	goblin = new JButton("Goblin");
	plant = new JButton("Plant");
	slime = new JButton("Slime");
	
	buttons.add(bird);
	buttons.add(goblin);
	buttons.add(plant);
	buttons.add(slime);

	bird.addActionListener(new AbstractAction("Bird"){
		public void actionPerformed(ActionEvent e){
		    Picture.add(new Bird());
		}
	    });
	goblin.addActionListener(new AbstractAction("Goblin"){
		public void actionPerformed(ActionEvent e){
		    Picture.add(new Goblin());
		}
	    });
	plant.addActionListener(new AbstractAction("Plant"){
		public void actionPerformed(ActionEvent e){
		    Picture.add(new Plant());
		}
	    });
	slime.addActionListener(new AbstractAction("Slime"){
		public void actionPerformed(ActionEvent e){
		    Picture.add(new Slime());
		}
	    });
	
	this.add(buttons);
    }

    public static void main(String[] args){
	new Driver();
    }

}
