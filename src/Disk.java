import java.awt.Color;

import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

/**
 * @author Nora Betry and Elyse Quigley
 * Creates the disk object and sets the positions
 */
public class Disk {
    private int diameter;
    private Point position;
    private Rectangle disk;

    public Disk(int diameter, Point position){
        this.diameter = diameter;
        this.position = position;
        disk = createRectangle();
       
    }

    /**
     * Creates and returns a rectangle object and selects a random color for it from our predetermined list. 
     */
    private Rectangle createRectangle(){
        Color[] colorOptions = {new Color(252, 120, 158), new Color(240,144,100), new Color(245,201,133), new Color (242,151,129), new Color(163,129,242), new Color(240,129,214), new Color(129,242,208), new Color(129,234,242), new Color(129,196,242)};
        Rectangle rectangle = new Rectangle(position, new Point(diameter, 20));
        int randint = (int) (Math.random() * 8 ) + 1;
        rectangle.setFillColor(colorOptions[randint]);

        return rectangle;
    }

  
    public Rectangle getRectangle(){
        return disk;
    }

    public void setPosition(Point point){
        disk.setPosition(point);
        position = point;        
    }

    
}
