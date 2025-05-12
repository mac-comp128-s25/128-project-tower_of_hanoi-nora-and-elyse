/**
 * @author Nora Betry and Elyse Quigley
 * Holds the main method, run method, and keeps track of level.
 */
public class TowerOfHanoi {
    private static int level = 5;
    public TowerOfHanoi(){

    }    

    public static void main(String[] args){
        GameManager gameManager = new GameManager();
        GameBoard gameBoard = new GameBoard(gameManager);
        run(gameManager, gameBoard);
    }

    /**
     * Calls the runRound method given the current level
     */
    public static void run(GameManager gm, GameBoard gb){
        gm.runRound(level, gb);

    }

}
