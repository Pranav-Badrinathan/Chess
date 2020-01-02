package game.chess.pieces;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.gui.Tile;

public abstract class Piece
{
	//The piece type (King, Rook, Pawn, etc.)
	protected PieceType type;
	protected ChessColor pieceColor;
	
	public Piece(PieceType type, ChessColor pieceColor)
	{
		this.type = type;
		this.pieceColor = pieceColor;
	}
	
	//getter for protected variable 'PieceType type' 
	public PieceType getType()
	{
		return type;
	}
	
	//getter for protected variable 'ChessColor pieceColor'
	public ChessColor getColor()
	{
		return pieceColor;
	}

	//Check if the Move is a valid move
	public boolean isValidMove(Tile currentTile, Tile targetTile)
	{
		// Don't move if the target Tile is the current Tile
		if(currentTile.position == targetTile.position)
			return false;
		
		if(targetTile.piece != null && this.pieceColor == targetTile.piece.pieceColor)
			return false;
		
		return true;
	}
	
	//Draw Image related to this piece to the screen
	public JLabel getSprite(Image img)
	{
		return new JLabel(new ImageIcon(img));
	}
}
