package breakout;

import edu.macalester.graphics.*;

import java.awt.Color;

/**
 * Description: A paddle is represented as a Rectangle graphical object which helps the ball bounces 
 */

public class Paddle extends Rectangle {
    /**
     * Constructs a paddle with the specified size and position
     */
    public Paddle(double width, double height, double x, double y) {
        super(x, y, width, height);
        setFillColor(Color.BLACK);
    }
}
