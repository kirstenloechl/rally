package game;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class PhysicsSimulation {

    private double tick;
    private double width;
    private double height;

    private int teamOneScore;
    private int teamTwoScore;

    private List<PhysicsObject> balls;
    private List<PhysicsObject> walls;
    private List<PhysicsPlayer> players;

    private static final String BALL = "BALL";
    private static final String FIELD = "FIELD";
    private static final String TEAMONE = "TEAMONE";
    private static final String TEAMTWO = "TEAMTWO";

    public PhysicsSimulation(int width, int height, int border) {

        this.balls = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.players = new ArrayList<>();

        this.width = width;
        this.height = height;

        PhysicsObject ball = new PhysicsObject(BALL, new Ellipse2D.Double(), new Vector2D(30, 30), new Vector2D(100, 100), 100, Color.WHITE);
        ball.setBounce(0.9);
        ball.setAcceleration(0, 100);

        balls.add(ball);
        reset();

        PhysicsObject topWall = new PhysicsObject(FIELD, new Rectangle2D.Double(), new Vector2D(0, 0), new Vector2D(width, border));
        PhysicsObject leftWall = new PhysicsObject(FIELD, new Rectangle2D.Double(), new Vector2D(0, 0), new Vector2D(border, height));
        PhysicsObject rightWall = new PhysicsObject(FIELD, new Rectangle2D.Double(), new Vector2D(width - border, 0), new Vector2D(width, height));
        PhysicsObject centerWall = new PhysicsObject(FIELD, new Rectangle2D.Double(), new Vector2D((width - border) / 2, (height - border) / 2), new Vector2D((width + border) / 2, height - border));

        PhysicsObject teamOne = new PhysicsObject(TEAMONE, new Rectangle2D.Double(), new Vector2D(0, height - border), new Vector2D(width/2, height));
        PhysicsObject teamTwo = new PhysicsObject(TEAMTWO, new Rectangle2D.Double(), new Vector2D(width/2, height - border), new Vector2D(width, height));
        teamOneScore = 0;
        teamTwoScore = 0;

        walls.add(topWall);
        walls.add(leftWall);
        walls.add(rightWall);
        walls.add(teamOne);
        walls.add(teamTwo);
        walls.add(centerWall);

        PhysicsPlayer playerOne = new PhysicsPlayer(new PhysicsObject(TEAMONE, new Rectangle2D.Double(), new Vector2D(width / 15, border), new Vector2D((width - border) / 4, height - 2 * border), 0, Color.RED), (border), (width - border) / 2, (width / 5), KeyEvent.VK_A, KeyEvent.VK_D);
        PhysicsPlayer playerTwo = new PhysicsPlayer(new PhysicsObject(TEAMTWO, new Rectangle2D.Double(), new Vector2D(width / 15, border), new Vector2D((width - border) * 3 / 4, height - 2 * border), 0, Color.BLUE), (width + border) / 2, (width - border), (width / 5), KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        players.add(playerOne); // Ready Player One
        players.add(playerTwo);

    }

    public List<PhysicsObject> getBalls() {
        return balls;
    }

    public List<PhysicsObject> getWalls() {
        return walls;
    }

    public List<PhysicsPlayer> getPlayers() {
        return players;
    }

    public int getTeamOneScore() {
        return teamOneScore;
    }

    public int getTeamTwoScore() {
        return teamTwoScore;
    }

    public void reset() {

        for(PhysicsObject b: balls) {

            b.setPosition(width / 2, height / 4);
            b.setVelocity(Math.random() * 500 - 250, Math.random() * 500 - 250);

        }
    }

    public void update(double tick) {

        for(PhysicsObject b: balls) {
            b.update(tick);
        }

        for(PhysicsPlayer p: players) {
            p.update(tick);
        }

        collidePlayers();
        collideWalls();

    }

    private void collideWalls() {

        String id;

        for(PhysicsObject w: this.walls) {
            for (PhysicsObject b : balls) {

                id = collide(b, w);

                if(id == null) {
                    continue;
                } if(id == TEAMONE) {
                    reset();
                    teamTwoScore++;
                    return;
                } else if (id == TEAMTWO) {
                    reset();
                    teamOneScore++;
                    return;
                } else {
                    PhysicsSound.playSound("/sounds/Bounce.wav");
                }
            }
        }
    }

    private void collidePlayers() {

        String id;

        for(PhysicsPlayer p: this.players) {
            for (PhysicsObject b : balls) {
                id = collide(b, p);
                if(id != null) {
                    PhysicsSound.playSound("/sounds/BounceLow.wav");
                }
            }
        }

    }

    private String collide(PhysicsObject b, PhysicsObject o) {

        Vector2D relp = b.getPosition().subtract(o.getPosition());
        if(relp.size() > b.getRadius().getX() + o.getRadius().size())
            return null;

        Vector2D relv = b.getVelocity().subtract(o.getVelocity());

        // Compute vector from the point of collision to the circle center
        double closestX = (Math.abs(relp.getX()) > Math.abs(o.getRadius().getX())) ? Math.copySign(Math.abs(o.getRadius().getX()), relp.getX()) : relp.getX();
        double closestY = (Math.abs(relp.getY()) > Math.abs(o.getRadius().getY())) ? Math.copySign(Math.abs(o.getRadius().getY()), relp.getY()) : relp.getY();

        // Compute normal vector between objects
        Vector2D normal = new Vector2D(relp.getX() - closestX, relp.getY() - closestY);

        // Circle center is inside the rectangle
        if(relp.getX() == closestX && relp.getY() == closestY) {

            // Approximated solution
            normal = normal.scale(-1);

        }

        // Bodies have not yet collided
        if(normal.size() > b.getRadius().getX())
            return null;

        Vector2D unitNormal = normal.unit();
        Vector2D unitTangent = new Vector2D(-unitNormal.getY(), unitNormal.getX());

        // Break velocity into components
        double normalVelocity = unitNormal.dot(relv);
        double tangentVelocity = unitTangent.dot(relv);

        // Bodies do not collide if moving away from one another
        if(normalVelocity > 0)
            return null;

        // Geometric mean of the coefficients of restitution
        double bounce = Math.sqrt(b.getBounce() * o.getBounce());
        double friction = 0.2;

        // Compute normal impulse
        double normalImpulse = -(1 + bounce) * normalVelocity;
        normalImpulse /= b.getInverseMass() + o.getInverseMass();

        // Compute tangential impulse (is that even a thing?)
        double tangentImpulse = friction * tangentVelocity;
        tangentImpulse /= b.getInverseMass() + o.getInverseMass();

        // Apply normal impulse
        b.setVelocity(b.getVelocity().add(unitNormal.scale(b.getInverseMass() * normalImpulse)));
        o.setVelocity(o.getVelocity().subtract(unitNormal.scale(o.getInverseMass() * normalImpulse)));

        // Apply tangential impulse
        b.setVelocity(b.getVelocity().subtract(unitTangent.scale(b.getInverseMass() * tangentImpulse)));
        o.setVelocity(o.getVelocity().add(unitTangent.scale(o.getInverseMass() * tangentImpulse)));

        return o.getId();

    }

}
