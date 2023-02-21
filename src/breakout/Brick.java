package breakout;

import edu.macalester.graphics.*;

import java.awt.Color;

/**
 * Description: Represents a single brick that can be destroyed by a ball
 */

public class Brick extends Rectangle {
    private Color color;

    /**
     * Constructs a brick with the specified size, position and color
     */
    public Brick(double width, double height, double x, double y, Color color) {
        super(x, y, width, height);
        this.color = color;
        createBrickDrawing();
    }

    /**
     * Draw the brick with the specified color and outline condition
     */
    private void createBrickDrawing() {
        setFillColor(color);
        setStroked(false);
    }
}

