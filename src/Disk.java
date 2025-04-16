import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class Disk {
    private int diameter;
    private Point position;
    private boolean correctPlace;
    private CanvasWindow canvas;
    private Rectangle disk;

    public Disk(int diameter, Point position, CanvasWindow canvas){
        this.diameter = diameter;
        this.position = position;
        this.canvas = canvas;
        disk = createRectangle();

    }

    private Rectangle createRectangle(){
        Rectangle rectangle = new Rectangle(position, new Point(diameter, 20));
        rectangle.setFillColor(Color.BLUE);

        return rectangle;
    }

    public Rectangle getRectangle(){
        return disk;
    }

    
}
