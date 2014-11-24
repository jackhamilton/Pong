
import game.Layer;
import game.Room;
import graphics.BufferedDevice;
import graphics.swing.BufferedJFrame;

/**
 *
 * @author Jack
 */
public class Main {

    public static void main(String[] args) {
        
        BufferedJFrame frame = new BufferedJFrame(0, 0, 300, 300, "Pong");
        GameClock clock = new GameClock();
        clock.start(60, new BufferedDevice[]{frame});
        
    }
    
}