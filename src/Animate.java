import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;

public class Animate {
    private GraphicsObject movingObj;
    private Point start;
    private Point end;
    private double elapsedTime;
    private double totalLength;
    private DiskManager dm;
    private GameBoard gb;
    private int stepTracker;

    public Animate(Point start, Point end, GraphicsObject movingObj, GameManager gm, DiskManager dm, GameBoard gb){ 
        this.movingObj = movingObj;
        this.start = start;
        this.end = end;
        elapsedTime = 0;
        totalLength = start.distance(end);
        this.dm = dm;
        this.gb = gb;
        stepTracker = 0;
    }

    public void update(double dt){
        
        elapsedTime += dt;
        double x = start.getX() + (end.getX() -start.getX()) * (elapsedTime/totalLength);
        double y = start.getY() + (end.getY() -start.getY()) * (elapsedTime/totalLength);
     
        if(!check()){
            movingObj.setPosition(new Point (x, y));
        } 
        else if (dm.checkIfDone(gb.getStacks(), gb)) {
            gb.nextLevel();
        }
        
        
      
    }

    public void updateOver(double dt){
        elapsedTime += dt;
        double x = 0;
        double y =0;
        if(stepTracker == 0){
            x = start.getX();
            y = start.getY() + (175 -start.getY()) * (elapsedTime/totalLength);
            if(Math.abs(y - 175) < 3){
                stepTracker++;
                elapsedTime = 0;

            }
        }
        else if(stepTracker == 1){
            x = start.getX() + (end.getX() -start.getX()) * (elapsedTime/totalLength);
            y = 175;
            if(Math.abs(x - end.getX()) < 3){
                stepTracker++;
                elapsedTime = 0;
            }
        }
        else if(stepTracker == 2){
            x = end.getX();
            y = 175 + (end.getY() -175) * (elapsedTime/totalLength);
            if(Math.abs(y - end.getY()) < 3){
                stepTracker++;
            }

        }
        if(!check()){
            movingObj.setPosition(new Point (x, y));
        } 
        else if (dm.checkIfDone(gb.getStacks(), gb)) {
            gb.nextLevel();
        }

    }

    public boolean check(){
         if((Math.abs(movingObj.getPosition().getX() - end.getX()) < 3) && (Math.abs(movingObj.getPosition().getY() - end.getY()) < 3)) {
            movingObj.setPosition(end);
            return true;
         } else {
            return false;
         }
    }

 
}
