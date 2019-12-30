package game.chess.pieces;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;

public class Queen extends Piece
{
	public Queen(ChessColor pieceColor)
	{
		super(PieceType.QUEEN, pieceColor);
	}
}
