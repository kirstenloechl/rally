package login_test;

import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RallyGame extends JPanel {

    PhysicsSimulation p;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for(PhysicsRectangle r : p.rectangles) {
            g2d.draw(r);
        }
        g2d.draw(p.ball);
    }

    public void play () throws InterruptedException {

        JFrame frame = new JFrame("RallyGame");
        PhysicsSimulation p = new PhysicsSimulation();
        RallyGame game = new RallyGame();
        game.p = p;

        frame.add(game);
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addKeyListener(p.player);

        while (true) {
            p.update();
            game.repaint();
            Thread.sleep(Math.round(p.SPF * 1000));
        }
    }

}
