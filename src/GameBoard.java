import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;


import java.awt.Color;
import java.awt.Font;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class GameBoard {
    private String answer;
    private GameManager gm;
    private DiskManager dm;
    private int level;
    private FewestMoveCalculator fmc;
    private CanvasWindow board;
    private GraphicsGroup structure;
    private Deque<Disk> stack1 = new ArrayDeque<Disk>();
    private Deque<Disk> stack2 = new ArrayDeque<Disk>();
    private Deque<Disk> stack3 = new ArrayDeque<Disk>();
    private int[] distancesFromBottom;
    private Button tower1;
    private Button tower2;
    private Button tower3;
    private Boolean button1;
    private Boolean button2;
    private Boolean button3;
    private Button help;
    private int minMoves;
    private boolean solved;


    public GameBoard(GameManager gm){
        level = 0;
        board = new CanvasWindow("Tower of Hanoi", 800, 500);
        structure = constructBoard();
        board.add(structure);
        board.draw();
        fmc = null;
        distancesFromBottom = new int[3];
        distancesFromBottom[1] = 430;
        distancesFromBottom[2] = 430;
        this.gm = gm;
        dm = null;
        //setUpButtons();
        minMoves = 1;
        
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

    //delete evemtually
    public void printStacks(){
        System.out.println(stack1.toString());
        System.out.println(stack2.toString());
        System.out.println(stack3.toString());

    }

    public void resetStacks(){
        distancesFromBottom[0] = 430 - (20 * level);
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
    }

    public ArrayList<Deque<Disk>> getStacks(){
        ArrayList<Deque<Disk>> result = new ArrayList<>();
        result.add(stack1);
        result.add(stack2);
        result.add(stack3);
        return result;
    }

    public void winScreen(){
        GraphicsText youWin = new GraphicsText("Level Passed!\n Next level: " + (level) + "\n   Moves: " + gm.getNumMoves());
        youWin.setCenter(350, 50);
        youWin.setFont(FontStyle.BOLD, 30);
        board.add(youWin);
        board.draw();
        board.pause(3000);
        gm.setNumMoves(0);
    }

    // public String setUpButtons() {
    //     tower1 = new Button("Tower 1");
    //     tower2 = new Button("Tower 2");
    //     tower3 = new Button("Tower 3");
    //     board.add(tower1, 167 -40, 175 - 40);
    //     board.add(tower2,400 -40, 175 - 40);
    //     board.add(tower3,617 -40, 175 - 40);
    //     board.draw();
    //     button1 = false;
    //     button2 = false;
    //     button3 = false;
    //     answer = "";
    //     while(answer.length() < 2){
    //         tower1.onClick(() -> {
    //             System.out.println("clicked 1");
    //             answer = buttonPressed(1);
    //             indicateButton();
    //             button1 = true;
    //         });

    //         tower2.onClick(() -> {
    //             System.out.println("clicked 2");
    //             answer = buttonPressed(2);
    //             indicateButton();
    //             button2 = true;
    //         });

    //         tower3.onClick(() -> {
    //             System.out.println("clicked 3");
    //             answer = buttonPressed(3);
    //             indicateButton();
    //             button3 = true;
    //         });
    //     }
    //     return answer;
    // }

    public void setUpButtons() {
        tower1 = new Button("Tower 1");
        tower2 = new Button("Tower 2");
        tower3 = new Button("Tower 3");
        help = new Button("Help");
        
        board.add(tower1, 127, 135);
        board.add(tower2, 360, 135);
        board.add(tower3, 577, 135);
        board.add(help);
        board.draw();
    
        button1 = false;
        button2 = false;
        button3 = false;
        answer = "";
    
        tower1.onClick(() -> handleButtonClick(1));
        tower2.onClick(() -> handleButtonClick(2));
        tower3.onClick(() -> handleButtonClick(3));
        help.onClick(() -> handleButtonClick(5)); // triggers different handling 
    }
    private void handleButtonClick(int towerNumber) {
        System.out.println("clicked " + towerNumber);
        answer = answer + buttonPressed(towerNumber);
        System.out.println(answer);
        indicateButton();
        if(towerNumber == 5){
            level = level -1;
            nextLevel();
            fmc.moveEffieciently(stack1, 1, 3, 2);
            dm.checkIfDone(getStacks(), this);
            nextLevel();
        }
        if (answer.length() >= 2) {
            gm.shuffleStacks(answer, this);  
    
            if (dm.checkIfDone(getStacks(), this)) {
                System.out.println("You finished the level!");
                solved = true;
                nextLevel(); 
            }

            answer = "";
            setUpButtons();
        }
    }
    private void nextLevel() {
        level++;
        if (level <= 9) {
            if(solved){
                winScreen();
            }
            resetStacks();
            solved = false;
            gm.runRound(level, this);
        } else {
            System.out.println("Game over! You beat all levels!");
            
        }
    }
    

    public String getAnswer(){
        return answer;
    }

    public String buttonPressed(int pressed) {
        String sequence = "0";
        if(button1 && button2) {
            if(pressed == 1){
                sequence = "21";
            }
            else{
                sequence = "12";
            }
            button1 = false;
            button2 = false;
        } 
        else if(button1 && button3) {
            if(pressed == 1){
                sequence = "31";
            }
            else{
                sequence = "13";
            }
            button1 = false;
            button3 = false;
        }

        else if(button3 && button2) {
            if(pressed == 2){
                sequence = "32";
            }
            else{
                sequence = "23";
            }
            button3 = false;
            button2 = false;
        }
        else{
            sequence = "" + pressed;
        }
        // if(!sequence.equals("0")){
        //     gm.shuffleStacks(sequence, this);
        // }

        return sequence;
        // if(dm.checkIfDone(getStacks())){
            
        // }
        
    }

    public void indicateButton() {

    }

    public void setDiskManager(DiskManager dm){
        this.dm = dm;
        fmc = new FewestMoveCalculator(this, gm, dm);
    }

    public int calculateMinMoves() {
        if(level == 1) {
            minMoves = 1;
            return 1;
        }
        minMoves = (int) Math.pow(2, level) - 1;
        return minMoves;
    }

    public void setSolved(boolean b){
        solved = b;
    }

}
