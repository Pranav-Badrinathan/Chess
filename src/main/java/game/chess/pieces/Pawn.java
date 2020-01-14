package game.chess.pieces;

import javax.swing.JOptionPane;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.gui.BoardHandler;
import game.chess.gui.ChessGUI;
import game.chess.gui.Tile;
import game.chess.util.Vector2;

public class Pawn extends Piece
{
	/**
	 * A child-class of {@link Piece}, and one of the pieces in the game of chess
	 * 
	 * @param pieceColor
	 */
	public Pawn(ChessColor pieceColor)
	{
		super(PieceType.PAWN, pieceColor);
	}

	@Override
	public boolean isValidMove(Tile currentTile, Tile targetTile)
	{
		if (super.isValidMove(currentTile, targetTile))
		{
			int steps = Math.abs(currentTile.position.y - targetTile.position.y);
			Vector2 tempPos = currentTile.position;
			int dir = 0;

			if (pieceColor == ChessGUI.player.color)
			{
				dir = -1;

				if ((targetTile.position.x == currentTile.position.x - 1
						|| targetTile.position.x == currentTile.position.x + 1)
						&& (targetTile.position.y == currentTile.position.y - 1))
				{
					if (targetTile.piece != null)
						return true;
				}

				if (!this.hasMoved)
				{
					if (!(currentTile.position.x == targetTile.position.x
							&& (targetTile.position.y == currentTile.position.y - 1
									|| targetTile.position.y == currentTile.position.y - 2)))
					{
						return false;
					}
				} else
				{
					if (!(currentTile.position.x == targetTile.position.x
							&& targetTile.position.y == currentTile.position.y - 1))
					{
						return false;
					}
				}
			} else if (pieceColor == ChessGUI.opponent.color)
			{
				dir = 1;

				if ((targetTile.position.x == currentTile.position.x - 1
						|| targetTile.position.x == currentTile.position.x + 1)
						&& (targetTile.position.y == currentTile.position.y + 1))
				{
					if (targetTile.piece != null)
						return true;
				}

				if (!this.hasMoved)
				{
					if (!(currentTile.position.x == targetTile.position.x
							&& (targetTile.position.y == currentTile.position.y + 1
									|| targetTile.position.y == currentTile.position.y + 2)))
					{
						return false;
					}
				} else
				{
					if (!(currentTile.position.x == targetTile.position.x
							&& targetTile.position.y == currentTile.position.y + 1))
					{
						return false;
					}
				}
			}

			for (int i = 0; i < steps; i++)
			{
				tempPos = new Vector2(tempPos.x, tempPos.y + dir);

				if (tempPos.y > 8)
					tempPos.y = 8;
				else if (tempPos.y < 1)
					tempPos.y = 1;

				Tile t = BoardHandler.getTile(tempPos);

				if (t.piece != null)
					return false;
			}

			this.hasMoved = true;

			return true;
		}

		return false;
	}

	public Piece getPromotedPiece()
	{
		String[] options = { "Queen", "Knight", "Rook", "Bishop" };
		switch (JOptionPane.showOptionDialog(null, "", "Pawn Promotion", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]))
		{
			case 0:
				return new Queen(pieceColor);
			case 1:
				return new Knight(pieceColor);
			case 2:
				return new Rook(pieceColor);
			case 3:
				return new Bishop(pieceColor);
		}

		return null;
	}
}
