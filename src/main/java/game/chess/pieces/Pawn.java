package game.chess.pieces;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;

public class Pawn extends Piece
{
	public Pawn(ChessColor pieceColor)
	{
		super(PieceType.PAWN, pieceColor);
	}
}
