import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;

public class Animate {
    private GraphicsObject movingObj;
    private Point start;
    private Point end;
    private double elapsedTime;
    private double totalLength;

    public Animate(Point start, Point end, GraphicsObject movingObj){
        this.movingObj = movingObj;
        this.start = start;
        this.end = end;
        elapsedTime = 0;
        totalLength = start.distance(end);
    }

    public void update(double dt){
        
        elapsedTime += dt;
        double x = start.getX() + (end.getX() -start.getX()) * (elapsedTime/totalLength);
        double y = start.getY() + (end.getY() -start.getY()) * (elapsedTime/totalLength);
        System.out.println("x: " + x + " y: " + y);
        System.out.println("end " + end);
     
        if(!check()){
            movingObj.setPosition(new Point (x, y));
        }
        else{
            System.out.println("Stop");
        }
        
      
    }

    public boolean check(){
         if(Math.abs(movingObj.getPosition().getX() - end.getX()) < 1) {
            return true;
         } else {
            return false;
         }
    }
}
