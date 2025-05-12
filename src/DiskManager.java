import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import edu.macalester.graphics.CanvasWindow;

/**
 * @author Nora Betry and Elyse Quigley
 * Handles disk object operations, including checking if all disks have been moved to final tower.
 */
public class DiskManager {
    private CanvasWindow canvas;

    public DiskManager(CanvasWindow canvas){
        this.canvas = canvas;
    }

    /**
     * Adds the rectangle object to the canvas window. 
     * @param disk         The disk object whose rectangle is being added. 
     */
    public void addToBoard(Disk disk){
        canvas.add(disk.getRectangle());
    }

    /**
     * Checks if the conditions are met for a win: first two stacks are empty and last is full
     * @param arrayList       An arraylist of the three stacks that hold the disks
     * @param gb              The gameboard the stacks are in
     * @return
     */
    public boolean checkIfDone(ArrayList<Deque<Disk>> arrayList, GameBoard gb){
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
            gb.setSolved(true);
            return true;
        }
        return false;
    }

    
}
