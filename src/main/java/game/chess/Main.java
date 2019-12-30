package game.chess;

import game.chess.gui.ChessGUI;
import game.chess.util.SpriteRef;

public class Main 
{
	public static void main(String[] args) 
	{
		SpriteRef.initImages();
		ChessGUI.createFrame();
	}
}
