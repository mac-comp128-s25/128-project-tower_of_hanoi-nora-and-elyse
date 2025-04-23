import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;


import java.awt.Color;
import java.awt.Font;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class GameBoard {
    private int level;
    private CanvasWindow board;
    private GraphicsGroup structure;
    private Deque<Disk> stack1 = new ArrayDeque<Disk>();
    private Deque<Disk> stack2 = new ArrayDeque<Disk>();
    private Deque<Disk> stack3 = new ArrayDeque<Disk>();
    private int[] distancesFromBottom;


    public GameBoard(){
        level = 0;
        board = new CanvasWindow("Tower of Hanoi", 800, 500);
        structure = constructBoard();
        board.add(structure);
        board.draw();
        distancesFromBottom = new int[3];
      //  distancesFromBottom[0] = 430 - (20 * level);
       // System.out.println("dis" + distancesFromBottom[0]);

        distancesFromBottom[1] = 430;
        distancesFromBottom[2] = 430;
        
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
        distancesFromBottom[0] = 430 - (20 * level);
        System.out.println("dis" + distancesFromBottom[0]);
        distancesFromBottom[1] = 430;
        distancesFromBottom[2] = 430;
        stack1.clear();
        stack2.clear();
        stack3.clear();
        board.removeAll();
        board.add(constructBoard());
        board.draw();
    }

    public void setLevel(int l){
        level = l;
        distancesFromBottom[0] = 430 - (20 * level);
        System.out.println("dis" + distancesFromBottom[0]);


    }

    public void moveDisk(Disk d, int newStack, int originalStack){
        double xPos = 0;
        if(newStack ==1){
            xPos = 167  - ((d.getRectangle().getWidth()-10)/2);
        }
        if(newStack == 2){
            xPos = 400 - ((d.getRectangle().getWidth()-10)/2);
        }
        if(newStack ==3){
            xPos = 617 -  ((d.getRectangle().getWidth()-10)/2);
        }

        d.getRectangle().setPosition(new Point(xPos, distancesFromBottom[newStack-1]));
        distancesFromBottom[newStack-1] -= 20;
        distancesFromBottom[originalStack-1] += 20;

        board.draw();
       // Scanner scan = new Scanner(System.in);
       // System.out.println("continue?");
       // String input = scan.nextLine();
    }

    public ArrayList<Deque<Disk>> getStacks(){
        ArrayList<Deque<Disk>> result = new ArrayList<>();
        result.add(stack1);
        result.add(stack2);
        result.add(stack3);
        return result;
    }

    public void winScreen(){
        GraphicsText youWin = new GraphicsText("Level Passed!\n Next level: " + (level++) );
        youWin.setCenter(350, 100);
        youWin.setFont(FontStyle.BOLD, 30);
        board.add(youWin);
        board.draw();
        board.pause(3000);
    }

}
