import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;


import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;

public class GameBoard {
    private CanvasWindow board;
    private GraphicsGroup structure;
    private Deque<Disk> stack1 = new ArrayDeque<Disk>();
    private Deque<Disk> stack2 = new ArrayDeque<Disk>();
    private Deque<Disk> stack3 = new ArrayDeque<Disk>();



    public GameBoard(){
        board = new CanvasWindow("Tower of Hanoi", 800, 500);
        structure = constructBoard();
        board.add(structure);
        board.draw();

        
    }

    private GraphicsGroup constructBoard(){
        GraphicsGroup gg = new GraphicsGroup();
        Rectangle bottomRectangle = new Rectangle(new Point(50, 450), new Point(700, 10));
        bottomRectangle.setFillColor(Color.DARK_GRAY);
        gg.add(bottomRectangle);
        Rectangle leftRectangle = new Rectangle(new Point(167, 175), new Point(10, 275));
        leftRectangle.setFillColor(Color.DARK_GRAY);
        gg.add(leftRectangle);
        Rectangle middRectangle = new Rectangle(new Point(400, 175), new Point(10, 275));
        middRectangle.setFillColor(Color.DARK_GRAY);
        gg.add(middRectangle);
        Rectangle righRectangle = new Rectangle(new Point(617, 175), new Point(10, 275));
        righRectangle.setFillColor(Color.DARK_GRAY);
        gg.add(righRectangle);

        return gg;
    }



    public CanvasWindow getCanvas(){
        return board;
    }

    public void addStack1(Disk d){
        stack1.push(d);
    }

    public void addStack2(Disk d){
        stack2.push(d);
    }
    public void addStack3(Disk d){
        stack3.push(d);
    }

    public Disk removeStack1(){
       return stack1.pop();
    }

    public Disk removeStack2(){
       return stack2.pop();
    }

    public Disk removeStack3(){
       return stack3.pop();
    }

    public void printStacks(){
        System.out.println(stack1.toString());
        System.out.println(stack2.toString());
        System.out.println(stack3.toString());

    }

    public void resetStacks(){
        stack1.clear();
        stack2.clear();
        stack3.clear();
        board.removeAll();
        board.add(constructBoard());
        board.draw();
    }
}
