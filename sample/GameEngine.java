import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameEngine implements Runnable  {

    private static final int MAX_TICKS_PER_SEC = 60;
    private static final int TICKS_SAMPLE_SIZE = 12;

    private Thread thread;
    private long prevTick;
    private LinkedList<Long> tickTimes;
    private int averageTPS;
    private boolean running;

    private ArrayList<Character> characters;
    private ArrayList<Character> ai;

    public GameEngine() {
        prevTick = -1;
        tickTimes = new LinkedList<Long>();
        characters = new ArrayList<Character>();
        ai = new ArrayList<Character>();

        // Testing
        Character slime, bird, giant, swordsman;
        slime = new Character("Slime.png", 100, 100);
        bird = new Character("Bird.png", 250, 250);
        giant = new Character("Giant.png", 250, 500);
        swordsman = new Character("Swordsman.png", 500, 250);
        ai.add(slime);
        ai.add(bird);
        ai.add(giant);
        ai.add(swordsman);
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void run() {
        while (running) {
            tick();
            simulate();
        }
    }
    
    public void pause() {
        running = false;
    }

    public void resume() {
        running = true;
    }

    // Calculates the average number of ticks per second and delays simulation
    // to below the ticks per second cap.
    public void tick() {
        long pastTime = System.currentTimeMillis() - prevTick;

        if (tickTimes.size() == TICKS_SAMPLE_SIZE) {
            tickTimes.remove();
        }
        tickTimes.add(pastTime);

        // Calculate average TPS
        long sum = 0;
        for (long tick : tickTimes) {
            sum += tick;
        }
        long averageTickTime = sum / TICKS_SAMPLE_SIZE;
        averageTPS = (int)(1000 / averageTickTime);

        prevTick = System.currentTimeMillis();

        // Delay ourself if we are too fast in order to stay below the
        // maximum number of ticks per second.
        if (pastTime < 1000.0 / MAX_TICKS_PER_SEC) {
            try {
                Thread.sleep((long)(1000.0 / MAX_TICKS_PER_SEC) - pastTime);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    // With knowledge about the world and user input, simulate the game
    public void simulate() {
        for (Character character : ai) {
            character.setX(character.getX() + (int)(Math.random() * 10 - 5));
            character.setY(character.getY() + (int)(Math.random() * 10 - 5));
        }
    }

    // Wow holy shit
    public ArrayList<ArrayList<Character>> getCharacters() {
        ArrayList<ArrayList<Character>> retAry = new ArrayList<ArrayList<Character>>();
        retAry.add(characters);
        retAry.add(ai);
        return retAry;
    }

    public int getAverageTPS() {
        return averageTPS;
    }

    public boolean isRunning() {
        return running;
    }


    // Keyboard event methods to be called from GamePanel or Screen or
    // whatever you intend
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
        } else if (keyCode == KeyEvent.VK_A) {
        } else if (keyCode == KeyEvent.VK_S) {
        } else if (keyCode == KeyEvent.VK_D) {
        }
    }

    public void keyReleased(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
        } else if (keyCode == KeyEvent.VK_A) {
        } else if (keyCode == KeyEvent.VK_S) {
        } else if (keyCode == KeyEvent.VK_D) {
        }
    }
}