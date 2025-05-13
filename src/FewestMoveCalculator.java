import edu.macalester.graphics.ui.Button;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

/**
 * @author Nora Betry and Elyse Quigley
 * Has the recursive method that is the algorithm for finding the steps to solve and adds steps to a list and then animates one by one
 */
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

    /**
     * The recursive method which first checks if there is only one disk remaining, and if so moves it over
     * Otherwise, it moves all the disks except the bottom from the starting stack to an intermediary stack, 
     * moves the bottom to the end stack, and then recursively moves the disks in the intermediary stack to the end
     * All "moves" are stored in a list to be executed later
     * @param stack        The stack of disks that must be moved to the end stack
     * @param start        The number label of the stacks correlating to where the disks will be moved
     * @param end
     * @param mid
     */
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
    
    /**
     * Goes through the ArrayList of moves and animates them one by one, after the user clicks the next button. 
     */
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
