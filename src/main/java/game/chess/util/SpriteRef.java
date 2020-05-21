package game.chess.util;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import game.chess.gui.Tile;

/**
 * Declares and initializes the Image variables utilized by {@link Tile#drawPieceSprite()}
 * 
 * @author Pranav Badrinathan
 */
public final class SpriteRef
{
	public static BufferedImage blackPawn;
	public static BufferedImage blackRook;
	public static BufferedImage blackKnight;
	public static BufferedImage blackBishop;
	public static BufferedImage blackQueen;
	public static BufferedImage blackKing;

	public static BufferedImage whitePawn;
	public static BufferedImage whiteRook;
	public static BufferedImage whiteKnight;
	public static BufferedImage whiteBishop;
	public static BufferedImage whiteQueen;
	public static BufferedImage whiteKing;

	public static void initImages()
	{
		try
		{
			//Initialize all black pieces' images
			blackBishop = ImageIO.read(new File(Reference.BLACK_PIECES_URL + "Black Bishop.png"));
			blackKing = ImageIO.read(new File(Reference.BLACK_PIECES_URL + "Black King.png"));
			blackKnight = ImageIO.read(new File(Reference.BLACK_PIECES_URL + "Black Knight.png"));
			blackPawn = ImageIO.read(new File(Reference.BLACK_PIECES_URL + "Black Pawn.png"));
			blackQueen = ImageIO.read(new File(Reference.BLACK_PIECES_URL + "Black Queen.png"));
			blackRook = ImageIO.read(new File(Reference.BLACK_PIECES_URL + "Black Rook.png"));
			
			//Initialize all white pieces' images
			whiteBishop = ImageIO.read(new File(Reference.WHITE_PIECES_URL + "white Bishop.png"));
			whiteKing = ImageIO.read(new File(Reference.WHITE_PIECES_URL + "white King.png"));
			whiteKnight = ImageIO.read(new File(Reference.WHITE_PIECES_URL + "white Knight.png"));
			whitePawn = ImageIO.read(new File(Reference.WHITE_PIECES_URL + "white Pawn.png"));
			whiteQueen = ImageIO.read(new File(Reference.WHITE_PIECES_URL + "white Queen.png"));
			whiteRook = ImageIO.read(new File(Reference.WHITE_PIECES_URL + "white Rook.png"));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
