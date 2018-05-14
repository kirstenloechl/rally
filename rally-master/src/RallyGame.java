import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;

public class RallyGame extends JPanel implements KeyListener {

    PhysicsSimulation p;
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static int keys[] = new int[] {0, 0, 0, 0};
    public RallyGame() {
    	addKeyListener(this);
    	setFocusable(true);
    	setFocusTraversalKeysEnabled(false);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for(PhysicsRectangle r : p.rectangles) {
            g2d.draw(r);
        }
        for(Player pl : players) {
        	g2d.draw(pl);
        }
        g2d.draw(p.ball);
    }

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("RallyGame");
        PhysicsSimulation p = new PhysicsSimulation();
        Player player1 = new Player(500, 440, 90, 60, 1, 2);
        players.add(player1);
        Player player2 = new Player(200, 440, 90, 60, 1, 2);
        players.add(player2);
        RallyGame game = new RallyGame();
        game.p = p;

        frame.add(game);
        frame.setSize(1015, 560);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            p.update();
            players.get(0).x += players.get(0).speed * (keys[1] - keys[0]);
            players.get(1).x += players.get(1).speed * (keys[3] - keys[2]);
            game.repaint();
            Thread.sleep(Math.round(p.SPF * 1000));
        }
    }
    
    public void keyPressed(KeyEvent e) {
    	switch(e.getKeyCode()) {
    	case KeyEvent.VK_LEFT:
    		keys[0] = 1;
    		break;
    	case KeyEvent.VK_RIGHT:
    		keys[1] = 1;
    		break;
    	case KeyEvent.VK_A:
    		keys[2] = 1;
    		break;
    	case KeyEvent.VK_D:
    		keys[3] = 1;
    		break;
    	default:
    		break;
    	}
    }
    
    public void keyTyped(KeyEvent e) {
    	
    }
    
    public void keyReleased(KeyEvent e) {
    	switch(e.getKeyCode()) {
    	case KeyEvent.VK_LEFT:
    		keys[0] = 0;
    		break;
    	case KeyEvent.VK_RIGHT:
    		keys[1] = 0;
    		break;
    	case KeyEvent.VK_A:
    		keys[2] = 0;
    		break;
    	case KeyEvent.VK_D:
    		keys[3] = 0;
    		break;
    	default:
    		break;
    	}
    }
    

}
