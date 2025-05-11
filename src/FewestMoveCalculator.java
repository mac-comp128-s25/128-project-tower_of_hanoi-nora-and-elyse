import edu.macalester.graphics.ui.Button;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;


public class FewestMoveCalculator {
    private GameBoard gb;
    private GameManager gm;
    private ArrayList<String> moves;
    private Iterator<String> moveIterator;
    public FewestMoveCalculator(GameBoard gb, GameManager gm, DiskManager dm){
        this.gb = gb;
        this.gm = gm;
        moves = new ArrayList<>();  

    }

    public void moveEfficiently(Deque<Disk> stack, int start, int end, int mid) {
        if (stack.size() == 1) {
            String move = "" + start + end;
            moves.add(move);
        } else {
            Deque<Disk> tempStack = new ArrayDeque<>(stack);
            tempStack.removeLast();
            moveEfficiently(tempStack, start, mid, end); 
            moves.add("" + start + end);                
            moveEfficiently(tempStack, mid, end, start); 
        }
    }
    
    public void setupMoveExecution() {
        moveIterator = moves.iterator();
    
        Button next = new Button("Next Step");
        gb.getCanvas().add(next, 0, 25);
        gb.getCanvas().draw();
    
        next.onClick(() -> {
            if (moveIterator.hasNext()) {
                String move = moveIterator.next();
                gm.shuffleStacks(move, gb);
                gb.getCanvas().draw();
            }
        });
    }
}
