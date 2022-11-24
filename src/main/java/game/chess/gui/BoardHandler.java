package game.chess.gui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.pieces.Bishop;
import game.chess.pieces.Ghost;
import game.chess.pieces.King;
import game.chess.pieces.Knight;
import game.chess.pieces.Pawn;
import game.chess.pieces.Piece;
import game.chess.pieces.Queen;
import game.chess.pieces.Rook;
import game.chess.util.Reference;
import game.chess.util.UsefulMethods;
import game.chess.util.Vector2;

public class BoardHandler
{
	private static int to = -1;
	private static int from = -1;
	private static Board board;
	
	/**
	 * Initializes the Board
	 * 
	 * @param board
	 */
	public static void init(Board board)
	{
		BoardHandler.board = board;
		addTiles();
		initPieces(ChessGUI.player1.color, ChessGUI.player2.color);
	}

	/**
	 * Returns a {@link Tile} whose [T] (position, piece, index or PieceType)
	 * matches the given [T]
	 * 
	 * @param filter
	 * @return a {@link Tile} object
	 * @see Vector2
	 * @see Piece
	 * @see PieceType
	 * @author Pranav Badrinathan
	 */
	public static <T> Tile[] getTiles(T filter)
	{
		Tile[] tiles = getBoardAsTiles();
		
		// Needs to be list, cause don't know the size of the list.
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
	 * @return a {@link Tile}[]
	 * @see Tile
	 * @see Component
	 * @author Pranav Badrinathan
	 */
	public static Tile[] getBoardAsTiles()
	{
		return Arrays.stream(board.getComponents()).toArray(Tile[]::new);
	}

	/**
	 * Adds {@link Tile} objects to the {@link Board} object, horizontally from the
	 * left to right and vertically from the top to the bottom, in a 8x8 grid;
	 * 
	 * @param board
	 */
	private static void addTiles()
	{
		int x = 1;
		int y = 1;

		for (int i = 0; i < 64; i++)
		{
			Tile toAdd;

			/*
			 * Makes the pattern on the chess board, while adding in the Tiles. The way it works is: 'x'
			 * 
			 * represents the x position of the Tile and y represents the y position. 
			 * 
			 * 'x' remains the same for the tiles in the same column, while 'y' changes between
			 * even and odd as you go further down the column. So doing (x+y) % 2 gives you
			 * 1 and 0 Alternating. So, it can be used to color the tiles, as it alternates
			 * like a chess board.
			 */
			if ((x + y) % 2 == 0)
				toAdd = new Tile(ChessColor.WHITE, Reference.visibleColor.get(ChessColor.WHITE), new Vector2(x, y), i);
			else
				toAdd = new Tile(ChessColor.BLACK, Reference.visibleColor.get(ChessColor.BLACK), new Vector2(x, y), i);

			// Mouse click listener, allows us to click tiles
			mouseHandler(toAdd, i);
			
			// Setup the positions: if x is 8 then reset it to 1 and add 1 to y
			x++;
			if (x == 9)
			{
				x = 1;
				y++;
			}

			board.add(toAdd);
		}
	}

	/**
	 * Adds the chess pieces to the {@link Board} {@code board} object. Based on the
	 * player and opponent's {@link ChessColor} the player's {@link ChessColor}
	 * pieces will be added to the bottom of the screen
	 * 
	 * @param player1Color
	 * @param player2Color
	 * @param board
	 * @author Pranav Badrinathan
	 * @see PieceType
	 */
	private static void initPieces(ChessColor player1Color, ChessColor player2Color)
	{
		Tile[] tiles = getBoardAsTiles();

		// Add all the pieces to be used by the opponent
		for (int i = 0; i <= 15; i++)
		{
			if (i <= 7)
			{
				switch (Reference.PIECE_POSITIONS[i])
				{
					case "rook":
						tiles[i].piece = new Rook(player2Color);
						break;
					case "knight":
						tiles[i].piece = new Knight(player2Color);
						break;
					case "bishop":
						tiles[i].piece = new Bishop(player2Color);
						break;
					case "k/q":
					{
						if (tiles[i].getTileColor() == player2Color)
							tiles[i].piece = new Queen(player2Color);
						else
							tiles[i].piece = new King(player2Color);
					}
						break;

					default:
						break;
				}
			}
			else
				tiles[i].piece = new Pawn(player2Color);

			tiles[i].drawPieceSprite();
			tiles[i].piece.initialize(tiles[i]);
		}

		// Add all the pieces to be used by the player
		for (int i = 0; i <= 15; i++)
		{
			if (i <= 7)
			{
				switch (Reference.PIECE_POSITIONS[i])
				{
					case "rook":
						tiles[63 - i].piece = new Rook(player1Color);
						break;
					case "knight":
						tiles[63 - i].piece = new Knight(player1Color);
						break;
					case "bishop":
						tiles[63 - i].piece = new Bishop(player1Color);
						break;
					case "k/q":
					{
						if (tiles[63 - i].getTileColor() == player1Color)
							tiles[63 - i].piece = new Queen(player1Color);
						else
							tiles[63 - i].piece = new King(player1Color);
					}
						break;

					default:
						break;
				}
			}
			else
				tiles[63 - i].piece = new Pawn(player1Color);

			tiles[63 - i].drawPieceSprite();
			tiles[63 - i].piece.initialize(tiles[63 - i]);
		}
	}

	/**
	 * Detects when the mouse's left click button is pressed and gets the
	 * {@link Tile} that is clicked and selects it
	 * 
	 * @param board
	 * @author Pranav Badrinathan
	 */
	private static void mouseHandler(Tile t, int j)
	{
		t.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				selectTiles(t, j, false);
			}
			
			public void mouseEntered(MouseEvent e) 
			{
				t.setBackground(UsefulMethods.blend(t.getBackground(), Reference.hoverColor));
			}
			
			public void mouseExited(MouseEvent e) 
			{
				t.resetBackgroundColor();
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
	private static void selectTiles(Tile selectedTile, int index, boolean castle)
	{
		Component[] comps = board.getComponents();

		if (from == -1 && selectedTile.piece != null)
		{
			if (!(board.currentPlayer == selectedTile.piece.getColor()))
				return;

			selectedTile.piece.onSelect(selectedTile);
			from = index;
		}
		else if (from != -1 && to == -1)
		{
			to = index;
			Tile fromTile = (Tile) comps[to];

			movePiece(from, to, true, true);

			Tile[] kings = getTiles(PieceType.KING);

			King king1 = (King) kings[0].piece;
			King king2 = (King) kings[1].piece;

			if (fromTile.piece != null && king1.getColor() == fromTile.piece.getColor())
			{
				if (king1.isUnderCheck)
				{
					movePiece(to, from, false, true);
					System.out.println("ILLEGAL MOVE!");
					
					
				}
			}
			else if (fromTile.piece != null && king2.getColor() == fromTile.piece.getColor())
			{
				if (king2.isUnderCheck)
				{
					movePiece(to, from, false, true);
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
	public static void movePiece(int fromTileIndex, int toTileIndex, boolean validate, boolean a)
	{
		Tile[] comps = getBoardAsTiles();

		Tile toTile = comps[toTileIndex];
		Tile fromTile = comps[fromTileIndex];

		//The Tile is no longer selected as it has been de-selected because of a (in)valid move
		fromTile.isSelected = false;
		fromTile.resetBackgroundColor();
		
		if (validate && !fromTile.piece.isValidMove(fromTile, toTile))
			return;
		
		toTile.piece = fromTile.piece;
		fromTile.piece = null;

		fromTile.drawPieceSprite();

		toTile.piece = checkPromotion(toTile);

		toTile.drawPieceSprite();

		board.revalidate();
		board.repaint();
		setChecks(board);
		
		if (a) board.currentPlayer = getOtherColor(board.currentPlayer);
		killAllGhosts();
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
				&& ((toTile.position.y == 8 && toTile.piece.getColor() == ChessGUI.player2.color)
						|| (toTile.position.y == 1 && toTile.piece.getColor() == ChessGUI.player1.color)))
		{
			Pawn p = (Pawn) toTile.piece;
			return p.getPromotedPiece();
		}

		return toTile.piece;
	}

	/**
	 * Set's the {@link King} piece's {@code isUnderCheck} variable based on the
	 * current board
	 * 
	 * @author Pranav Badrinathan
	 * @param board
	 */
	private static void setChecks(Board board)
	{
		Tile[] kingTiles = getTiles(PieceType.KING);

		King no1 = (King) kingTiles[0].piece;
		King no2 = (King) kingTiles[1].piece;

		no1.setCheck();
		no2.setCheck();
	}

	/**
	 * This method returns all the tiles between a rook and a king. It is designed to be used for checking if
	 * any pieces are in-between the king and the rook, thus voiding attempts to castle, though this check is
	 * done in the {@link King} class
	 * 
	 * @param kingTile
	 * @param rookTile
	 * @return the tiles between a rook and the king
	 */
	public static Tile[] getTilesinCastlePath(Tile kingTile, Tile rookTile)
	{
		ArrayList<Tile> tilesInBetween = new ArrayList<Tile>();
		Vector2 dir = Vector2.direction(kingTile.position, rookTile.position);

		int xPos = kingTile.position.x;

		for (int i = 0; i < 2; i++)
		{
			xPos += dir.x;
			tilesInBetween.add(getTiles(new Vector2(xPos, kingTile.position.y))[0]);
		}
		return tilesInBetween.toArray(new Tile[tilesInBetween.size()]);
	}

	/**
	 * This method is used to delete all the {@link Ghost} pieces on the board after a single round. 
	 */
	private static void killAllGhosts()
	{
		for (Tile tile : getBoardAsTiles())
		{
			if (tile.piece instanceof Ghost)
			{
				Ghost g = (Ghost) tile.piece;

				if (g.newGhost)
					g.newGhost = false;
				else
					tile.piece = null;
			}
		}
	}

	private static ChessColor getOtherColor(ChessColor color) 
	{
		if(color == ChessColor.WHITE)
			return ChessColor.BLACK;
		else
			return ChessColor.WHITE;
	}
}