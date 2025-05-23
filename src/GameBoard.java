import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * @author Nora Betry and Elyse Quigley
 * Creates the blank game board, has methods for physically moving disks on screen, and handles button creation and operations
 */
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
        board.setBackground(new Color(238,214,208));
        structure = constructBoard();
        board.add(structure);
        board.draw();
        fmc = null;
        distancesFromBottom = new int[3];
        distancesFromBottom[1] = 430;
        distancesFromBottom[2] = 430;
        this.gm = gm;
        dm = null;
        minMoves = 1;
        
    }

    /**
     * Creates the graphic objects for the blank board and adds them to a graphics group 
     * @return      Returns the graphics group created. 
     */
    private GraphicsGroup constructBoard(){
        GraphicsGroup gg = new GraphicsGroup();
        Rectangle bottomRectangle = new Rectangle(new Point(50, 450), new Point(700, 10));
        bottomRectangle.setFillColor(Color.DARK_GRAY);
        gg.add(bottomRectangle);
        Rectangle leftRectangle = new Rectangle(new Point(167, 200), new Point(10, 250));
        leftRectangle.setFillColor(Color.DARK_GRAY);
        gg.add(leftRectangle);
        Rectangle middRectangle = new Rectangle(new Point(400, 200), new Point(10, 250));
        middRectangle.setFillColor(Color.DARK_GRAY);
        gg.add(middRectangle);
        Rectangle righRectangle = new Rectangle(new Point(617, 200), new Point(10, 250));
        righRectangle.setFillColor(Color.DARK_GRAY);
        gg.add(righRectangle);

        return gg;
    }



    public CanvasWindow getCanvas(){
        return board;
    }

    /**
     * Adds specified disk object to the first tower stack.
     */
    public void addStack1(Disk d){
        stack1.push(d);
    }

    /**
     * Adds specified disk object to the second tower stack.
     */
    public void addStack2(Disk d){
        stack2.push(d);
    }

    /**
     * Adds specified disk object to the third tower stack.
     */
    public void addStack3(Disk d){
        stack3.push(d);
    }

    /**
     * Removes the top disk from the first tower stack and returns it. 
     */
    public Disk removeStack1(){
       return stack1.pop();
    }

    /**
     * Removes the top disk from the second tower stack and returns it. 
     */
    public Disk removeStack2(){
       return stack2.pop();
    }

    /**
     * Removes the top disk from the third tower stack and returns it. 
     */
    public Disk removeStack3(){
       return stack3.pop();
    }

    /**
     * Empties all the stacks, including removing the graphics objects from the canvas and resetting the distancesFromBottom array. 
     */
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

    /**
     * Sets the level and updates the distancesFromBottom array accordingly. 
     * @param l
     */
    public void setLevel(int l){
        level = l;
        distancesFromBottom[0] = 430 - (20 * level);


    }

    /**
     * Physically moves the rectangle object by finding the new position and adding an animation to the queue using that. 
     * Updates the distancesFromBottom array. 
     * @param d                   The disk being moved
     * @param newStack            The stack the disk is being moved to 
     * @param originalStack       The stack the disk is coming from
     */
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
        Point startPoint = d.getRectangle().getPosition();
        Point endPoint = new Point(xPos, distancesFromBottom[newStack-1]);
        gm.addAnimation(new Animate(startPoint, endPoint, d.getRectangle(), gm, dm, this));
        distancesFromBottom[newStack-1] -= 20;
        distancesFromBottom[originalStack-1] += 20;

        board.draw();
    }

    /**
     * Returns an ArrayList of the three stacks
     */
    public ArrayList<Deque<Disk>> getStacks(){
        ArrayList<Deque<Disk>> result = new ArrayList<>();
        result.add(stack1);
        result.add(stack2);
        result.add(stack3);
        return result;
    }

    /**
     * Puts winning text on the canvas.
     */
    public void winScreen(){
        GraphicsText youWin = new GraphicsText("Level Passed!\n Next level: " + (level) + "\n   Moves: " + gm.getNumMoves());
        youWin.setCenter(350, 50);
        youWin.setFont(FontStyle.BOLD, 30);
        board.add(youWin);
        board.draw();
        board.pause(3000);
        gm.setNumMoves(0);
    }


    /**
     * Constructs the buttons, puts them on the canvas, and calls handleButtonClick when clicked. 
     */
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
        help.onClick(() -> handleButtonClick(5)); 
    }

    /**
     * Calls shuffleStacks based on the sequence of buttons pressed, or calls the moveEfficiently method if the help button is pressed. 
     * @param towerNumber
     */
    private void handleButtonClick(int towerNumber) {
        answer = answer + buttonPressed(towerNumber);
        if(towerNumber == 5){
            level = level -1;
            gm.setNumMoves(0);
            nextLevel();
            fmc.moveEfficiently(stack1, 1, 3, 2);
            fmc.setupMoveExecution();
            dm.checkIfDone(getStacks(), this);
        }
        if (answer.length() >= 2) {
            gm.shuffleStacks(answer, this);  
            
            if (dm.checkIfDone(getStacks(), this)) {
                solved = true;
            }

            answer = "";
            setUpButtons();
        }
    }

    /**
     * Resets the board, calls the win screen method, and moves to the next level. 
     */
    public void nextLevel() {
        level++;
        if (level <= 9) {
            if(solved){
                winScreen();  
            }
            resetStacks();
            solved = false;
            setLevel(level);
            gm.setBoard(level, this);
            setUpButtons();
       
        gm.updateConstantText(this);
        }
    }
    

    public String getAnswer(){
        return answer;
    }

    /**
     * Returns a string based on the buttons pressed and the sequence in which they were pressed. 
     */
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

        return sequence;

    }

    public void setDiskManager(DiskManager dm){
        this.dm = dm;
        fmc = new FewestMoveCalculator(this, gm, dm);
    }

    /**
     * Returns the minimum amount of moves a level can be solved in
     */
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

    public int[] getDistancesFromBottom(){
        return distancesFromBottom;
    }

    /**
     * Updates the distancesFromBottom array according to which stack was added to and removed from
     */
    public void updateDistancesFromBottom(int newStack, int originalStack){
        distancesFromBottom[newStack-1] -= 20;
        distancesFromBottom[originalStack-1] += 20;
    }

}
