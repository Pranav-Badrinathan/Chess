package game.chess.pieces;

import javax.swing.JOptionPane;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.gui.BoardHandler;
import game.chess.gui.Tile;
import game.chess.util.Vector2;

/**
 * A child-class of {@link Piece}, and one of the pieces in the game of chess
 * 
 * @param pieceColor
 */
public class Pawn extends Piece
{
	private int dir = 0;

	/**
	 * Creates an instance of the {@link Pawn} piece
	 * 
	 * @param pieceColor
	 */
	public Pawn(ChessColor pieceColor)
	{
		super(PieceType.PAWN, pieceColor);
	}

	@Override
	public void initialize(Tile parentTile)
	{
		if (BoardHandler.getTiles(new Vector2(parentTile.position.x, parentTile.position.y - 1))[0].piece == null
				&& BoardHandler
						.getTiles(new Vector2(parentTile.position.x, parentTile.position.y + 1))[0].piece != null)
			dir = -1;
		else
			dir = 1;
	}

	@Override
	public boolean isValidMove(Tile currentTile, Tile targetTile)
	{
		if (super.isValidMove(currentTile, targetTile))
		{
			int steps = Math.abs(currentTile.position.y - targetTile.position.y);
			Vector2 tempPos = currentTile.position;

			if ((targetTile.position.x == currentTile.position.x - 1
					|| targetTile.position.x == currentTile.position.x + 1)
					&& (targetTile.position.y == currentTile.position.y + dir))
			{
				if (targetTile.piece != null)
				{
					if(targetTile.piece instanceof Ghost)
					{
						Ghost piece = (Ghost) targetTile.piece;
						piece.killParentPiece();
					}
					
					this.hasMoved = true;
					return true;
				}
			}

			if (!this.hasMoved)
			{
				if (!(currentTile.position.x == targetTile.position.x
						&& (targetTile.position.y == currentTile.position.y + dir
								|| targetTile.position.y == currentTile.position.y + (dir * 2))))
				{
					return false;
				}
				else if (targetTile.position.y == currentTile.position.y + (dir * 2))
				{
					Tile midTile = BoardHandler
							.getTiles(new Vector2(currentTile.position.x, currentTile.position.y + dir))[0];
					
					if(midTile.piece == null)
					{
						midTile.piece = new Ghost(this.type, this.pieceColor, this);
						System.out.println("GHOST MADE!!" + " " + midTile.position.x + " ," + midTile.position.y);
					}
				}

			}
			else
			{
				if (!(currentTile.position.x == targetTile.position.x
						&& targetTile.position.y == currentTile.position.y + dir))
				{
					return false;
				}
			}

			for (int i = 0; i < steps; i++)
			{
				tempPos = new Vector2(tempPos.x, tempPos.y + dir);

				if (tempPos.y > 8)
					tempPos.y = 8;
				else if (tempPos.y < 1)
					tempPos.y = 1;

				Tile t = BoardHandler.getTiles(tempPos)[0];

				if (t.piece != null && !(t.piece instanceof Ghost))
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
