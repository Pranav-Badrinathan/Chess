package game.chess.pieces;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.gui.Tile;

public class Queen extends Piece
{
	public Queen(ChessColor pieceColor)
	{
		super(PieceType.QUEEN, pieceColor);
	}
	
	@Override
	public boolean isValidMove(Tile currentTile, Tile targetTile)
	{
		Rook r = new Rook(pieceColor);
		Bishop b = new Bishop(pieceColor);
		
		if(r.isValidMove(currentTile, targetTile) || b.isValidMove(currentTile, targetTile))
			return true;
		else
			return false;
	}
}
