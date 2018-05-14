import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PhysicsPlayer extends PhysicsRectangle implements KeyListener{

    private int l;
    private int r;
    public double d;

    public PhysicsPlayer(double x, double y, double w, double h, double c, int l, int r, double d) {

        super(x, y, w, h, c);

        this.l = l;
        this.r = r;
        this.d = d;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == l) {
            this.x -= d;

        }

        if (e.getKeyCode() == r) {
            this.x += d;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
