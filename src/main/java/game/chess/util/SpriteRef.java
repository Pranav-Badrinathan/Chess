package game.chess.util;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import game.chess.Main;
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
			System.out.println(Main.class.getResource(Reference.BLACK_PIECES_URL + "Black Bishop.png"));
			blackBishop = ImageIO.read(getStream(Reference.BLACK_PIECES_URL + "Black Bishop.png"));
			blackKing = ImageIO.read(getStream(Reference.BLACK_PIECES_URL + "Black King.png"));
			blackKnight = ImageIO.read(getStream(Reference.BLACK_PIECES_URL + "Black Knight.png"));
			blackPawn = ImageIO.read(getStream(Reference.BLACK_PIECES_URL + "Black Pawn.png"));
			blackQueen = ImageIO.read(getStream(Reference.BLACK_PIECES_URL + "Black Queen.png"));
			blackRook = ImageIO.read(getStream(Reference.BLACK_PIECES_URL + "Black Rook.png"));
			
			//Initialize all white pieces' images
			whiteBishop = ImageIO.read(getStream(Reference.WHITE_PIECES_URL + "White Bishop.png"));
			whiteKing = ImageIO.read(getStream(Reference.WHITE_PIECES_URL + "White King.png"));
			whiteKnight = ImageIO.read(getStream(Reference.WHITE_PIECES_URL + "White Knight.png"));
			whitePawn = ImageIO.read(getStream(Reference.WHITE_PIECES_URL + "White Pawn.png"));
			whiteQueen = ImageIO.read(getStream(Reference.WHITE_PIECES_URL + "White Queen.png"));
			whiteRook = ImageIO.read(getStream(Reference.WHITE_PIECES_URL + "White Rook.png"));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static InputStream getStream(String str)
	{
		return Main.class.getResourceAsStream(str);
	}
}
