package game.chess.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.chess.enums.ChessColor;
import game.chess.pieces.Bishop;
import game.chess.pieces.King;
import game.chess.pieces.Knight;
import game.chess.pieces.Pawn;
import game.chess.pieces.Queen;
import game.chess.pieces.Rook;
import game.chess.util.Reference;
import game.chess.util.Vector2;

public class BoardHandler
{
	private static int to = -1;
	private static int from = -1;

	public static void Initialize(Board board)
	{
		addTiles(board);
		initPieces(ChessColor.BLACK, ChessColor.WHITE, board);
		mouseClickHandler(board);
	}

	/*
	 * This Method adds Tile objects to the BoardGUI object, horizontally from the
	 * left to right and vertically from the top to the bottom, in a 8x8 grid;
	 */
	private static void addTiles(Board currentBoard)
	{
		// Black is represented by the false and White by true
		boolean prvTileWasWhite = false;
		int x = 1;
		int y = 1;

		for (int i = 0; i < 64; i++)
		{
			Tile toAdd;

			if (prvTileWasWhite)
				toAdd = new Tile(ChessColor.BLACK, Color.GRAY, new Vector2(x, y));
			else
				toAdd = new Tile(ChessColor.WHITE, Color.WHITE, new Vector2(x, y));
			/*
			 * Setup the positions: if x is 8 then reset it to 1 and add 1 to y
			 */
			x++;
			if (x == 9)
			{
				x = 1;
				y++;
			}

			currentBoard.add(toAdd);

			/*
			 * This here makes sure to not change the color of the tile if it is at the end
			 * of a horizontal line, as the last Tile in a line and first in the next line
			 * are the same color.
			 */
			if (i != 7 && i != 15 && i != 23 && i != 31 && i != 39 && i != 47 && i != 55)
				prvTileWasWhite = !prvTileWasWhite;
		}
	}

	private static void initPieces(ChessColor player, ChessColor opponent, Board currentBoard)
	{
		Component[] comps = currentBoard.getComponents();
		Tile[] tiles = new Tile[64];
		for (int i = 0; i < comps.length; i++)
		{
			tiles[i] = (Tile) comps[i];
		}

		// Add all the pieces to be used by the opponent
		for (int i = 0; i <= 15; i++)
		{
			if (i <= 7)
			{
				switch (Reference.piecePositions[i])
				{
					case "rook":
						tiles[i].piece = new Rook(opponent);
						break;
					case "knight":
						tiles[i].piece = new Knight(opponent);
						break;
					case "bishop":
						tiles[i].piece = new Bishop(opponent);
						break;
					case "k/q":
					{
						if (tiles[i].getTileColor() == opponent)
							tiles[i].piece = new Queen(opponent);
						else
							tiles[i].piece = new King(opponent);
					}
						break;

					default:
						break;
				}
			} else
				tiles[i].piece = new Pawn(opponent);

			tiles[i].drawPieceSprite();
		}

		// Add all the pieces to be used by the player
		for (int i = 0; i <= 15; i++)
		{
			if (i <= 7)
			{
				switch (Reference.piecePositions[i])
				{
					case "rook":
						tiles[63 - i].piece = new Rook(player);
						break;
					case "knight":
						tiles[63 - i].piece = new Knight(player);
						break;
					case "bishop":
						tiles[63 - i].piece = new Bishop(player);
						break;
					case "k/q":
					{
						if (tiles[63 - i].getTileColor() == player)
							tiles[63 - i].piece = new Queen(player);
						else
							tiles[63 - i].piece = new King(player);
					}
						break;

					default:
						break;
				}
			} else
				tiles[63 - i].piece = new Pawn(player);

			tiles[63 - i].drawPieceSprite();
		}
	}

	private static void mouseClickHandler(Board board)
	{
		Component[] comps = board.getComponents();
		Tile[] tiles = new Tile[64];
		for (int i = 0; i < comps.length; i++)
		{
			tiles[i] = (Tile) comps[i];
		}

		board.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				int x = e.getX();
				int y = e.getY();

				for (int i = 0; i < tiles.length; i++)
				{
					Rectangle tile = tiles[i].getBounds();
					if (tile.x + tile.width > x && tile.x < x && tile.y + tile.height > y && tile.y < y)
						selectTiles(tiles[i], i, board);
				}
			}
		});
	}

	private static void selectTiles(Tile selectedTile, int index, Board board)
	{	
		if (from == -1 && selectedTile.piece != null)
		{
			from = index;
		}
		else if (from != -1 && to == -1)
		{
			to = index;
			movePiece(from, to, board);
			
			from = -1;
			to = -1;
		}

	}

	private static void movePiece(int fromTileindex, int toTileindex, Board board)
	{
		Component[] comps = board.getComponents();
		
		Tile toTile = (Tile) comps[toTileindex];
		Tile fromTile = (Tile) comps[fromTileindex];
		
		toTile.piece = fromTile.piece;
		fromTile.piece = null;
		
		fromTile.drawPieceSprite();
		toTile.drawPieceSprite();
		
		board.revalidate();
		board.repaint();
	}
}
