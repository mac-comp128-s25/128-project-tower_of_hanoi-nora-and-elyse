import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import edu.macalester.graphics.CanvasWindow;

public class DiskManager {
    private CanvasWindow canvas;

    public DiskManager(CanvasWindow canvas){
        this.canvas = canvas;
    }

    public void addToBoard(Disk disk){
        canvas.add(disk.getRectangle());
    }

    public boolean checkIfDone(GameBoard gb){
        ArrayList<Deque<Disk>> arrayList = gb.getStacks();
        if(arrayList.get(0).isEmpty() && arrayList.get(1).isEmpty()){
            Deque<Disk> tempDeque = new ArrayDeque<Disk>();
            tempDeque.addAll(arrayList.get(2));
            while(!tempDeque.isEmpty()){
                double prev = tempDeque.pop().getRectangle().getWidth();
                if(!tempDeque.isEmpty()){
                    if(prev > tempDeque.peek().getRectangle().getWidth()){
                        return false;
                    }
                }
            }
            System.out.println("success!!");
            gb.setSolved(true);
            return true;
        }
        return false;
    }

    
}
