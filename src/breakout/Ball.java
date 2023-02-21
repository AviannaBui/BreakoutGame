package breakout;

import edu.macalester.graphics.*;

import java.awt.Color;
import java.util.List;

/**
 * Description: Represents a ball that bounces upon contact with other objects
 */

public class Ball extends Ellipse {
    private double velocityX;
    private double velocityY;
    private double maxX;

    /**
     * Constructs a ball with the specified size, velocity, bounds and color
     */
    public Ball(double x, double y, double width, double height, 
                double velocityX, double velocityY, double maxX) {
        super(x, y, width, height);
        setFillColor(new Color(0xB19CD8));
        
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.maxX = maxX;
    }

    /**
     * Set up the rules for the ball's movement
     */
    public void move(CanvasWindow canvas) {
        moveBy(velocityX * 0.5, velocityY * 0.5);
        if (getX() <= 0 || getX() + getWidth() >= maxX) { 
            this.setVelocityX(-velocityX);
        }
        if (getY() <= 0) { 
            this.setVelocityY(-velocityY);
        }
        if (getIntersectingObject(canvas) instanceof Paddle) {
            this.setVelocityY(-velocityY);
        } 
        if (getIntersectingObject(canvas) instanceof Brick) {
            if (canvas.getElementAt(getUpperPoint()) != null || 
                canvas.getElementAt(getLowerPoint()) != null) {
                this.setVelocityY(-velocityY);
            } else {
                this.setVelocityX(-velocityX);
            }
        }
    }

    /**
     * Return the object that intersects with the ball
     */
    public GraphicsObject getIntersectingObject(CanvasWindow canvas) {
        List<Point> points = List.of(getUpperPoint(), getLowerPoint(), getLeftPoint(), getRightPoint()); 
        for (Point p: points) {
            GraphicsObject obj = canvas.getElementAt(p);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }

    private Point getUpperPoint() {
        return new Point(getX() + getWidth() / 2, getY() - 1);
    }

    private Point getLowerPoint() {
        return new Point(getX() + getWidth() / 2, getY() + getHeight() + 1);
    }

    private Point getLeftPoint() {
        return new Point(getX() - 1, getY() + getHeight() / 2);
    }

    private Point getRightPoint() {
        return new Point(getX() + getWidth() + 1, getY() + getHeight() / 2);
    }

    private void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    private void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    @Override
    public String toString() {
        return "Ball [velocityX=" + velocityX + ", velocityY=" + velocityY + "]";
    }
}
