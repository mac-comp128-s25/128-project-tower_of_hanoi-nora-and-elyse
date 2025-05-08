import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;

public class Animate {
    private GraphicsObject movingObj;
    private Point start;
    private Point end;
    private double elapsedTime;
    private double totalLength;
    private GameManager gm;
    private DiskManager dm;
    private GameBoard gb;

    public Animate(Point start, Point end, GraphicsObject movingObj, GameManager gm, DiskManager dm, GameBoard gb){ 
        this.movingObj = movingObj;
        this.start = start;
        this.end = end;
        elapsedTime = 0;
        totalLength = start.distance(end);
        this.gm = gm;
        this.dm = dm;
        this.gb = gb;
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
        else if (dm.checkIfDone(gb.getStacks(), gb)) {
                // System.out.println("You finished the level!");
                // //gb.setSolved(true);
            gb.nextLevel();
               //gb.winScreen();
        }
        else{
            System.out.println("Stop");
        }
        
      
    }

    public boolean check(){
         if(Math.abs(movingObj.getPosition().getX() - end.getX()) < 3) {
            movingObj.setPosition(end);
            return true;
         } else {
            return false;
         }
    }
}
