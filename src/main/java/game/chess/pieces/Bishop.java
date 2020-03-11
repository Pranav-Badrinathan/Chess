package game.chess.pieces;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.gui.BoardHandler;
import game.chess.gui.Tile;
import game.chess.util.Vector2;

public class Bishop extends Piece
{
	/**
	 * A child-class of {@link Piece}, and one of the pieces in the game of chess
	 * 
	 * @param pieceColor
	 */
	public Bishop(ChessColor pieceColor)
	{
		super(PieceType.BISHOP, pieceColor);
	}

	@Override
	public boolean isValidMove(Tile currentTile, Tile targetTile)
	{
		if (!super.isValidMove(currentTile, targetTile))
			return false;

		if (!(Math.abs(currentTile.position.x - targetTile.position.x) == Math
				.abs(currentTile.position.y - targetTile.position.y)))
			return false;
		
		Vector2 dir = new Vector2();
		
		if(currentTile.position.x > targetTile.position.x)
			dir.x = -1;
		else
			dir.x = 1;
		
		if(currentTile.position.y > targetTile.position.y)
			dir.y = -1;
		else
			dir.y = 1;
		
		int steps = Math.abs(currentTile.position.x - targetTile.position.x) - 1;
		Vector2 tempPos = currentTile.position;
		
		for (int i = 0; i < steps; i++)
		{
			tempPos = new Vector2(tempPos.x + dir.x, tempPos.y + dir.y);
			if(BoardHandler.getTiles(tempPos)[0].piece != null)
				return false;
		}
		
		this.hasMoved = true;
		return true;
	}
}
