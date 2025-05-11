public class TowerOfHanoi {
    private static int level = 1;
    public TowerOfHanoi(){

    }    

    public static void main(String[] args){
        GameManager gameManager = new GameManager();
        GameBoard gameBoard = new GameBoard(gameManager);
        run(gameManager, gameBoard);
    }

    public static void run(GameManager gm, GameBoard gb){
        gm.runRound(level, gb);

    }

}
