import java.util.Scanner;

import edu.macalester.graphics.Point;

public class GameManager {
    
    public GameManager(){

    }

    public boolean runRound(int i, GameBoard gb){
        setBoard(i, gb);
        Scanner scan = new Scanner(System.in);
        System.out.println("Continue? ");
        String answer = scan.nextLine();
        shuffleStacks(answer, gb);
        gb.printStacks();
        return true;
    }

    public void shuffleStacks(String answer, GameBoard gb){
        if(answer.substring(0,1).equals("1")){
            if(answer.substring(1).equals("2")){
                gb.addStack2(gb.removeStack1());
            }
            if(answer.substring(1).equals("3")){
                gb.addStack3(gb.removeStack1());
            }
        }
        if(answer.substring(0,1).equals("2")){
            if(answer.substring(1).equals("1")){
                gb.addStack1(gb.removeStack2());
            }
            if(answer.substring(1).equals("3")){
                gb.addStack3(gb.removeStack2());
            }
        }
        if(answer.substring(0,1).equals("3")){
            if(answer.substring(1).equals("1")){
                gb.addStack1(gb.removeStack3());
            }
            if(answer.substring(1).equals("2")){
                gb.addStack2(gb.removeStack3());
            }
        }
    }

    public void setBoard(int i, GameBoard gb){
        DiskManager dm = new DiskManager(gb.getCanvas());
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

    public void move(Disk disk, Point newPoint, GameBoard gb){
        disk.setPosition(newPoint);
        gb.getCanvas().draw();
    }

}
