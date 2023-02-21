package breakout;

import edu.macalester.graphics.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: This class generates brick layers on the screen, keeps track of the bricks
    * and handles their removal
 */

public class BrickAdministrator {
    private List<Brick> bricks;
    private CanvasWindow canvas;
    private List<Color> brickColor = List.of(new Color(0xffcee6), new Color(0xfcbcd7), new Color(0xf9a3cb),
                                            new Color(0xef87be), new Color(0xe56ab3));

    /**
     * Constructs a brick administrator for the specified canvas
     */
    public BrickAdministrator(CanvasWindow canvas) {
        bricks = new ArrayList<>();
        this.canvas = canvas;
    }

    /**
     * Generates layers of bricks on the screen according to the specified size, distance between
        *  the bricks and layers per color
     */
    public void createBrickLayers(double widthRatio, double heightRatio, double dist, int layerPerColor) {
        for (double i = 0; i < canvas.getWidth(); i += canvas.getWidth() * widthRatio) {
            for (Color c: brickColor) {
                for (int j = 0; j < layerPerColor; j++) {
                    double initialY = canvas.getHeight() / 8;
                    double num = layerPerColor * brickColor.indexOf(c) + j;
                    Brick brick = new Brick(canvas.getWidth() * widthRatio - dist, 
                                            canvas.getHeight() * heightRatio, i,
                                            initialY + canvas.getHeight() * heightRatio * num + dist * num, 
                                            c);
                    canvas.add(brick);
                    bricks.add(brick);
                }
            }
        }
    } 

    /**
     * Removes a brick from the canvas and the list of bricks
     */
    public void removeBrick(Brick b) {
        canvas.remove(b);
        bricks.remove(b);
    }

    /**
     * Check for existing bricks
     * @return true if there are bricks that have not been hit
     */
    public boolean bricksStillExist() {
        return bricks.size() > 0;
    }

    @Override
    public String toString() {
        return "BrickAdministrator [brickColor=" + brickColor + ", bricks=" + bricks + ", canvas=" + canvas + "]";
    }
}
