package logic;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RallyGame extends JPanel {

    private transient PhysicsSimulation p;

    private static final double FPS = 100;
    private static final double TICK = 1.0 / FPS;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int BORDER = 20;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        for(PhysicsObject o: p.getBalls()) {

            g2d.fill(o.getShape());
        }

        for(PhysicsObject o: p.getWalls()) {

            g2d.fill(o.getShape());
        }

        for(PhysicsObject o: p.getPlayers()) {

            g2d.fill(o.getShape());
        }

        g2d.setPaint(Color.BLACK);
        g.setFont(new Font("Veranda", Font.PLAIN, 40));
        g2d.drawString(String.format("%d - %d", p.getTeamOneScore(), p.getTeamTwoScore()), WIDTH/2 - 45, HEIGHT/8);
    }

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("logic.RallyGame");
        PhysicsSimulation p = new PhysicsSimulation(WIDTH, HEIGHT, BORDER);
        RallyGame game = new RallyGame();
        game.p = p;

        frame.add(game);
        frame.setSize(WIDTH, HEIGHT + BORDER);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for(PhysicsPlayer player: p.getPlayers())
            frame.addKeyListener(player);

        boolean loop = true;

        while(loop) {

            p.update(TICK);
            game.repaint();
            Thread.sleep(Math.round(TICK * 1000));

            for(PhysicsObject b : p.getBalls()) {
                double v = b.getVelocity().size();
                if(v > 1000000 || v < 0.0001)
                    loop = false;
            }

        }
    }

}
