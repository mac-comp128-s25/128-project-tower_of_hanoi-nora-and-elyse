import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;


import java.awt.Color;

public class GameBoard {
    CanvasWindow board;
    GraphicsGroup structure;

    public GameBoard(){
        board = new CanvasWindow("Tower of Hanoi", 800, 500);
        structure = constructBoard();
        board.add(structure);
        board.draw();

        
    }

    private GraphicsGroup constructBoard(){
        GraphicsGroup gg = new GraphicsGroup();
        Rectangle bottomRectangle = new Rectangle(new Point(50, 450), new Point(700, 10));
        bottomRectangle.setFillColor(Color.DARK_GRAY);
        gg.add(bottomRectangle);
        Rectangle leftRectangle = new Rectangle(new Point(167, 100), new Point(10, 350));
        leftRectangle.setFillColor(Color.DARK_GRAY);
        gg.add(leftRectangle);
        Rectangle middRectangle = new Rectangle(new Point(400, 100), new Point(10, 350));
        middRectangle.setFillColor(Color.DARK_GRAY);
        gg.add(middRectangle);
        Rectangle righRectangle = new Rectangle(new Point(617, 100), new Point(10, 350));
        righRectangle.setFillColor(Color.DARK_GRAY);
        gg.add(righRectangle);

        return gg;
    }


}
