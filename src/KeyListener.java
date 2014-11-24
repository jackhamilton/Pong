
import java.awt.event.KeyEvent;
import java.util.Random;
import resources.GameObject;
import resources.listener.Keylistener;

/**
 * @author Jack
 */
public class KeyListener extends Keylistener {

    public GameObject paddle1, paddle2;
    public boolean lpressed = false, rpressed = false, lpressed2 = false, rpressed2 = false;
    
    public KeyListener(GameObject paddle1, GameObject paddle2) {
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
    }
    
    @Override
    public void KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_LEFT) {
            paddle1.setMovement(2, 180);
            lpressed = true;
        }else if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_RIGHT) {
            paddle1.setMovement(2, 0);
            rpressed = true;
        }
        if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_A) {
            paddle2.setMovement(2, 180);
            lpressed2 = true;
        }else if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_D) {
            paddle2.setMovement(2, 0);
            rpressed2 = true;
        }
    }

    @Override
    public void KeyReleased(KeyEvent e) {
        if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_LEFT) {
            lpressed = false;
        }else if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_RIGHT) {
            rpressed = false;
        }
        if (!lpressed && !rpressed) {
            paddle1.setMovement(0,0);
        }
        if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_A) {
            lpressed2 = false;
        }else if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_D) {
            rpressed2 = false;
        }
        if (!lpressed2 && !rpressed2) {
            paddle2.setMovement(0,0);
        }
    }

    @Override
    public void KeyTyped(KeyEvent e) {
        
    }

}