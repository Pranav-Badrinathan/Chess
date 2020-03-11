package game.chess.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.pieces.Bishop;
import game.chess.pieces.King;
import game.chess.pieces.Knight;
import game.chess.pieces.Pawn;
import game.chess.pieces.Piece;
import game.chess.pieces.Queen;
import game.chess.pieces.Rook;
import game.chess.util.Reference;
import game.chess.util.Vector2;

public class BoardHandler
{
	private static int to = -1;
	private static int from = -1;

	/**
	 * Initializes the Board
	 * 
	 * @param board
	 */
	public static void Initialize(Board board)
	{
		addTiles(board);
		initPieces(ChessGUI.player.color, ChessGUI.opponent.color, board);
		mouseClickHandler(board);
	}

	/**
	 * Returns a {@link Tile} whose position matches the given position
	 * 
	 * @param pos
	 * @return a {@link Tile} object
	 * @see Vector2
	 * @author Pranav Badrinathan
	 */
	public static <T> Tile[] getTiles(T filter)
	{
		Tile[] tiles = getBoardAsTiles(ChessGUI.board);
		List<Tile> t = new ArrayList<Tile>();

		if (filter instanceof Vector2)
		{
			Vector2 pos = (Vector2) filter;
			t = Arrays.stream(tiles).filter(x -> x.position.x == pos.x && x.position.y == pos.y)
					.collect(Collectors.toList());
		}
		else if (filter instanceof Piece)
		{
			Piece p = (Piece) filter;
			if (p != null)
				t = Arrays.stream(tiles).filter(x -> x.piece != null && x.piece == p).collect(Collectors.toList());
		}
		else if (filter instanceof Integer)
		{
			int p = (int) filter;
			t = Arrays.stream(tiles).filter(x -> x.index == p).collect(Collectors.toList());
		}
		else if (filter instanceof PieceType)
		{
			PieceType p = (PieceType) filter;

			if (p != null)
				t = Arrays.stream(tiles).filter(x -> x.piece != null && x.piece.getType() == p)
						.collect(Collectors.toList());
		}

		for (int i = 0; i < t.size(); i++)
		{
			tiles[i] = (Tile) t.get(i);
		}

		return tiles;
	}

	/**
	 * Returns {@code board}'s child components as tiles
	 * 
	 * @param board
	 * @return a {@link Tile}[]
	 * @see Tile
	 * @see Component
	 * @author Pranav Badrinathan
	 */
	private static Tile[] getBoardAsTiles(Board board)
	{
		Component[] comps = board.getComponents();
		Tile[] t = new Tile[comps.length];

		for (int i = 0; i < comps.length; i++)
		{
			t[i] = (Tile) comps[i];
		}

		return t;
	}

	/**
	 * Adds {@link Tile} objects to the {@link Board} object, horizontally from the
	 * left to right and vertically from the top to the bottom, in a 8x8 grid;
	 * 
	 * @param board
	 */
	private static void addTiles(Board board)
	{
		// Black is represented by the false and White by true
		boolean prvTileWasWhite = false;
		int x = 1;
		int y = 1;

		for (int i = 0; i < 64; i++)
		{
			Tile toAdd;

			if (prvTileWasWhite)
				toAdd = new Tile(ChessColor.BLACK, Color.GRAY, new Vector2(x, y), i);
			else
				toAdd = new Tile(ChessColor.WHITE, Color.WHITE, new Vector2(x, y), i);

			// Setup the positions: if x is 8 then reset it to 1 and add 1 to y
			x++;
			if (x == 9)
			{
				x = 1;
				y++;
			}

			board.add(toAdd);

			/*
			 * This here makes sure to not change the color of the tile if it is at the end
			 * of a horizontal line, as the last Tile in a line and first in the next line
			 * are the same color.
			 */
			if (i != 7 && i != 15 && i != 23 && i != 31 && i != 39 && i != 47 && i != 55)
				prvTileWasWhite = !prvTileWasWhite;
		}
	}

	/**
	 * Adds the chess pieces to the {@link Board} {@code board} object. Based on the
	 * player and opponent's {@link ChessColor} the player's {@link ChessColor}
	 * pieces will be added to the bottom of the screen
	 * 
	 * @param playerColor
	 * @param opponentColor
	 * @param board
	 * @author Pranav Badrinathan
	 * @see PieceType
	 */
	private static void initPieces(ChessColor playerColor, ChessColor opponentColor, Board board)
	{
		Tile[] tiles = getBoardAsTiles(board);

		// Add all the pieces to be used by the opponent
		for (int i = 0; i <= 15; i++)
		{
			if (i <= 7)
			{
				switch (Reference.piecePositions[i])
				{
					case "rook":
						tiles[i].piece = new Rook(opponentColor);
						break;
					case "knight":
						tiles[i].piece = new Knight(opponentColor);
						break;
					case "bishop":
						tiles[i].piece = new Bishop(opponentColor);
						break;
					case "k/q":
					{
						if (tiles[i].getTileColor() == opponentColor)
							tiles[i].piece = new Queen(opponentColor);
						else
							tiles[i].piece = new King(opponentColor);
					}
						break;

					default:
						break;
				}
			}
			else
				tiles[i].piece = new Pawn(opponentColor);

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
						tiles[63 - i].piece = new Rook(playerColor);
						break;
					case "knight":
						tiles[63 - i].piece = new Knight(playerColor);
						break;
					case "bishop":
						tiles[63 - i].piece = new Bishop(playerColor);
						break;
					case "k/q":
					{
						if (tiles[63 - i].getTileColor() == playerColor)
							tiles[63 - i].piece = new Queen(playerColor);
						else
							tiles[63 - i].piece = new King(playerColor);
					}
						break;

					default:
						break;
				}
			}
			else
				tiles[63 - i].piece = new Pawn(playerColor);

			tiles[63 - i].drawPieceSprite();
		}
	}

	/**
	 * Detects when the mouse's left click button is pressed and gets the
	 * {@link Tile} that is clicked and selects it
	 * 
	 * @param board
	 * @author Pranav Badrinathan
	 */
	private static void mouseClickHandler(Board board)
	{
		Tile[] tiles = getBoardAsTiles(board);

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
					{
						selectTiles(tiles[i], i, board, false);
					}
				}
			}
		});
	}

	/**
	 * Selects a {@link Tile} and sets it up for movement of a {@link Piece}
	 * 
	 * @param selectedTile
	 * @param index
	 * @param board
	 */
	private static void selectTiles(Tile selectedTile, int index, Board board, boolean castle)
	{
		Component[] comps = board.getComponents();

		if (from == -1 && selectedTile.piece != null)
		{
			selectedTile.piece.onSelect(selectedTile);
			from = index;
		}
		else if (from != -1 && to == -1)
		{
			to = index;
			Tile fromTile = (Tile) comps[to];

			movePiece(from, to, board);

			Tile[] kings = getTiles(PieceType.KING);

			King king1 = (King) kings[0].piece;
			King king2 = (King) kings[1].piece;

			if (king1.getColor() == fromTile.piece.getColor())
			{
				if (king1.isUnderCheck)
				{
					movePiece(to, from, board);
					System.out.println("ILLEGAL MOVE!");
				}
			}
			else if (king2.getColor() == fromTile.piece.getColor())
			{
				if (king2.isUnderCheck)
				{
					movePiece(to, from, board);
					System.out.println("ILLEGAL MOVE!");
				}
			}
			
			from = -1;
			to = -1;
		}

	}

	/**
	 * Gets the {@link Tile} at {@code fromTileIndex} and {@code toTileIndex} and
	 * moves the piece from the {@link Tile} at {@code fromTileIndex} to the
	 * {@link Tile} at {@code toTileIndex}
	 * 
	 * @param fromTileIndex
	 * @param toTileIndex
	 * @param board
	 */
	public static void movePiece(int fromTileIndex, int toTileIndex, Board board)
	{
		Component[] comps = board.getComponents();

		Tile toTile = (Tile) comps[toTileIndex];
		Tile fromTile = (Tile) comps[fromTileIndex];

		if (!fromTile.piece.isValidMove(fromTile, toTile))
			return;

		toTile.piece = fromTile.piece;
		fromTile.piece = null;

		fromTile.drawPieceSprite();

		toTile.piece = checkPromotion(toTile);

		toTile.drawPieceSprite();

		board.revalidate();
		board.repaint();
		setChecks(board);
	}

	/**
	 * This method first checks if the {@link Tile} {@code toTile}'s piece is a
	 * {@link Pawn}. If it is, then it checks if it is in the correct position for
	 * being promoted based on {@link ChessColor}. If it is, then it is replaced
	 * with a promoted {@link Piece} and returned. If any of these conditions fail,
	 * then it is simply going to return the input value
	 * 
	 * @param toTile
	 * @return Promoted version of {@code toTile.piece}
	 * @author Pranav Badrinathan
	 */
	private static Piece checkPromotion(Tile toTile)
	{
		if (toTile.piece instanceof Pawn
				&& ((toTile.position.y == 8 && toTile.piece.getColor() == ChessGUI.opponent.color)
						|| (toTile.position.y == 1 && toTile.piece.getColor() == ChessGUI.player.color)))
		{
			Pawn p = (Pawn) toTile.piece;
			return p.getPromotedPiece();
		}

		return toTile.piece;
	}

	private static void setChecks(Board board)
	{
		Tile[] kingTiles = getTiles(PieceType.KING);

		King no1 = (King) kingTiles[0].piece;
		King no2 = (King) kingTiles[1].piece;

		no1.setCheck(getBoardAsTiles(board));
		no2.setCheck(getBoardAsTiles(board));
	}
}
