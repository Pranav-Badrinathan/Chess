package game.chess.pieces;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.gui.BoardHandler;
import game.chess.gui.ChessGUI;
import game.chess.gui.Tile;
import game.chess.util.Vector2;

public class King extends Piece
{
	private boolean kingSideCastle;
	private boolean queenSideCastle;

	private Piece rook1;
	private Piece rook2;

	public King(ChessColor pieceColor)
	{
		super(PieceType.KING, pieceColor);
	}

	@Override
	public boolean isValidMove(Tile currentTile, Tile targetTile)
	{
		if (!super.isValidMove(currentTile, targetTile))
			return false;

		Vector2 move = new Vector2((targetTile.position.x - currentTile.position.x),
				(targetTile.position.y - currentTile.position.y));

		// Castling Implementation: if the tile clicked is 2 off on the x axis, then
		// return true based on if the respective variable is true it to true
		if (!this.hasMoved)
		{
			if ((targetTile.position.x - currentTile.position.x) == 2)
			{
				if (currentTile.position.x == 4)
				{
					if (queenSideCastle)
					{
						Tile from = BoardHandler.getTile(new Vector2(8, currentTile.position.y));
						Tile to = BoardHandler.getTile(new Vector2(5, currentTile.position.y));
						
						BoardHandler.movePiece(from.index, to.index, ChessGUI.board);

						this.hasMoved = true;
						return true;
					}
				}
				else if (currentTile.position.x == 5)
				{
					if (kingSideCastle)
					{
						Tile from = BoardHandler.getTile(new Vector2(8, currentTile.position.y));
						Tile to = BoardHandler.getTile(new Vector2(6, currentTile.position.y));
						
						BoardHandler.movePiece(from.index, to.index, ChessGUI.board);

						this.hasMoved = true;
						return true;
					}
				}
			}
			else if ((targetTile.position.x - currentTile.position.x) == -2)
			{
				if (currentTile.position.x == 4)
				{
					if (kingSideCastle)
					{
						Tile from = BoardHandler.getTile(new Vector2(1, currentTile.position.y));
						Tile to = BoardHandler.getTile(new Vector2(3, currentTile.position.y));
						
						BoardHandler.movePiece(from.index, to.index, ChessGUI.board);

						this.hasMoved = true;
						return true;
					}
				}
				else if (currentTile.position.x == 5)
				{
					if (queenSideCastle)
					{
						Tile from = BoardHandler.getTile(new Vector2(1, currentTile.position.y));
						Tile to = BoardHandler.getTile(new Vector2(4, currentTile.position.y));
						
						BoardHandler.movePiece(from.index, to.index, ChessGUI.board);

						this.hasMoved = true;
						return true;
					}
				}
			}
		}

		// If the king is to move in any direction for only 1 then return true
		if (Math.abs(move.x) == 1 && Math.abs(move.y) == 1 || Math.abs(move.x) == 0 && Math.abs(move.y) == 1
				|| Math.abs(move.x) == 1 && Math.abs(move.y) == 0)
		{
			this.hasMoved = true;
			return true;
		}
		return false;
	}

	@Override
	public void onSelect(Tile parentTile)
	{
		// This super call is here for the future, when I will implement highlighting
		// tiles that this piece can move on
		super.onSelect(parentTile);

		rook1 = BoardHandler.getTile(new Vector2(1, parentTile.position.y)).piece;
		rook2 = BoardHandler.getTile(new Vector2(8, parentTile.position.y)).piece;

		if (!this.hasMoved)
		{
			if (rook1 != null && !rook1.hasMoved)
			{
				if (parentTile.position.x == 4)
				{
					if (checkPath(parentTile.position, -1, 1))
					{
						System.out.println("KING SIDE P1");
						kingSideCastle = true;
					}
				}
				else if (parentTile.position.x == 5)
				{
					if (checkPath(parentTile.position, -1, 1))
					{
						System.out.println("QUEEN SIDE P1");
						queenSideCastle = true;
					}
				}
			}

			if (rook2 != null && !rook2.hasMoved)
			{
				if (parentTile.position.x == 4)
				{
					if (checkPath(parentTile.position, 1, 8))
					{
						System.out.println("QUEEN SIDE P2");
						queenSideCastle = true;
					}
				}
				else if (parentTile.position.x == 5)
				{
					if (checkPath(parentTile.position, 1, 8))
					{
						System.out.println("KING SIDE P2");
						kingSideCastle = true;
					}
				}
			}
		}
	}

	private boolean checkPath(Vector2 pos, int dir, int minMax)
	{
		for (int i = pos.x + dir; i != minMax; i += dir)
		{
			Piece p = BoardHandler.getTile(new Vector2(i, pos.y)).piece;
			if (p != null)
				return false;
		}

		return true;
	}
}
