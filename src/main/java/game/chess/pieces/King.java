package game.chess.pieces;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;

public class King extends Piece
{
	public King(ChessColor pieceColor)
	{
		super(PieceType.KING, pieceColor);	
	}
}
