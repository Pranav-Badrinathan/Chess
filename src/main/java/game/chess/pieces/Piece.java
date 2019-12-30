package game.chess.pieces;

import game.chess.enums.PieceType;
import game.chess.util.Vector2;

public abstract class Piece
{
	//The piece type (King, Rook, Pawn, etc.)
	protected PieceType type;
	
	public Piece(PieceType type)
	{
		this.type = type;
	}
	
	//getter for protected variable 'PieceType type' 
	public PieceType getType()
	{
		return type;
	}

	//Check if the Move is a valid move
	public boolean isValidMove(Vector2 currentPos, Vector2 targetPos)
	{
		//TODO: Implement
		return true;
	}
}
