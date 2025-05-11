import edu.macalester.graphics.ui.Button;

import java.awt.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

import edu.macalester.graphics.GraphicsObject;

public class FewestMoveCalculator {
    private GameBoard gb;
    private GameManager gm;
    private DiskManager dm;
    private ArrayList<String> moves;
    private Iterator<String> moveIterator;
    public FewestMoveCalculator(GameBoard gb, GameManager gm, DiskManager dm){
        this.gb = gb;
        this.gm = gm;
        this.dm = dm;
        moves = new ArrayList<>();  // Make this a class-level field
          

    }

   

    // public void moveEffieciently(Deque stack, int start, int end, int mid){
    //     Button next = new Button("Next Step");
        
    //     gb.getCanvas().add(next, 0, 20);
    //     gb.getCanvas().draw();
    //     gb.getCanvas().pause(1000);
        
    //     if(stack.size() == 1){
            
    //         String move = "" + start + end;
    //         System.out.println(move);
    //         // next.onClick(() -> gm.shuffleStacks(move, gb, true));
    //         moves.add(move);
    //         for(String s : moves){
    //             System.out.println("MOVE: " +s);
    //             next.onClick(() -> gm.shuffleStacks(s, gb, true));
                

    //         }
    //         moves.clear();
    //         gb.getCanvas().draw();
    //     }
    //     else if (!dm.checkIfDone(gb.getStacks(), gb)){
    //         Deque<Disk> tempStack = new ArrayDeque<>();
    //         tempStack.addAll(stack);
    //         System.out.println("og: " +stack.toString());
    //         System.out.println("copy " + tempStack.toString());

    //         if(!tempStack.isEmpty()){
    //             tempStack.removeLast();
            
    //         moveEffieciently(tempStack, start, mid, end);   // Step 1
    //         gb.getCanvas().draw();
    //         //gb.getCanvas().pause(500);
    //         String move = "" + start + end;
    //         System.out.println("MOVE:   " + move);
    //         //next.onClick(() -> gm.shuffleStacks(move, gb, true));
    //         moves.add(move);
    //         // gb.getCanvas().pause(200);
    //         // gb.getCanvas().draw();
    //         //gb.getCanvas().pause(500);

    //         moveEffieciently(tempStack, mid, end, start);
    //         // gb.getCanvas().draw();
    //         //gb.getCanvas().pause(500);

    //         }
    //     }
        
    // }

    public void moveEfficiently(Deque<Disk> stack, int start, int end, int mid) {
        if (stack.size() == 1) {
            String move = "" + start + end;
            moves.add(move);
        } else {
            Deque<Disk> tempStack = new ArrayDeque<>(stack);
            tempStack.removeLast();
            moveEfficiently(tempStack, start, mid, end); // Step 1
            moves.add("" + start + end);                // Step 2
            moveEfficiently(tempStack, mid, end, start); // Step 3
        }
    }
    
    public void setupMoveExecution() {
        moveIterator = moves.iterator();
    
        Button next = new Button("Next Step");
        gb.getCanvas().add(next, 0, 20);
        gb.getCanvas().draw();
    
        next.onClick(() -> {
            if (moveIterator.hasNext()) {
                String move = moveIterator.next();
                System.out.println("MOVE: " + move);
                gm.shuffleStacks(move, gb, true);
                gb.getCanvas().draw();
            }
        });
    }

    public void moveLowestDisk(int start, int end, int mid){

    }


}
// 3 -- far 
// 4 -- mid