package game.chess.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import game.chess.enums.ChessColor;
import game.chess.util.Reference;

/**
 * The class which represents the chess board. All the {@link Tile} objects are added to a static 
 * instance of this class.
 * 
 * @author Pranav Badrinathan
 */

@SuppressWarnings("serial")
public class Board extends JPanel
{
	GridLayout chessBoard = new GridLayout(8, 8);

	public ChessColor currentPlayer = ChessColor.WHITE;
	
	/**
	 * Creates a new instance of the {@link Board} class
	 */
	public Board()
	{
		this.setLayout(chessBoard);
		this.setPreferredSize(Reference.boardDimention);
	}
}
