import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

import edu.macalester.graphics.Point;

public class GameManager {
    DiskManager dm;
    public GameManager(){
        dm = null;
    }

    public boolean runRound(int i, GameBoard gb){
        gb.setLevel(i);
        setBoard(i, gb);
        Scanner scan = new Scanner(System.in);
        while(!dm.checkIfDone(gb.getStacks())){
            System.out.println("Continue? ");
            String answer = scan.nextLine();
            shuffleStacks(answer, gb);
        }
        
        gb.printStacks();
        if(i <9){
          return true;
        }
        else{
            return false;
        }
    }



    public void shuffleStacks(String answer, GameBoard gb){
        while(!checkMove(answer, gb)){
            System.out.println("Invalid move, try again");
            System.out.println("Enter Move: ");
            Scanner scan = new Scanner(System.in);
            answer = scan.nextLine();
        }
        if(answer.substring(0,1).equals("1")){
            if(answer.substring(1).equals("2")){
                Disk tempDisk = gb.removeStack1();
                gb.addStack2(tempDisk);
                gb.moveDisk(tempDisk, 2, 1);
            }
            if(answer.substring(1).equals("3")){
                Disk tempDisk = gb.removeStack1();
                gb.addStack3(tempDisk);
                gb.moveDisk(tempDisk, 3, 1);
            }
        }
        if(answer.substring(0,1).equals("2")){
            if(answer.substring(1).equals("1")){
                Disk tempDisk = gb.removeStack2();
                gb.addStack1(tempDisk);
                gb.moveDisk(tempDisk, 1, 2);
            }
            if(answer.substring(1).equals("3")){
                Disk tempDisk = gb.removeStack2();
                gb.addStack3(tempDisk);
                gb.moveDisk(tempDisk, 3, 2);
            }
        }
        if(answer.substring(0,1).equals("3")){
            if(answer.substring(1).equals("1")){
                Disk tempDisk = gb.removeStack3();
                gb.addStack1(tempDisk);
                gb.moveDisk(tempDisk, 1, 3);
            }
            if(answer.substring(1).equals("2")){
                Disk tempDisk = gb.removeStack3();
                gb.addStack2(tempDisk);
                gb.moveDisk(tempDisk, 2, 3);
            }
        }
    }

    public void setBoard(int i, GameBoard gb){
        dm = new DiskManager(gb.getCanvas());
        Point positionTracker = new Point((172-i*7), (430));
        for(int j = i; j > 0; j--){

            Disk d = new Disk(j*14, positionTracker);
            gb.addStack1(d);
            System.out.println("dimensions" + i*14);
            positionTracker = new Point((172-(j-1)*7), positionTracker.getY()-20);
            dm.addToBoard(d);
            gb.getCanvas().draw();
        }
    }

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

    
}
