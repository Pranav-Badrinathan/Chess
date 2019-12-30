package game.chess.pieces;

import game.chess.enums.PieceType;
import game.chess.util.Vector2;

public class Piece
{
	//The piece type (King, Rook, Pawn, etc.)
	protected PieceType type;
	
	//getter for protected variable 'PieceType type' 
	public PieceType getType()
	{
		return type;
	}
	
	//Overridden by children classes to use to move the piece
	public void Move() {}

	//Check if the Move is a valid move
	public boolean isValidMove(Vector2 currentPos, Vector2 targetPos)
	{
		//TODO: Implement
		return true;
	}
}
