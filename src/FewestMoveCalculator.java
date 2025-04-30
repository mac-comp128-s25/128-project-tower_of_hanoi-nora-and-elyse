import java.util.ArrayDeque;
import java.util.Deque;

public class FewestMoveCalculator {
    private GameBoard gb;
    private GameManager gm;
    private DiskManager dm;

    public FewestMoveCalculator(GameBoard gb, GameManager gm, DiskManager dm){
        this.gb = gb;
        this.gm = gm;
        this.dm = dm;
    }

    public void moveEffieciently(Deque stack, int start, int end, int mid){
        if(stack.size() == 1){
            String move = "" + start + end;
            System.out.println(move);
            gm.shuffleStacks(move, gb);
            gb.getCanvas().draw();
        }
        else if (!dm.checkIfDone(gb.getStacks(), gb)){
            Deque<Disk> tempStack = new ArrayDeque<>();
            tempStack.addAll(stack);
            System.out.println("og: " +stack.toString());
            System.out.println("copy " + tempStack.toString());

            if(!tempStack.isEmpty()){
                tempStack.removeLast();
            
            moveEffieciently(tempStack, start, mid, end);   // Step 1
            gb.getCanvas().draw();
            gb.getCanvas().pause(500);
            String move = "" + start + end;
            System.out.println("MOVE:   " + move);
            gm.shuffleStacks(move, gb);             // Step 2
            gb.getCanvas().draw();
            gb.getCanvas().pause(500);

            moveEffieciently(tempStack, mid, end, start);
            gb.getCanvas().draw();
            gb.getCanvas().pause(500);

            }
        }
    }

    public void moveLowestDisk(int start, int end, int mid){

    }


}
// 3 -- far 
// 4 -- mid