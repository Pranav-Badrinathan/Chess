package game.chess.pieces;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.gui.Tile;

public class King extends Piece
{
	public King(ChessColor pieceColor)
	{
		super(PieceType.KING, pieceColor);
	}

	@Override
	public boolean isValidMove(Tile currentTile, Tile targetTile)
	{
		if (!super.isValidMove(currentTile, targetTile))
			return false;

		// If the king is to move in any direction for only 1 then return true
		if (Math.abs(targetTile.position.x - currentTile.position.x) == 1
				&& Math.abs(targetTile.position.y - currentTile.position.y) == 1
				|| Math.abs(targetTile.position.x - currentTile.position.x) == 0
						&& Math.abs(targetTile.position.y - currentTile.position.y) == 1
				|| Math.abs(targetTile.position.x - currentTile.position.x) == 1
						&& Math.abs(targetTile.position.y - currentTile.position.y) == 0)
			return true;

		return false;
	}
}
