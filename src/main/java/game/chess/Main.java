package game.chess;

import game.chess.gui.ChessGUI;
import game.chess.util.SpriteRef;

/**
 * The class that contains the <br> {@code public static void main(String[] args)}<br> method
 * 
 * @author Pranav Badrinathan
 */
public class Main 
{
	/**
	 * Entry Point of the application. It first initializes the images used by the pieces by calling {@link SpriteRef#initImages()} 
	 * and then draws the applcation to the screen by calling {@link ChessGUI#createFrame()}
	 * 
	 * @author Pranav Badrinathan
	 */
	public static void main(String[] args) 
	{
		SpriteRef.initImages();
		ChessGUI.createFrame();
	}
}
