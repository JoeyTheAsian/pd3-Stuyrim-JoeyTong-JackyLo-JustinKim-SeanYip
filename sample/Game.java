import java.awt.Container;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Game extends JFrame {
    Container pane;
    //screen dimensions
    int height = Toolkit.getDefaultToolkit().getScreenSize().height-37;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
   

    public Game() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setTitle("The Senior Scrolls I: Stuyrim");
	setLayout(null);
	MenuPanel MenuPanel = new MenuPanel();
	add(MenuPanel);
	setResizable(false);
	setSize(width, height);
	setVisible(true);
    }
}

