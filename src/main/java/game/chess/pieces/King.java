package game.chess.pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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
public class King extends Piece
{
	private boolean kingSideCastle;
	private boolean queenSideCastle;

	public boolean isUnderCheck;

	private Tile rook1;
	private Tile rook2;

	/**
	 * Creates an instance of the {@link King} piece
	 * 
	 * @param pieceColor
	 */
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
						Tile to = BoardHandler.getTiles(new Vector2(5, currentTile.position.y))[0];

						BoardHandler.movePiece(rook2.index, to.index, true);

						this.hasMoved = true;
						return true;
					}
				}
				else if (currentTile.position.x == 5)
				{
					if (kingSideCastle)
					{
						Tile to = BoardHandler.getTiles(new Vector2(6, currentTile.position.y))[0];

						BoardHandler.movePiece(rook2.index, to.index, true);

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
						Tile to = BoardHandler.getTiles(new Vector2(3, currentTile.position.y))[0];

						BoardHandler.movePiece(rook1.index, to.index, true);

						this.hasMoved = true;
						return true;
					}
				}
				else if (currentTile.position.x == 5)
				{
					if (queenSideCastle)
					{
						Tile to = BoardHandler.getTiles(new Vector2(4, currentTile.position.y))[0];

						BoardHandler.movePiece(rook1.index, to.index, true);

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

		rook1 = BoardHandler.getTiles(new Vector2(1, parentTile.position.y))[0];
		rook2 = BoardHandler.getTiles(new Vector2(8, parentTile.position.y))[0];

		if (!this.hasMoved && !this.isUnderCheck)
		{
			if (rook1 != null)
			{
				if (!rook1.piece.hasMoved)
				{
					if (parentTile.position.x == 4)
					{
						if (checkPath(parentTile.position, -1, 1)
								&& checkCheckOnPath(BoardHandler.getTilesinCastlePath(parentTile, rook1)))
						{
							System.out.println("KING SIDE P1");
							kingSideCastle = true;
						}
					}
					else if (parentTile.position.x == 5)
					{
						if (checkPath(parentTile.position, -1, 1)
								&& checkCheckOnPath(BoardHandler.getTilesinCastlePath(parentTile, rook1)))
						{
							System.out.println("QUEEN SIDE P1");
							queenSideCastle = true;
						}
					}
				}
			}

			if (rook2 != null)
			{
				if (!rook2.piece.hasMoved)
				{
					if (parentTile.position.x == 4)
					{
						if (checkPath(parentTile.position, 1, 8)
								&& checkCheckOnPath(BoardHandler.getTilesinCastlePath(parentTile, rook2)))
						{
							System.out.println("QUEEN SIDE P2");
							queenSideCastle = true;
						}
					}
					else if (parentTile.position.x == 5)
					{
						if (checkPath(parentTile.position, 1, 8)
								&& checkCheckOnPath(BoardHandler.getTilesinCastlePath(parentTile, rook2)))
						{
							System.out.println("KING SIDE P2");
							kingSideCastle = true;
						}
					}
				}
			}
		}
	}

	private boolean checkPath(Vector2 pos, int dir, int minMax)
	{
		for (int i = pos.x + dir; i != minMax; i += dir)
		{
			Piece p = BoardHandler.getTiles(new Vector2(i, pos.y))[0].piece;
			if (p != null)
				return false;
		}

		return true;
	}

	private boolean checkCheckOnPath(Tile[] tiles)
	{
		for (Tile tile : tiles)
		{
			if (detectCheck(tile.position))
				return false;
		}
		return true;
	}

	public void setCheck()
	{
		isUnderCheck = detectCheck(this);
		System.out.println("Check: " + isUnderCheck + ", Color: " + this.pieceColor);
	}

	public <T> boolean detectCheck(T tile)
	{
		ArrayList<Tile> tiles = new ArrayList<>(Arrays.asList(BoardHandler.getBoardAsTiles()));
		Tile selectedTile = BoardHandler.getTiles(tile)[0];
		
		tiles = new ArrayList<>(tiles.stream().filter(x -> x.piece != null).collect(Collectors.toList()));;
		
		for (Tile t : tiles)
		{
			if (t.piece != null && t.piece.getColor() != this.getColor() && !(t.piece instanceof Ghost) && t.piece.isValidMove(t, selectedTile))
				return true;
		}

		return false;
	}
}
