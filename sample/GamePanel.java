import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel {
    private boolean[] keysPressed = new boolean[256];
    private boolean[] keysReleased = new boolean[256];
    private BufferedImage bg; //background
    //Maps dropped items (after death of monsters) to its location in game.
    private int windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height-37;
    private int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    //dimensions of the bottom portion of the screen with all the buttons
    private int height = windowHeight/5;
    private int width = windowWidth;
    //player inventory will probably be removed in the future after testing
    private Inventory inventory = new Inventory();
    private InventoryPanel invent = new InventoryPanel(inventory);
    private PartyPanel party = new PartyPanel();
    private JTextArea environmentInfo = new JTextArea();
    Screen screen = new Screen();
    private JTextArea playerData;
    private JButton inventoryButton, menuButton, partyButton;

    public GamePanel() {
	setLayout(null);
	setBounds(0, 0 , windowWidth, windowHeight);
	try {bg = ImageIO.read(new File("GUI Images/trimmed paper background.png"));}
	catch (Exception e) {Utilities.showErrorMessage(this, e);}
	setVisible(true);

	//add buttons
	inventoryButton = new JButton("Inventory");
	inventoryButton.setOpaque(false);
	inventoryButton.setBorderPainted(false);
	inventoryButton.setContentAreaFilled(false);
	inventoryButton.setVerticalTextPosition(SwingConstants.CENTER);
	inventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
	inventoryButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	inventoryButton.setBounds(windowWidth/6*5-(width/70),windowHeight-height+(height/30),width/6,(height-(height/30))/3);
	//get button textures
	Image i1 = new ImageIcon("GUI Images/Button.png").getImage().getScaledInstance(inventoryButton.getWidth(),inventoryButton.getHeight(),java.awt.Image.SCALE_SMOOTH);
	Image i2 = new ImageIcon("GUI Images/Button1.png").getImage().getScaledInstance(inventoryButton.getWidth(),inventoryButton.getHeight(),java.awt.Image.SCALE_SMOOTH);
	inventoryButton.setIcon(new ImageIcon(i1));
	inventoryButton.setForeground(Color.white);
	inventoryButton.addActionListener(e -> {
		for(int i = 0; i < 256; i++){
		    if(keysPressed[i]){
			keysReleased[i] = true;
			keysPressed[i] = false;
		    }
		}
		//if it's there, take it out and give focus to screen, if it isn't put it in ,update items and take focus
		if (invent.isVisible()) {
		    invent.setVisible(false);
		    screen.requestFocusInWindow();
		    inventoryButton.setIcon(new ImageIcon(i1));
		}else if(!invent.isVisible()){
		    //nullifies all input before opening 
		    invent.setVisible(true);
		    invent.requestFocusInWindow();
		    //invent.updateInventory(inventory);
		    inventoryButton.setIcon(new ImageIcon(i2));
			invent.setInventory(inventory);
		}
	    });
	partyButton = new JButton("Party");
	partyButton.setOpaque(false);
	partyButton.setBorderPainted(false);
	partyButton.setContentAreaFilled(false);
	partyButton.setVerticalTextPosition(SwingConstants.CENTER);
	partyButton.setHorizontalTextPosition(SwingConstants.CENTER);
	partyButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	partyButton.setBounds(windowWidth/6*5-(width/70),(windowHeight-height)+(height/4)+(height/30),width/6,(height-(height/30))/3);
	partyButton.setIcon(new ImageIcon(i1));
	partyButton.setForeground(Color.white);
	partyButton.addActionListener(e -> {
		if(party.isVisible()){
		    party.setVisible(false);
		    screen.requestFocusInWindow();
		    partyButton.setIcon(new ImageIcon(i1));
		}else if(!party.isVisible()){
		    for(int i = 0; i < 256; i++){
			if(keysPressed[i]){
			    keysReleased[i] = true;
			    keysPressed[i] = false;
			}
		    }
		    party.setVisible(true);
		    party.requestFocusInWindow();
		    partyButton.setIcon(new ImageIcon(i2));
		    party.updatePartyData(screen.characters);		       	
		}
	    });
	menuButton = new JButton("Menu");
	menuButton.setOpaque(false);
	menuButton.setBorderPainted(false);
	menuButton.setContentAreaFilled(false);
	menuButton.setVerticalTextPosition(SwingConstants.CENTER);
	menuButton.setHorizontalTextPosition(SwingConstants.CENTER);
	menuButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	menuButton.setBounds(windowWidth/6*5-(width/70),(windowHeight-height)+(height/4*2)+(height/30),width/6,(height-(height/30))/3);
	menuButton.setIcon(new ImageIcon(i1));
	menuButton.setForeground(Color.white);
	menuButton.addActionListener(e -> {
		screen.requestFocusInWindow();
	    });

	//GRABS ALL PARTY DATA INCLUDING HP, MANA, ETC
	playerData = new JTextArea();
	playerData.setSize(width/6,height);
	playerData.setLocation(0+(width/50),(windowHeight/5*4)+(height/20));
	playerData.setOpaque(false);
	playerData.setVisible(true);
	playerData.setEditable(false);
	playerData.setHighlighter(null);
	playerData.setDragEnabled(false);
	playerData.setForeground(Color.BLACK);
	playerData.setFont(new Font("TimesRoman", Font.PLAIN, height/15));

	environmentInfo = new JTextArea();
	environmentInfo.setSelectedTextColor(Color.WHITE);


	//creates inventory panel
	invent.setSize(windowWidth/2, windowHeight/2);
	invent.setLocation(windowWidth/4, windowHeight/4);
	screen = new Screen();
    	add(party);
	add(invent);
	add(playerData);
	add(menuButton);
	add(inventoryButton);
	add(partyButton);
	add(screen);
	revalidate();
    }

    public static class ItemFactory {
	public static Item get(String name) {
	    try {
		switch (name) {
		case "Cake": return new Item(ImageIO.read(new File("items/Cake.png")), "Cake", "It's a lie.", 9001, 9001, 9001);
		}
	    }
	    catch (Exception e) {Utilities.showErrorMessage(null, e);}
	    return null;
	}
    }
	
	public Inventory getInventory() {return inventory;}
	
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(bg,0,windowHeight/5*4,width,height,null);
	String text = "";
	for(int i = 0; i < screen.characters.size(); i++){
	    if (i!= 0){text += "\n";}	
	    text += "Player " + (i +1) + " " + "(" + "LVL: " + screen.characters.get(i).getLVL() + "): " + "\nHP: " +  screen.characters.get(i).getHP()+"/"+screen.characters.get(i).getMaxHP()
		+ "\nMana: " + screen.characters.get(i).getMana()+"/"+screen.characters.get(i).getMaxMana();
	}
	playerData.setText(text);
    }

    public class Screen extends Canvas implements Runnable{
	private BufferedImage flickerStop;
	//FPS counter variables
	private static final int MAX_FPS = 60;
	private static final int MAX_FPS_1 = 60;
	private static final int FPS_SAMPLE_SIZE = 6;
	// The width and height of each tile in pixels
	private static final int TILE_SCALE = 60;
	//arraylists containing all entities on screen, painted by while loop in screen
	private ArrayDeque<AttackEvent> attacks = new ArrayDeque<>(); //Used as a stack
	private ArrayDeque<KeyEvent> itemPickupRequests = new ArrayDeque<>();
	private ArrayList<Player> characters = new ArrayList<>();
	private ArrayList<Character> ai = new ArrayList<>();
	private ArrayList<Item> droppedItems = new ArrayList<>();
	private ArrayList<MapObject> mapObjects = new ArrayList<>();
	private ArrayList<Drawable> screenEntities = new ArrayList<>();
	private boolean running;
	private int averageFPS;
	private int averageFPS1;
	private int mapX = 0;
	private int mapY = 0;
	//SCREEN dimensions
	private int screenHeight = ((Toolkit.getDefaultToolkit().getScreenSize().height-37)/5*4);
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private LinkedList<Long> frames = new LinkedList<>();
	private LinkedList<Long> frames1 = new LinkedList<>();
	private long time; //global time
	private long prevTick = -1;
	private long prevTick1 = -1;
	private Map currentMap;

	private boolean shielded;
	//for testing putposes
	private Mage player2 = new Mage("sprites/mage down.png", screenWidth/2, screenHeight/2);
	private Swordsman player = new Swordsman("sprites/swordsman down.png", screenWidth/2, screenHeight/2);
	private Swordsman player3 = new Swordsman("sprites/swordsman down.png", screenWidth/2, screenHeight/2);
	private Thread thread;

	private int[][] spawnCoords = {{0,0},{1200,0},{0,1200},{1200,1200}};

	public Screen() {
	    setSize(screenWidth, screenHeight);
	    try{flickerStop =ImageIO.read(new File("GUI Images/flickerStop.png"));
	    }catch(Exception e){Utilities.showErrorMessage(this,e);}
	    characters.add(player);
	    characters.add(player2);
	    characters.add(player3);

	    for (int i = 0; i < 10; i++){
		int a = (int)(Math.random()*25)*60;
		int b = (int)(Math.random()*20)*60;
		if (a > 400 || a < -400 || b > 400 || b < -400)
		    i--;
		else{
		    Bush bush = new Bush(a,b);
		    mapObjects.add(bush);
		}
	    }
	    for (int i = 0; i < 10; i++){
		int a = (int)(Math.random()*25)*60;
		int b = (int)(Math.random()*20)*60;
		if (a > 400 || a < -400 || b > 1600 || b < 800)
		    i--;
		else{
		    Rock rock = new Rock(a,b);
		    mapObjects.add(rock);
		}
	    }
	    for (int i = 0; i < 10; i++){
		int a = (int)(Math.random()*25)*60;
		int b = (int)(Math.random()*20)*60;
		if (a > 1600 || a < 800 || b > 1600 || b < 800)
		    i--;
		else{
		    Campfire campfire = new Campfire(a,b);
		    mapObjects.add(campfire);
		}
	    }
	    for (int i = 0; i < 10; i++){
		int a = (int)(Math.random()*25)*60;
		int b = (int)(Math.random()*20)*60;
		if (a > 1600 || a < 800 || b > 400 || b < -400)
		    i--;
		else{
		    Tree tree = new Tree(a,b);
		    mapObjects.add(tree);
		}
	    }

            currentMap = new Map();
	    addKeyListener(new KeyListener() {
		    public void keyPressed(KeyEvent e) {keysPressed[e.getKeyCode()] = true;}
		    public void keyReleased(KeyEvent e) {
			keysPressed[e.getKeyCode()] = false;
			keysReleased[e.getKeyCode()] = true;
		    }
		    public void keyTyped(KeyEvent e) {if (e.getKeyChar() == 'e') {itemPickupRequests.push(e);}}
		});
	    addMouseListener(new MouseListener() {
		    public void mouseClicked(MouseEvent e) {}
		    public void mouseEntered(MouseEvent e) {}
		    public void mouseExited(MouseEvent e) {}
		    public void mousePressed(MouseEvent e) {
			Character player = characters.get(0);
			if (time-player.getTimeStarted() >= player.getATKspeed()){
			    player.setTimeStarted(time);
			    e.translatePoint(-mapX, -mapY);
			    attacks.push(new AttackEvent(player.getX() + player.getWidth()/2 - mapX, player.getY() + player.getWidth() / 2 - mapY, e.getX(), e.getY(), characters.get(0).getRange()));
			}
		    }
		    public void mouseReleased(MouseEvent e) {}
		});
	    addMouseMotionListener(new MouseMotionListener() {
		    public void mouseDragged(MouseEvent e) {}
		    public void mouseMoved(MouseEvent e) {}
		});
	    addMouseWheelListener(new MouseWheelListener() {
		    public void mouseWheelMoved(MouseWheelEvent e) {}
		});
	    setVisible(true);
	}

	private class AttackEvent {
	    private double startX, startY, endX, endY, range;

	    public AttackEvent(double startX, double startY, double endX, double endY, double range) {
		this.startX = startX;
		this.startY = startY;
		double distance = Math.hypot(endX - startX, endY - startY), scaleFactor = range/distance;
		this.endX = endX;
		this.endY = endY;
		if (distance > range) {
		    this.endX = startX + scaleFactor*(endX - startX);
		    this.endY = startY + scaleFactor*(endY - startY);
		}
	    }

	    public double getEndX() {return endX;}
	    public double getEndY() {return endY;}
	    public double getrange() {return range;}
	    public double getStartX() {return startX;}
	    public double getStartY() {return startY;}
	}

	protected boolean intersectEllipseLineSegment(double x1, double y1, double x2, double y2, double h, double k, double a, double b) {
	    //For the purposes of this game, a line segment that is in an ellipse but does not intersect it (completely contained in ellipse) counts as an intersection. x1, y1, x2, and y2 are points that define the **directed** ((x1, y1) to (x2, y2)) line segment to test. h and k are the x- and y-coordinates of the center of the ellipse, respectively. a and b are the same variables as they are in the equation of an ellipse
	    if ((((Math.pow(x1 - h, 2)/(a*a)) + (Math.pow(y1 - k, 2)/(b*b))) <= 1) && (((Math.pow(x2 - h, 2)/(a*a)) + (Math.pow(y2 - k, 2)/(b*b))) <= 1)) {return true;}
	    double m = (y2 - y1)/(x2 - x1), c = y1 - m*x1, d = c + m*h, e = c - k; //m is the slope of the **directed** line segment defined from (x1, y1) to (x2, y2). c is the y-intercept of that line segment, if it were extended to intersect the y-axis. d and e are additional variables to make the calculation shorter. Note that all this will not work if x1 = x2 (vertical line because of divison by zero) (will include a separate case for that).
	    double discriminant = a*a*m*m + b*b - d*d - k*k + 2*d*k, iX1 = 0, iY1 = 0, iX2 = 0, iY2 = 0; //Discriminant, like in the quadratic formula, is used to find the number of intersection points. iX1, iY1, iX2, iY2 represent the intersection points.
	    if (discriminant < 0) {return false;}
	    if (discriminant >= 0) {
		iX1 = (h*b*b - m*a*a*e + a*b*Math.sqrt(discriminant))/(a*a*m*m + b*b);
		iY1 = (b*b*d + k*a*a*m*m + a*b*m*Math.sqrt(discriminant))/(a*a*m*m + b*b);
	    }
	    if (discriminant > 0) { //Note that this case and the one above are not mutually exclusive
		iX2 = (h*b*b - m*a*a*e - a*b*Math.sqrt(discriminant))/(a*a*m*m + b*b);
		iY2 = (b*b*d + k*a*a*m*m - a*b*m*Math.sqrt(discriminant))/(a*a*m*m + b*b);
	    }
	    double lineSegmentDirection = Math.atan2(y2 - y1, x2 - x1);
	    double lineSegmentLength = Math.hypot(x2 - x1, y2 - y1);
	    return (((Math.hypot(iX1 - x1, iY1 - y1) <= lineSegmentLength) && (Math.atan2(iY1 - y1, iX1 - x1) == lineSegmentDirection)) || ((Math.hypot(iX2 - x1, iY2 - y1) <= lineSegmentLength) && (Math.atan2(iY2 - y1, iX2 - x1) == lineSegmentDirection)));
	}

	//renders the screen
	public void render(){
	    BufferStrategy bs = getBufferStrategy();
	    if(bs  == null){
		createBufferStrategy(3);
		return;
	    }
	    Graphics g= bs.getDrawGraphics();
	    //draws black screen to prevent layered images and flicker
	    g.drawImage(flickerStop,0,0,screenWidth,screenHeight, null);
	    // Draw the current map
	    drawMap(g);
	    //draw fps
	    g.setColor(Color.GREEN);

	    screenEntities.addAll(characters);
	    screenEntities.addAll(ai);
	    screenEntities.addAll(droppedItems);
	    screenEntities.addAll(mapObjects);

	    screenEntities.sort((Drawable e1, Drawable e2) -> (new Integer(e1.getY())).compareTo(e2.getY()));
	    //draws everything
	    for(Drawable entity : screenEntities){
		if(entity instanceof Character){
		    try{
			g.setColor(Color.RED);
			g.fillRect(entity.getX(),entity.getY()-10,80,7);
			g.setColor(Color.GREEN);
			if(((Character)entity).getHP()>0){
			    g.fillRect(entity.getX(),entity.getY()-10,
				       (int)(80.0*((double)((Character)entity).getHP()/(double)((Character)entity).getMaxHP())),7);
			}
		    }catch(Exception e){
			e.printStackTrace();
		    }
		}
		if(entity.getX() < windowWidth+(windowWidth/4) && entity.getY()<windowHeight+(windowHeight/4)){
		    if (entity instanceof Item) {g.drawImage(entity.getImage(), entity.getX() + mapX, entity.getY() + mapY, null);}
		    else {g.drawImage(entity.getImage(),entity.getX(),entity.getY(),null);}
		}
	    }
	    screenEntities.clear();
	    characters.get(0).setY(screenHeight/2);
	    characters.get(0).setX(screenWidth/2);
		
		if (getClosestItemDistance() <= 50) {g.drawString("Press E to pick up " + getClosestItem().getName(), 5, getHeight() - 10);}
	    g.drawString("TPS: " + averageFPS, 0, 20);
	    g.drawString("FPS: " + averageFPS1,0, 31);
	    g.drawString("Global Time: " + time, 0, 50);

	    g.dispose();
	    bs.show();

	    //actual FPS limiter, greatly improves performance by not letting system max out processor with useless still frames
	    long pastTime = System.currentTimeMillis() - prevTick;

	    if (frames1.size() == FPS_SAMPLE_SIZE) {
		frames1.remove();
	    }
	    frames1.add(pastTime);
	    // Calculate average FPS
	    long sum = 0;
	    for (long frame : frames1){
		sum += frame;
	    }
	    long averageFrame = sum / FPS_SAMPLE_SIZE;
	    if (averageFrame == 0) {averageFrame = 1;}
	    if (averageFrame == 0) averageFrame = 1;  //IF STATEMENT
	    averageFPS1 = (int)(1000 / averageFrame); //NOTE: THERE'S AN ARITHMETIC ERROR. AND I THINK THIS LINE IS CRASHING 
	    prevTick1 = System.currentTimeMillis();
	    // Only if the time passed since the previous tick is less than one
	    // second divided by the number of maximum FPS allowed do we delay
	    // ourselves to give Time time to catch up to our rendering.
	    if (pastTime < 1000.0 / MAX_FPS_1) {
		try {
		    Thread.sleep((long)(1000.0 / MAX_FPS_1)-pastTime);
		} catch (InterruptedException e) {
		    Utilities.showErrorMessage(this, e);
		}
	    }
	}

        // Renders the tilemap of the current map to the screen
        private void drawMap(Graphics g) {
            Tile[][] tilemap = currentMap.getTileMap();
            for (int i = 0; i < tilemap.length; i++) {
                for (int j = 0; j < tilemap[i].length; j++) {
                    drawTile(i, j, tilemap[i][j], g);
                }
            }
            // drawAllMapObjects(g);
        }

        private void drawTile(int x, int y, Tile tile, Graphics g) {
	    if((x * TILE_SCALE + mapX + TILE_SCALE >0)&&(y*TILE_SCALE + mapY +TILE_SCALE>0)
	       &&(x * TILE_SCALE + mapX<windowWidth+1) && ( y * TILE_SCALE + mapY < windowHeight+1)){
		Image texture = currentMap.getTexture(tile);
		g.drawImage(texture, x * TILE_SCALE + mapX, y * TILE_SCALE + mapY,
			    TILE_SCALE, TILE_SCALE, null);
	    }
        }
    
	public Item getClosestItem() {
		if (droppedItems.isEmpty()) {return null;}
		double[] distancesToDroppedItems = new double[droppedItems.size()];
		for (int i = 0; i < droppedItems.size(); i++) {distancesToDroppedItems[i] = Math.hypot(droppedItems.get(i).getX() - (player.getX() + player.getWidth()/2 - mapX), droppedItems.get(i).getY() - (player.getY() + player.getHeight()/2 - mapY));}
		int indexOfSmallestDistanceToDroppedItem = 0;
		for (int i = 1; i < distancesToDroppedItems.length - 1; i++) {if (distancesToDroppedItems[i + 1] < distancesToDroppedItems[i]) {indexOfSmallestDistanceToDroppedItem = i + 1;}}
		double smallestDistanceToDroppedItem = distancesToDroppedItems[indexOfSmallestDistanceToDroppedItem];
		return droppedItems.get(indexOfSmallestDistanceToDroppedItem);
	}
	
	public double getClosestItemDistance() {
		Item closestItem = getClosestItem();
		if (closestItem == null) {return Double.MAX_VALUE;}
		return Math.hypot(closestItem.getX() - (player.getX() + player.getWidth()/2 - mapX), closestItem.getY() - (player.getY() + player.getHeight()/2 - mapY));
	}
	
	public void run() {
	    while (running) {
		time++;
		if (time%100==0){
		    for (Character c : characters)
			c.slowStatRestore(); //return to normal stats
		    for (Character c : ai)
			c.slowStatRestore();
		}
		tick();
		render();
	    }
	}

	public synchronized void start() {
	    time = 0;
	    running = true;
	    thread = new Thread(this);
	    thread.start();
	}

	public synchronized void stop() {
	    try {thread.join();}
	    catch (InterruptedException e) {Utilities.showErrorMessage(this, e);}
	}

	//chance of spawning each monster is 0.1% and it will spawn in one of the four sides of the screen
	public void chanceOfSpawn(){
	    int[][]side = {{0,(int)(Math.random()*screenHeight)},
			   {screenWidth,(int)(Math.random()*screenHeight)},
			   {(int)(Math.random()*screenWidth),0},
			   {(int)(Math.random()*screenWidth),screenHeight}};
	    int temp = (int)(Math.random()*4);
	    Player plyr;
	    if (Math.abs(characters.get(0).getX()-spawnCoords[0][0]) < 400 && Math.abs(characters.get(0).getY()-spawnCoords[0][1]) < 400){
		if (Math.random() < 0.01){
		    plyr = new Bug("sprites/bug down.png",side[temp][0],side[temp][1]);
		    ai.add(plyr);
		    plyr.setTimeStarted(time);
		}
	    }else if (Math.abs(characters.get(0).getX()-spawnCoords[1][0]) < 400 && Math.abs(characters.get(0).getY()-spawnCoords[1][1]) < 400){
		if (Math.random() < 0.01){
		    plyr = new Bird("sprites/bird down.png",side[temp][0],side[temp][1]);
		    ai.add(plyr);
		    plyr.setTimeStarted(time);
		}
	    }else if (Math.abs(characters.get(0).getX()-spawnCoords[2][0]) < 400 && Math.abs(characters.get(0).getY()-spawnCoords[2][1]) < 400){
		if (Math.random() < 0.01){
		    plyr = new Slime("sprites/slime down.png",side[temp][0],side[temp][1]);
		    ai.add(plyr);
		    plyr.setTimeStarted(time);
		}
	    }else if (Math.abs(characters.get(0).getX()-spawnCoords[3][0]) < 400 && Math.abs(characters.get(0).getY()-spawnCoords[3][1]) < 400){
		if (Math.random() < 0.01){
		    plyr = new Goblin("sprites/goblin down.png",side[temp][0],side[temp][1]);
		    ai.add(plyr);
		    plyr.setTimeStarted(time);
		}
	    }
	}

	//updates game screen data
	public void tick() {
	    if(keysPressed[VK_SHIFT] ){
		shielded = true;
		characters.get(0).setDEF(characters.get(0).getDEF() + 100);
	    }
	    if (keysPressed[VK_W] && ableToMove("up", characters.get(0),characters.get(0).getSpeed())) {
		if (!shielded){
		    mapY+=characters.get(0).getSpeed();
		    for (int i = 0; i < spawnCoords.length; i++)
			spawnCoords[i][1]+=characters.get(0).getSpeed();
		    characters.get(0).setUpAnimated();
		    for (Character monster : ai)
			monster.setY(monster.getY()+characters.get(0).getSpeed());
		    mapObjects.forEach(obj -> obj.setY(obj.getY() + characters.get(0).getSpeed()));
		    for (int i = 1; i< characters.size(); i++)
			characters.get(i).setY(characters.get(i).getY()+characters.get(0).getSpeed());
		}else{
		    mapY+=characters.get(0).getSpeed()/2;
		    for (int i = 0; i < spawnCoords.length; i++)
			spawnCoords[i][1]+=characters.get(0).getSpeed()/2;
		    characters.get(0).setUpShieldAnimated();
		    for (Character monster : ai)
			monster.setY(monster.getY()+characters.get(0).getSpeed()/2);
		    mapObjects.forEach(obj -> obj.setY(obj.getY() + characters.get(0).getSpeed()/2));
		    for (int i = 1; i< characters.size(); i++)
			characters.get(i).setY(characters.get(i).getY()+characters.get(0).getSpeed()/2);
		}
	    }
	    if (keysPressed[VK_S] && ableToMove("down", characters.get(0),characters.get(0).getSpeed())) {
		if (!shielded){
		    mapY-=characters.get(0).getSpeed();
		    for (int i = 0; i < spawnCoords.length; i++)
			spawnCoords[i][1]-=characters.get(0).getSpeed();
		    characters.get(0).setDownAnimated();
		    for (Character monster : ai)
			monster.setY(monster.getY()-characters.get(0).getSpeed());
		    mapObjects.forEach(obj -> obj.setY(obj.getY() - characters.get(0).getSpeed()));
		    for (int i = 1; i< characters.size(); i++)
			characters.get(i).setY(characters.get(i).getY()-characters.get(0).getSpeed());
		}else{
		    mapY-=characters.get(0).getSpeed()/2;
		    for (int i = 0; i < spawnCoords.length; i++)
			spawnCoords[i][1]-=characters.get(0).getSpeed()/2;
		    characters.get(0).setDownShieldAnimated();
		    for (Character monster : ai)
			monster.setY(monster.getY()-characters.get(0).getSpeed()/2);
		    mapObjects.forEach(obj -> obj.setY(obj.getY() - characters.get(0).getSpeed()/2));
		    for (int i = 1; i< characters.size(); i++)
			characters.get(i).setY(characters.get(i).getY()-characters.get(0).getSpeed()/2);
		}
	    }
	    if (keysPressed[VK_A] && ableToMove("left", characters.get(0),characters.get(0).getSpeed())) {
		if (!shielded){
		    mapX+=characters.get(0).getSpeed();
		    for (int i = 0; i < spawnCoords.length; i++)
			spawnCoords[i][0]+=characters.get(0).getSpeed();
		    characters.get(0).setLeftAnimated();
		    for (Character monster : ai)
			monster.setX(monster.getX()+characters.get(0).getSpeed());
		    mapObjects.forEach(obj -> obj.setX(obj.getX() + characters.get(0).getSpeed()));
		    for (int i = 1; i< characters.size(); i++)
			characters.get(i).setX(characters.get(i).getX()+characters.get(0).getSpeed());
		}else{
		    mapX+=characters.get(0).getSpeed()/2;
		    for (int i = 0; i < spawnCoords.length; i++)
			spawnCoords[i][0]+=characters.get(0).getSpeed()/2;
		    characters.get(0).setLeftShieldAnimated();
		    for (Character monster : ai)
			monster.setX(monster.getX()+characters.get(0).getSpeed()/2);
		    mapObjects.forEach(obj -> obj.setX(obj.getX() + characters.get(0).getSpeed()/2));
		    for (int i = 1; i< characters.size(); i++)
			characters.get(i).setX(characters.get(i).getX()+characters.get(0).getSpeed()/2);
		}
	    }
	    if (keysPressed[VK_D] && ableToMove("right", characters.get(0),characters.get(0).getSpeed())) {
		if (!shielded){
		    mapX-=characters.get(0).getSpeed();
		    for (int i = 0; i < spawnCoords.length; i++)
			spawnCoords[i][0]-=characters.get(0).getSpeed();
		    characters.get(0).setRightAnimated();
		    for (Character monster : ai)
			monster.setX(monster.getX()-characters.get(0).getSpeed());
		    mapObjects.forEach(obj -> obj.setX(obj.getX() - characters.get(0).getSpeed()));
		    for (int i = 1; i< characters.size(); i++)
			characters.get(i).setX(characters.get(i).getX()-characters.get(0).getSpeed());
		}else{
		    mapX-=characters.get(0).getSpeed()/2;
		    for (int i = 0; i < spawnCoords.length; i++)
			spawnCoords[i][0]-=characters.get(0).getSpeed()/2;
		    characters.get(0).setRightShieldAnimated();
		    for (Character monster : ai)
			monster.setX(monster.getX()-characters.get(0).getSpeed()/2);
		    mapObjects.forEach(obj -> obj.setX(obj.getX() - characters.get(0).getSpeed()/2));
		    for (int i = 1; i< characters.size(); i++)
			characters.get(i).setX(characters.get(i).getX()+-characters.get(0).getSpeed()/2);
		}
	    }

	    //special attack
	    if (keysPressed[VK_SPACE] && characters.get(0).getMana() >= 50){
		for (Character c : ai)
		    if (c.getDist(characters.get(0)) < 200)
			characters.get(0).sAttack(c);
		keysPressed[VK_SPACE] = false;
		characters.get(0).setMana(characters.get(0).getMana()-50);
	    }
	    //reset player to idle mode after done moving
	    if(keysReleased[VK_SHIFT]){
		shielded = false;
		characters.get(0).setDEF(characters.get(0).getMaxDEF());
	    }
	    if(keysReleased[VK_W]){
		if(!shielded){
		    characters.get(0).setUp();
		}else{
		    characters.get(0).setUpShield();
		}
		keysReleased[VK_W] = false;
	    }
	    if(keysReleased[VK_S]){
		if(!shielded){
		    characters.get(0).setDown();
		}else{
		    characters.get(0).setDownShield();
		}
		keysReleased[VK_S] = false;
	    }
	    if(keysReleased[VK_A] ){
		if(!shielded){
		    characters.get(0).setLeft();
		}else{
		    characters.get(0).setLeftShield();
		}
		keysReleased[VK_A] = false;
	    }
	    if(keysReleased[VK_D]){
		if(!shielded){	
		    characters.get(0).setRight();
		}else{
		    characters.get(0).setRightShield();
		}
		keysReleased[VK_D] = false;
	    }
	    while (!(attacks.isEmpty())) {
		AttackEvent attack = attacks.pop();
		//System.out.println("\nStart: (" + attack.getStartX() + ", " + attack.getStartY() + "); End: (" + attack.getEndX() + ", " + attack.getEndY() + ")");
		for (Character character : ai) {
		    if (intersectEllipseLineSegment(attack.getStartX(), attack.getStartY(), attack.getEndX(), attack.getEndY(), (character.getX() + character.getWidth()/2 - mapX), (character.getY() + character.getHeight()/2 - mapY), character.getWidth(), character.getHeight())) {characters.get(0).attack(character);}
		}
	    }
		while (!(itemPickupRequests.isEmpty())) {
			if (droppedItems.isEmpty()) {
				itemPickupRequests.clear();
				break;
			}
			itemPickupRequests.pop(); //Really nothing to do with the KeyEvent. Keep it here just in case.
			Item closestItem = getClosestItem();
			if (getClosestItemDistance() <= 50) {
				getInventory().add(closestItem); 
				droppedItems.remove(closestItem);
			}
		}
	    chanceOfSpawn(); //chance of spawn

	    //AI code
	    for (Character character : ai){
		if (character.getTarget() == null)
		    character.setTarget(characters.get((int)(Math.random()*characters.size()))); //random character on screen
		else if (character.getTarget().getHP() <= 0)
		    character.setTarget(null);
		if (character.getDist(character.getTarget()) < character.getRange()){
		    if (Math.abs(character.getChangeX()) > Math.abs(character.getChangeY())){
			if (character.getChangeX() > character.getChangeY())
			    character.setRight();
			else if (character.getChangeX() < character.getChangeY())
			    character.setLeft();
		    }else{
			if (character.getChangeY() > character.getChangeX())
			    character.setDown();
			else if (character.getChangeY() < character.getChangeX())
			    character.setUp();
		    }
		    if ((character.getTimeStarted()-time)%character.getATKspeed() != 0){}
		    else character.attack(character.getTarget());
		}else{
		    int speedX = (int)(character.getSpeed()*character.getChangeX()/character.getDist(character.getTarget()));
		    int speedY = (int)(character.getSpeed()*character.getChangeY()/character.getDist(character.getTarget()));
		    if (speedX < 0 && ableToMove("left",character, Math.abs(speedX)) || speedX > 0 && ableToMove("right",character, Math.abs(speedX)))
			character.setX(character.getX() + speedX);
		    if (speedY < 0 && ableToMove("up",character, Math.abs(speedY)) || speedY > 0 && ableToMove("down",character, Math.abs(speedY)))
			character.setY(character.getY() + speedY);
		    if (Math.abs(character.getChangeX()) > Math.abs(character.getChangeY())){
			if (character.getChangeX() > character.getChangeY())
			    character.setRightAnimated();
			else if (character.getChangeX() < character.getChangeY())
			    character.setLeftAnimated();
		    }else{
			if (character.getChangeY() > character.getChangeX())
			    character.setDownAnimated();
			else if (character.getChangeY() < character.getChangeX())
			    character.setUpAnimated();
		    }
		}
	    }
	    for (int j = 1; j < characters.size(); j++){
		if (ai.size() <= 0){
		    if(characters.get(j).getDist(characters.get(0)) > (50*j)){
			int speedX = (int)(characters.get(j).getSpeed()*characters.get(j).getChangeX()/characters.get(j).getDist(characters.get(0)));
			int speedY = (int)(characters.get(j).getSpeed()*characters.get(j).getChangeY()/characters.get(j).getDist(characters.get(0)));
			if (speedX < 0 && ableToMove("left",characters.get(j), Math.abs(speedX)) || speedX > 0 && ableToMove("right",characters.get(j), Math.abs(speedX)))
			    characters.get(j).setX(characters.get(j).getX() + speedX);
			if (speedY < 0 && ableToMove("up",characters.get(j), Math.abs(speedY)) || speedY > 0 && ableToMove("down",characters.get(j), Math.abs(speedY)))
			    characters.get(j).setY(characters.get(j).getY() + speedY);
			//when the characters are moving
			if (Math.abs(characters.get(j).getChangeX()) > Math.abs(characters.get(j).getChangeY())){
			    if (characters.get(j).getChangeX() > characters.get(j).getChangeY())
				characters.get(j).setRightAnimated();
			    else if (characters.get(j).getChangeX() < characters.get(j).getChangeY())
				characters.get(j).setLeftAnimated();
			}else{
			    if (characters.get(j).getChangeY() > characters.get(j).getChangeX())
				characters.get(j).setDownAnimated();
			    else if (characters.get(j).getChangeY() < characters.get(j).getChangeX())
				characters.get(j).setUpAnimated();
			}
			//when the characters are idle
		    }else{
			if (Math.abs(characters.get(j).getChangeX()) > Math.abs(characters.get(j).getChangeY())){
			    if (characters.get(j).getChangeX() > characters.get(j).getChangeY())
				characters.get(j).setRight();
			    else if (characters.get(j).getChangeX() < characters.get(j).getChangeY())
				characters.get(j).setLeft();
			}else{
			    if (characters.get(j).getChangeY() > characters.get(j).getChangeX())
				characters.get(j).setDown();
			    else if (characters.get(j).getChangeY() < characters.get(j).getChangeX())
				characters.get(j).setUp();
			}
		    }
		}else{
		    if (characters.get(j).getTarget() == null)
			characters.get(j).setTarget(ai.get((int)(Math.random()*ai.size()))); //random monster on screen
		    else if (characters.get(j).getTarget().getHP() <= 0)
			characters.get(j).setTarget(null);
		    try{
			if (characters.get(j).getDist(characters.get(j).getTarget()) < characters.get(j).getRange()){
			    if (Math.abs(characters.get(j).getChangeX()) > Math.abs(characters.get(j).getChangeY())){
				if (characters.get(j).getChangeX() > characters.get(j).getChangeY())
				    characters.get(j).setRight();
				else if (characters.get(j).getChangeX() < characters.get(j).getChangeY())
				    characters.get(j).setLeft();
			    }else{
				if (characters.get(j).getChangeY() > characters.get(j).getChangeX())
				    characters.get(j).setDown();
				else if (characters.get(j).getChangeY() < characters.get(j).getChangeX())
				    characters.get(j).setUp();
			    }
			    if ((characters.get(j).getTimeStarted()-time)%characters.get(j).getATKspeed() != 0){}
			    else characters.get(j).attack(characters.get(j).getTarget());
			    if (characters.get(j).getTarget().getHP() <= 0)
				characters.get(j).setEXP(characters.get(j).getEXP()+characters.get(j).getTarget().getEXP());
			    if (characters.get(j).getEXP() >= characters.get(j).getLVLreq())
				characters.get(j).LVLup();
			}else{
			    int speedX = (int)(characters.get(j).getSpeed()*characters.get(j).getChangeX()/characters.get(j).getDist(characters.get(j).getTarget()));
			    int speedY = (int)(characters.get(j).getSpeed()*characters.get(j).getChangeY()/characters.get(j).getDist(characters.get(j).getTarget()));
			    if (speedX < 0 && ableToMove("left",characters.get(j), Math.abs(speedX)) || speedX > 0 && ableToMove("right",characters.get(j), Math.abs(speedX)))
				characters.get(j).setX(characters.get(j).getX() + speedX);
			    if (speedY < 0 && ableToMove("up",characters.get(j), Math.abs(speedY)) || speedY > 0 && ableToMove("down",characters.get(j), Math.abs(speedY)))
				characters.get(j).setY(characters.get(j).getY() + speedY);
			    if (Math.abs(characters.get(j).getChangeX()) > Math.abs(characters.get(j).getChangeY())){
				if (characters.get(j).getChangeX() > characters.get(j).getChangeY())
				    characters.get(j).setRightAnimated();
				else if (characters.get(j).getChangeX() < characters.get(j).getChangeY())
				    characters.get(j).setLeftAnimated();
			    }else{
				if (characters.get(j).getChangeY() > characters.get(j).getChangeX())
				    characters.get(j).setDownAnimated();
				else if (characters.get(j).getChangeY() < characters.get(j).getChangeX())
				    characters.get(j).setUpAnimated();
			    }
			}
		    }catch(/*IndexOutOfBounds*/Exception e){
			if (characters.get(j).getDist(characters.get(0)) > 50){
			    int speedX = (int)(characters.get(j).getSpeed()*characters.get(j).getChangeX()/characters.get(j).getDist(characters.get(0)));
			    int speedY = (int)(characters.get(j).getSpeed()*characters.get(j).getChangeY()/characters.get(j).getDist(characters.get(0)));
			    if (speedX < 0 && ableToMove("left",characters.get(j), Math.abs(speedX)) || speedX > 0 && ableToMove("right",characters.get(j), Math.abs(speedX)))
				characters.get(j).setX(characters.get(j).getX() + speedX);
			    if (speedY < 0 && ableToMove("up",characters.get(j), Math.abs(speedY)) || speedY > 0 && ableToMove("down",characters.get(j), Math.abs(speedY)))
				characters.get(j).setY(characters.get(j).getY() + speedY);
			    if (Math.abs(characters.get(j).getChangeX()) > Math.abs(characters.get(j).getChangeY())){
				if (characters.get(j).getChangeX() > characters.get(j).getChangeY())
				    characters.get(j).setRightAnimated();
				else if (characters.get(j).getChangeX() < characters.get(j).getChangeY())
				    characters.get(j).setLeftAnimated();
			    }else{
				if (characters.get(j).getChangeY() > characters.get(j).getChangeX())
				    characters.get(j).setDownAnimated();
				else if (characters.get(j).getChangeY() < characters.get(j).getChangeX())
				    characters.get(j).setUpAnimated();
			    }
			}else{
			    if (Math.abs(characters.get(j).getChangeX()) > Math.abs(characters.get(j).getChangeY())){
				if (characters.get(j).getChangeX() > characters.get(j).getChangeY())
				    characters.get(j).setRight();
				else if (characters.get(j).getChangeX() < characters.get(j).getChangeY())
				    characters.get(j).setLeft();
			    }else{
				if (characters.get(j).getChangeY() > characters.get(j).getChangeX())
				    characters.get(j).setDown();
				else if (characters.get(j).getChangeY() < characters.get(j).getChangeX())
				    characters.get(j).setUp();
			    }
			}
		    }
		}
	    }
	    
	    //kill characters and players with <= 0 hp
	    for (int i = 0; i < ai.size(); i++){
		if (ai.get(i).getHP() <= 0){
		    characters.get(0).setEXP(characters.get(0).getEXP()+ai.get(i).getEXP());
		    if (characters.get(0).getEXP() >= characters.get(0).getLVLreq())
			characters.get(0).LVLup();
		    Character deadCharacter = ai.get(i);
		    double dropChance = Math.random();
		    ai.get(i).getDrops().forEach((itemName, chance) -> {
			    if (dropChance <= chance) {
				Item droppedItem = ItemFactory.get(itemName);
				droppedItem.setX(deadCharacter.getX() - mapX);
				droppedItem.setY(deadCharacter.getY() - mapY);
				droppedItems.add(droppedItem);
			    }
			});
		    ai.remove(i);
		    i--;
		}
	    }
	    for (int i = 0; i < characters.size(); i++){
	    	if (characters.get(i).getHP()<=0){}
	    }

	    long pastTime = System.currentTimeMillis() - prevTick;

	    if (frames.size() == FPS_SAMPLE_SIZE) {
		frames.remove();
	    }
	    frames.add(pastTime);

	    // Calculate average FPS
	    long sum = 0;
	    for (long frame : frames) {
		sum += frame;
	    }
	    long averageFrame = sum / FPS_SAMPLE_SIZE;
	    averageFPS = (int)(1000 / averageFrame);
	    prevTick = System.currentTimeMillis();
	    // Only if the time passed since the previous tick is less than one
	    // second divided by the number of maximum FPS allowed do we delay
	    // ourselves to give Time time to catch up to our rendering.
	    if (pastTime < 1000.0 / MAX_FPS) {
		try {
		    Thread.sleep((long)(1000.0 / MAX_FPS)-pastTime);
		} catch (InterruptedException e) {
		    Utilities.showErrorMessage(this, e);
		}
	    }
	}
	//compares 2 rectangular objects to see if they are colliding
	private boolean isIn(int x, int y, int x1, int y1, int a, int b,int a1, int b1){
	    //	    System.out.println(x + "," + y + "," + a + "," + b );
	    //System.out.println(x + "," + y + "," + a + "," + b );
	    Rectangle player = new Rectangle(x,y,x1,y1);
	    Rectangle object = new Rectangle(a,b,a1,b1);
	    if(!player.intersects(object)){
		return true;
	    }
	    return false;
	}
	private boolean ableToMove(String direction, Character character, int speed) {
	    ArrayList<MapObject> objects = new ArrayList<MapObject>();
	    int x = character.getX(),
		y = character.getY(),
		x1 = character.getImage().getWidth(null)/2,
		y1 = character.getImage().getWidth(null)/2;
	    if(direction == "right"){x+=speed;x1+=speed;}
	    else if(direction == "left"){x-=speed;x1-=speed;}
	    else if(direction == "up"){y-=speed;y1-=speed;}
	    else if(direction == "down"){y+=speed;y1+=speed;}
	    if(direction.equals("right")){x+=speed;x1+=speed;}
	    else if(direction.equals("left")){x-=speed;x1-=speed;}
	    else if(direction.equals("up")){y-=speed;y1-=speed;}
	    else if(direction.equals("down")){y+=speed;y1+=speed;}
	        
	    for(MapObject mapObject : mapObjects){
		if(mapObject.getX() < windowWidth*2 && mapObject.getX() >0-(windowWidth) 
		   && mapObject.getY() < windowHeight*2 && mapObject.getY() > 0-windowHeight){
		    objects.add(mapObject);
		}
	    }
	    for(MapObject object : objects){
		int a1 = object.getImage().getWidth(null)/2,
		    b1 = object.getImage().getWidth(null)/2,
		    a = object.getX(),
		    b = object.getY();
		if(!isIn(x,y,x1,y1,a,b,a1,b1))
		    return false;
	    }
	    return true;
	}
	
    }	     
}
