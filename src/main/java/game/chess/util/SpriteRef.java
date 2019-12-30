package game.chess.util;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

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
			blackBishop = ImageIO.read(new File(Reference.blackPiecesUrl + "Black Bishop.png"));
			blackKing = ImageIO.read(new File(Reference.blackPiecesUrl + "Black King.png"));
			blackKnight = ImageIO.read(new File(Reference.blackPiecesUrl + "Black Knight.png"));
			blackPawn = ImageIO.read(new File(Reference.blackPiecesUrl + "Black Pawn.png"));
			blackQueen = ImageIO.read(new File(Reference.blackPiecesUrl + "Black Queen.png"));
			blackRook = ImageIO.read(new File(Reference.blackPiecesUrl + "Black Rook.png"));
			
			//Initialize all white pieces' images
			whiteBishop = ImageIO.read(new File(Reference.whitePiecesUrl + "white Bishop.png"));
			whiteKing = ImageIO.read(new File(Reference.whitePiecesUrl + "white King.png"));
			whiteKnight = ImageIO.read(new File(Reference.whitePiecesUrl + "white Knight.png"));
			whitePawn = ImageIO.read(new File(Reference.whitePiecesUrl + "white Pawn.png"));
			whiteQueen = ImageIO.read(new File(Reference.whitePiecesUrl + "white Queen.png"));
			whiteRook = ImageIO.read(new File(Reference.whitePiecesUrl + "white Rook.png"));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
