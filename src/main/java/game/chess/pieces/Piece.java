package game.chess.pieces;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.util.Vector2;

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
	public boolean isValidMove(Vector2 currentPos, Vector2 targetPos)
	{
		//TODO: Implement
		return true;
	}
	
	//Draw Image related to this piece to the screen
	public JLabel DrawImage(Image img)
	{
		return new JLabel(new ImageIcon(img));
	}
}
