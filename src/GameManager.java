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

    public boolean checkMove() {
        return false;
    }

    // public void move(Disk disk, Point newPoint, GameBoard gb){
    //     disk.setPosition(newPoint);
    //     gb.getCanvas().draw();
    // }

  
}
