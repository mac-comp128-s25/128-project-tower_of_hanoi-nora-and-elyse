import java.util.Scanner;

public class TowerOfHanoi {
    private static int level = 2;
    public TowerOfHanoi(){

    }    

    public static void main(String[] args){
        GameManager gameManager = new GameManager();
        GameBoard gameBoard = new GameBoard(gameManager);
        run(gameManager, gameBoard);
    }

    public static void run(GameManager gm, GameBoard gb){
        // while(gm.runRound(level, gb)){
        //    // System.out.println("Running " + level);
        //     gb.winScreen();
        //     level++;
        //     gb.resetStacks();
        //     if(level > 9){
        //         break;
        //     }
        // }
        gm.runRound(level, gb);
       // gb.nextLevel();

    }

}
