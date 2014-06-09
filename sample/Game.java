import java.awt.Container;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Game extends JFrame {
    Container pane;
    //screen dimensions
    int height = Toolkit.getDefaultToolkit().getScreenSize().height-37;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;

    MenuPanel menuPanel = new MenuPanel();
    GamePanel gamePanel = new GamePanel();

    public Game() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setTitle("The Senior Scrolls I: Stuyrim");
	setLayout(null);

	add(menuPanel);
	add(gamePanel);
	setResizable(false);
	setSize(width, height);
	setVisible(true);
    }
    public void startGame(){
	gamePanel.setVisible(true);
	gamePanel.screen.start();
	remove(menuPanel);
	gamePanel.requestFocusInWindow();
    }
}

