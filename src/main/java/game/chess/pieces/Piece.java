package game.chess.pieces;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.gui.Tile;

/**
 * Abstract super class that is inherited by all the individual piece classes
 * 
 * @author Pranav Badrinathan
 */
public abstract class Piece
{
	// The piece type (King, Rook, Pawn, etc.)
	protected PieceType type;
	protected ChessColor pieceColor;

	public boolean hasMoved = false;

	public Piece(PieceType type, ChessColor pieceColor)
	{
		this.type = type;
		this.pieceColor = pieceColor;
	}

	/**
	 * Getter for protected variable 'PieceType type'
	 * 
	 * @return the {@link PieceType} of this piece
	 */
	public PieceType getType()
	{
		return type;
	}

	/**
	 * getter for protected variable 'ChessColor pieceColor'
	 * 
	 * @return the {@link ChessColor} of this piece
	 */
	public ChessColor getColor()
	{
		return pieceColor;
	}

	/**
	 * Checks if the movement from {@code currentTile} to {@code targetTile} is a
	 * valid move for this {@link PieceType}
	 * 
	 * @param currentTile
	 * @param targetTile
	 * @return a boolean that reflects if it is a valid move {@code currentTile} to
	 *         {@code targetTile} or if its not
	 */
	public boolean isValidMove(Tile currentTile, Tile targetTile)
	{
		// Don't move if the target Tile is the current Tile
		if (currentTile.position == targetTile.position)
			return false;

		if (targetTile.piece != null && this.pieceColor == targetTile.piece.pieceColor)
			return false;

		return true;
	}

	/**
	 * Draw Image related to this piece to the screen
	 * 
	 * @param img
	 * @return a {@code JLabel} that has its {@code ImageIcon} set to {@code img}
	 */
	public JLabel getSprite(Image img)
	{
		return new JLabel(new ImageIcon(img));
	}

	/**
	 * This method is called when a player chooses a {@link Tile} with a
	 * {@link Piece} on it
	 */
	public void onSelect(Tile parentTile)
	{
	}
}
