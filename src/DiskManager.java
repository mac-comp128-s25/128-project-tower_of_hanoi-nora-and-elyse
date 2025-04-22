import edu.macalester.graphics.CanvasWindow;

public class DiskManager {
    private CanvasWindow canvas;

    public DiskManager(CanvasWindow canvas){
        this.canvas = canvas;
    }

    public void addToBoard(Disk disk){
        canvas.add(disk.getRectangle());
    }

    public boolean checkIfDone(){
       
        return false;
    }

    
}
