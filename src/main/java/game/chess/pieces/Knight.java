package game.chess.pieces;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.gui.Tile;
import game.chess.util.Vector2;

public class Knight extends Piece
{
	/**
	 * A child-class of {@link Piece}, and one of the pieces in the game of chess
	 * 
	 * @param pieceColor
	 */
	public Knight(ChessColor pieceColor)
	{
		super(PieceType.KNIGHT, pieceColor);
	}

	@Override
	public boolean isValidMove(Tile currentTile, Tile targetTile)
	{
		if (super.isValidMove(currentTile, targetTile))
		{
			Vector2[] possiblePositions = new Vector2[8];
			Vector2 currentPos = currentTile.position;

			// hardcode the positions
			possiblePositions[0] = new Vector2(currentPos.x + 1, currentPos.y + 2);
			possiblePositions[1] = new Vector2(currentPos.x + 1, currentPos.y - 2);
			possiblePositions[2] = new Vector2(currentPos.x - 1, currentPos.y + 2);
			possiblePositions[3] = new Vector2(currentPos.x - 1, currentPos.y - 2);
			possiblePositions[4] = new Vector2(currentPos.x + 2, currentPos.y + 1);
			possiblePositions[5] = new Vector2(currentPos.x + 2, currentPos.y - 1);
			possiblePositions[6] = new Vector2(currentPos.x - 2, currentPos.y + 1);
			possiblePositions[7] = new Vector2(currentPos.x - 2, currentPos.y - 1);

			for (int i = 0; i < possiblePositions.length; i++)
			{
				if (possiblePositions[i].x == targetTile.position.x && possiblePositions[i].y == targetTile.position.y)
				{
					this.hasMoved = true;
					return true;
				}
			}
		}

		return false;
	}
}
