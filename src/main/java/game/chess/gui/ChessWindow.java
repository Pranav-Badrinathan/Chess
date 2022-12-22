package game.chess.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ChessWindow
{
	// Constants
	private final int startWidth = 960;
	private final int startHeight = 540;
	
	private GridPane chessboard;
	private NumberBinding curBoardSize;
	
	public void createWindow(Stage primaryStage)
	{
		primaryStage.setTitle("Chess");
		
		// Set the Minimum window size. Change to possibly scale off window size
		primaryStage.setMinHeight(400);
		primaryStage.setMinWidth(600);
		
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: #E0E0E0;");
		
		// Create the chessboard and set it's minimum size to something small so it's resizable
		chessboard = new GridPane();
		chessboard.setMinSize(0, 0);
		
		// Set alignment to Center so it floats in the center of the window.
		chessboard.setAlignment(Pos.CENTER);
		
		// Get the board size as a binding that changes as the size of the board changes. Used to calculate
		// the individual tile's size.
		curBoardSize = Bindings.min(chessboard.heightProperty(), chessboard.widthProperty());
		Rectangle[][] tiles = new Rectangle[8][8];
		initTiles(tiles);
		
		root.setCenter(chessboard);
		
		primaryStage.setScene(new Scene(root, startWidth, startHeight));
		primaryStage.show();
	}
	
	/**
	 * Initialize the chess tiles as rectangles and add them to the {@code chessboard}.
	 * @param t
	 */
	private void initTiles(Rectangle[][] t)
	{
		for (int x = 0; x < t.length; x++)
		{
			for (int y = 0; y < t[0].length; y++) 
			{
				// Initialize the rectangle with a starting width and height.
				t[x][y] = new Rectangle(startHeight/8, startHeight/8);
				if ((x + y) % 2 == 0) t[x][y].setFill(Color.WHITE);
				else t[x][y].setFill(Color.GRAY);
				
				t[x][y].widthProperty().bind(curBoardSize.divide(8));
				t[x][y].heightProperty().bind(curBoardSize.divide(8));

				
				chessboard.add(t[x][y], x, y);
			}
		}
	}
}
