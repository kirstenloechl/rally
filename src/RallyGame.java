import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RallyGame extends JPanel {

    PhysicsCircle ball;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillOval((int) Math.round(ball.pos_x), (int) Math.round(ball.pos_y), 30, 30);
    }

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("RallyGame");
        PhysicsSimulation p = new PhysicsSimulation();
        RallyGame game = new RallyGame();
        game.ball = p.ball;

        frame.add(game);
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            p.update();
            game.repaint();
            Thread.sleep(Math.round(p.SPF * 1000));
        }
    }

}
