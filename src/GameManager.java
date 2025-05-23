import java.awt.Color;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

/**
 * @author Nora Betry and Elyse Quigley
 * Handles animate call, has the movement manager, sets up board, and checks move validity. 
 */
public class GameManager {
    private DiskManager dm;
    private int numMoves;
    private Queue<Animate> animations;


    public GameManager(){
        dm = null;
        numMoves = 0;
        animations = new LinkedList<Animate>();
    }

    /**
     * Runs a round of the game, and animates according to the animations queue 
     * @param i         The level 
     */
    public boolean runRound(int i, GameBoard gb){
        gb.setLevel(i);
        setBoard(i, gb);
        
        gb.getCanvas().animate( (dt) -> {
            Iterator<Animate> iter = animations.iterator();
            while(iter.hasNext()){
                Animate animation = iter.next();
                if(animation.check()){
                    iter.remove();
                }

                //animation.update(3); //<- Don't delete: alternate animation path
                animation.updateOver(5);
            }

        });

        gb.setUpButtons();
        
        updateConstantText(gb);
        if(i <9){
          return true;
        }
        else{
            return false;
        }
    }

    public void addAnimation(Animate a){
        animations.add(a);
    }

    /**
     * Moves a disk from stack to stack based on the answer given, ie "12" would move the top disk from tower 1 to 2
     */
    public void shuffleStacks(String answer, GameBoard gb){
        Disk tempDisk = null;
        if(answer.length()>1){
            if(!checkMove(answer, gb)){
                GraphicsText invalid = new GraphicsText("Invalid move, try again!");
                invalid.setCenter(300, 50);
                invalid.setFont(FontStyle.BOLD, 30);
                gb.getCanvas().add(invalid);
                gb.getCanvas().draw();
               
                gb.getCanvas().pause(3000);
                coverInvalid(gb);
                
               
            }

       

        else if(answer.substring(0,1).equals("1")){
            if(answer.substring(1).equals("2")){
                tempDisk = gb.removeStack1();
                gb.addStack2(tempDisk);
                gb.moveDisk(tempDisk, 2, 1);
            }
            if(answer.substring(1).equals("3")){
                tempDisk = gb.removeStack1();
                gb.addStack3(tempDisk);
                gb.moveDisk(tempDisk, 3, 1);
            }
            numMoves++;
        }
        else if(answer.substring(0,1).equals("2")){
            if(answer.substring(1).equals("1")){
                tempDisk = gb.removeStack2();
                gb.addStack1(tempDisk);
                gb.moveDisk(tempDisk, 1, 2);
            }
            if(answer.substring(1).equals("3")){
                tempDisk = gb.removeStack2();
                gb.addStack3(tempDisk);
                gb.moveDisk(tempDisk, 3, 2);
            }
            numMoves++;
        }
        else if(answer.substring(0,1).equals("3")){
            if(answer.substring(1).equals("1")){
                tempDisk = gb.removeStack3();
                gb.addStack1(tempDisk);
                gb.moveDisk(tempDisk, 1, 3);
            }
            if(answer.substring(1).equals("2")){
                tempDisk = gb.removeStack3();
                gb.addStack2(tempDisk);
                gb.moveDisk(tempDisk, 2, 3);
            }
            numMoves++;
        }

        updateConstantText(gb);
        

    }
    }

    /**
     * Adds the initial disks to the canvas based on the level. 
     * @param i       The level
     */
    public void setBoard(int i, GameBoard gb){
        dm = new DiskManager(gb.getCanvas());
        gb.setDiskManager(dm);
        Point positionTracker = new Point((172-i*7), (430));
        for(int j = i; j > 0; j--){

            Disk d = new Disk(j*14, positionTracker);
            gb.addStack1(d);
            positionTracker = new Point((172-(j-1)*7), positionTracker.getY()-20);
            dm.addToBoard(d);
            gb.getCanvas().draw();
        }
    }


    /**
     * Checks if the move is valid, meaning it is not moving from an empty stack or moving a large disk on top of a smaller one. 
     * @return       true if the move is valid
     */
    public boolean checkMove(String answer, GameBoard gb) {
        int num1 =  Integer.parseInt(answer.substring(0,1)) -1;
        int num2 = Integer.parseInt(answer.substring(1)) -1;
        
        ArrayList<Deque<Disk>> stacks = gb.getStacks();
        if(stacks.get(num1).isEmpty()){
            return false;
        }
        Double width1 = stacks.get(num1).peek().getRectangle().getWidth();
        Double width2;
        if(!stacks.get(num2).isEmpty()){
             width2 = stacks.get(num2).peek().getRectangle().getWidth();
        }
        else{
            width2 = 1000.0;
        }
        if(width1 > width2){
            return false;
        }
        return true;
    }

    public void setNumMoves(int nm) {
        numMoves = nm;
    }

    public int getNumMoves() {
        return numMoves;
    }

    /**
     * Updates the user's number of moves and the minimum amount of moves text. 
     */
    public void updateConstantText(GameBoard gb) {
        coverMoves(gb);
        GraphicsText constantText = new GraphicsText("Moves: " + numMoves + "   Minimum moves: " + gb.calculateMinMoves());
        constantText.setCenter(633, 480);
        constantText.setFont(FontStyle.PLAIN, 15);
        gb.getCanvas().add(constantText);
        gb.getCanvas().draw();
    }

    /**
     * Conceals the previous text on the canvas by covering it.
     */
    public void coverMoves(GameBoard gb){
        Rectangle cover = new Rectangle(500, 470, 270, 25);
        cover.setFillColor(new Color(238,214,208));
        cover.setFilled(true);
        cover.setStrokeColor(new Color(238,214,208));
        cover.setStroked(true);
        gb.getCanvas().add(cover);
        gb.getCanvas().draw();
    }

    /**
     * Conceals the previous text on the canvas by covering it.
     */
    public void coverInvalid(GameBoard gb){
        Rectangle cover = new Rectangle(215, 15, 400, 50);
        cover.setFillColor(new Color(238,214,208));
        cover.setFilled(true);
        cover.setStrokeColor(new Color(238,214,208));
        cover.setStroked(true);
        gb.getCanvas().add(cover);
        gb.getCanvas().draw();
    }

    public Queue<Animate> getAnimates() {
        return animations;
    }
    
}
