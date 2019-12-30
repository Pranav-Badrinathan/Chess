package game.chess.pieces;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;

public class Rook extends Piece
{
	public Rook(ChessColor pieceColor)
	{
		super(PieceType.ROOK, pieceColor);
	}
}
