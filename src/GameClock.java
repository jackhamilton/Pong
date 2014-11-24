
import game.Layer;
import game.Room;
import graphics.BufferedDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import resources.GameObject;

/**
 * @author Jack
 */
public class GameClock extends game.Clock{
    
    private GameObject paddle1, paddle2, ball, scoreboard, score1, score2;
    private int score1Counter = 0, score2Counter = 0;
    private int changeCounter = 0;
    
    @Override
    public void tick() {
        check: if (changeCounter == 0) {
            Rectangle topScoreZone = new Rectangle(0, 0, 350, 49);
            Rectangle botScoreZone = new Rectangle(0, 279, 350, 30);
            if (topScoreZone.intersects(ball.getObjectBounds())) {
                ball.move(140, 160);
                Random random = new Random();
                int angle = random.nextInt(90) + 45;
                int neg = random.nextInt(2);
                if (neg == 0) {
                    ball.setMovement(3, angle + 180);
                } else {
                    ball.setMovement(3, angle);                
                }
                score2Counter++;
                if (score2Counter == 10) {
                    score2Counter = 0;
                    score1Counter = 0;
                }
                BufferedImage score2Img = new BufferedImage(15, 40, BufferedImage.TYPE_INT_ARGB);
                score2Img.getGraphics().drawString(score2Counter+"", 3, 35);
                score2.changeSprites(new Image[]{score2Img});
                break check;
            }
            if (botScoreZone.intersects(ball.getObjectBounds())) {
                ball.move(140, 160);Random random = new Random();
                int angle = random.nextInt(90) + 45;
                int neg = random.nextInt(2);
                if (neg == 0) {
                    ball.setMovement(3, angle + 180);
                } else {
                    ball.setMovement(3, angle);                
                }
                score1Counter++;
                if (score1Counter == 10) {
                    score2Counter = 0;
                    score1Counter = 0;
                    BufferedImage score1Img = new BufferedImage(15, 40, BufferedImage.TYPE_INT_ARGB);
                    score1Img.getGraphics().drawString(score1Counter+"", 3, 15);
                    score1.changeSprites(new Image[]{score1Img});
                    BufferedImage score2Img = new BufferedImage(15, 40, BufferedImage.TYPE_INT_ARGB);
                    score2Img.getGraphics().drawString(score2Counter+"", 3, 35);
                    score2.changeSprites(new Image[]{score2Img});
                }
                BufferedImage score1Img = new BufferedImage(15, 40, BufferedImage.TYPE_INT_ARGB);
                score1Img.getGraphics().drawString(score1Counter+"", 3, 15);
                score1.changeSprites(new Image[]{score1Img});
                break check;
            }
            if (ball.getObjectBounds().intersects(paddle1.getObjectBounds())) {
                ball.movementAngle *= -1;
                ball.movementAngle += 360;
                changeCounter = 2;
                break check;
            }
            if (ball.getObjectBounds().intersects(paddle2.getObjectBounds())) {
                ball.movementAngle = 360 - ball.movementAngle;
                changeCounter = 2;
                break check;
            }
            if (ball.location.x <= 10) {
                ball.movementAngle = 180 - ball.movementAngle;
                changeCounter = 2;
                break check;
            }
            if (ball.location.x + ball.currentSprite.image.getWidth(null) >= 290) {
                ball.movementAngle -= 180;
                ball.movementAngle *= -1;
                changeCounter = 2;
                break check;
            }
            if (ball.movementAngle < 0) {
            ball.movementAngle += 360;
            }
            if (ball.movementAngle > 360) {
                ball.movementAngle -= 360;
            }
        } else {
            changeCounter--;
        }
    }
    
    @Override
    public void init() {
        
        BufferedDevice frame = bufferedDevices[0];
        BufferedImage paddleImage = new BufferedImage(70, 15, BufferedImage.TYPE_INT_ARGB);
        BufferedImage ballImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        BufferedImage scoreBImg = new BufferedImage(15, 40, BufferedImage.TYPE_BYTE_GRAY);
        BufferedImage score1Img = new BufferedImage(15, 40, BufferedImage.TYPE_INT_ARGB);
        BufferedImage score2Img = new BufferedImage(15, 40, BufferedImage.TYPE_INT_ARGB);
        score1Img.getGraphics().drawString("0", 3, 15);
        score2Img.getGraphics().drawString("0", 3, 35);
        Graphics g = ballImage.createGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillOval(0, 0, 10, 10);
        g = paddleImage.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRoundRect(0, 0, 60, 10, 4, 4);
        Room room = new Room(frame);
        Layer layer = new Layer(frame);
        paddle1 = new GameObject(115, 40, new Image[]{paddleImage}, frame);
        paddle1.setBounded(true);
        paddle2 = new GameObject(115, 270, new Image[]{paddleImage}, frame);
        paddle2.setBounded(true);
        ball = new GameObject(140, 160, new Image[]{ballImage}, frame);
        scoreboard = new GameObject(275, 140, new Image[]{scoreBImg}, frame);
        score1 = new GameObject(275, 140, new Image[]{score1Img}, frame);
        score2 = new GameObject(275, 140, new Image[]{score2Img}, frame);
        KeyListener listener = new KeyListener(paddle1, paddle2);
        frame.changeKeyListener(listener);
        layer.add(paddle1);
        layer.add(paddle2);
        layer.add(ball);
        layer.add(scoreboard);
        layer.add(score1);
        layer.add(score2);
        room.addLayer(layer, "Base");
        room.display();
        Random random = new Random();
        int angle = random.nextInt(90) + 45;
        int neg = random.nextInt(2);
        if (neg == 0) {
            ball.setMovement(3.5, angle + 180);
        } else {
            ball.setMovement(3.5, angle);                
        }
        
    }
    
}