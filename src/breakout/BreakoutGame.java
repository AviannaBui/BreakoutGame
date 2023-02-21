package breakout;

import edu.macalester.graphics.*;

import java.awt.Color;

/**
 * Author: Avianna
 * Description: The main game class to run the Breakout game
 */

public class BreakoutGame {
    private static final int CANVAS_WIDTH = 600; 
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private BrickAdministrator brickAdmin;
    private Paddle paddle;
    private Ball ball;
    private GraphicsText liveDisplay;

    private int lives;
    private boolean isMoving;
    private boolean newRound;

    /**
     * Create the Breakout game
     */
    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);

        brickAdmin = new BrickAdministrator(canvas);

        paddle = new Paddle(CANVAS_WIDTH * 0.15, CANVAS_HEIGHT * 0.008, 0, 0);

        ball = new Ball(0, 0, 15, 15, 4.3, 4.7, CANVAS_WIDTH);

        lives = 3;
        liveDisplay = new GraphicsText();

        newRound = true;

        canvas.onMouseMove(event -> {
            if (isMoving) {
                if (event.getPosition().getX() > paddle.getWidth() / 2 && 
                    event.getPosition().getX() <= CANVAS_WIDTH - paddle.getWidth()) {
                    paddle.setCenter(event.getPosition().getX(), CANVAS_HEIGHT * 0.65);
                } else if (event.getPosition().getX() <= paddle.getWidth() / 2) {
                    paddle.setCenter(paddle.getWidth() / 2, CANVAS_HEIGHT * 0.65);
                } else {
                    paddle.setCenter(CANVAS_WIDTH - paddle.getWidth() / 2, CANVAS_HEIGHT * 0.65);
                }
            }
        });

        canvas.animate(() -> { 
            if (newRound) {
                if (!isMoving) {
                    canvas.pause(3000);
                }
                isMoving = true;
                ball.move(canvas);  
                hitBrick();  
            }
            if (loseRound()) {
                lives--;
                if (lives < 3) {
                    updateLayout(); 
                }
                isMoving = false;
            } 
            if (winGame()) {
                gameEndingSetUp(new GraphicsText(), "YOU WIN!!!", new Color(0xf79ac0));
            }
            if (lives == 0 && !winGame()) {
                newRound = false;
                gameEndingSetUp(new GraphicsText(), "BOOOOO!!! TRY AGAIN", new Color(0x9B1003));
            } 
        });
    }

    /**
     * Operates the game
     */
    public void run() {
        screenSetUp();
        updateLayout();
    }

    /**
     * Set up the game screen by adding and positioning the ball, bricks, paddle, and players'
        * lives information
     */
    public void screenSetUp() {
        brickAdmin.createBrickLayers(0.1, 0.025, 1, 2);
        canvas.add(paddle);
        canvas.add(ball);

        liveDisplay.setFont(FontStyle.BOLD, 18);
        canvas.add(liveDisplay);
    }

    /**
     * Update the position/information of the ball and lives' display text on the screen
     */
    private void updateLayout() {
        paddle.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT * 0.65);
        ball.setCenter(CANVAS_WIDTH / 2 , CANVAS_HEIGHT * 0.4);

        liveDisplay.setText("Lives: " + lives);
        liveDisplay.setCenter(CANVAS_WIDTH * 0.92, CANVAS_HEIGHT * 0.028);
    }

    /**
     * Checks whether the ball hits any brick, removing the intersecting brick if so.
     */
    private void hitBrick() {
        GraphicsObject obj = ball.getIntersectingObject(canvas);
        if (obj instanceof Brick brick) {
            brickAdmin.removeBrick(brick);
        }
    }

    /**
     * Set logic for winning the game
     */
    private boolean winGame() {
        return !brickAdmin.bricksStillExist();
    }

    /**
     * Set logic for losing a game round
     */
    private boolean loseRound() {
        return ball.getY() >= paddle.getY() + paddle.getHeight();
    }

    /**
     * Set up the ending screen for the game when it finishes
     */
    private void gameEndingSetUp(GraphicsText text, String s, Color color) {
        canvas.removeAll();
        text.setText(s);
        text.setFont(FontStyle.BOLD, 50);
        text.setFillColor(color);
        text.setCenter(CANVAS_WIDTH * 0.5, CANVAS_HEIGHT * 0.4);
        canvas.add(text);
    }

    public static void main(String[] args) {
        new BreakoutGame().run();
    }
}
