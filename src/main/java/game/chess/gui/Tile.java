package game.chess.gui;

import java.awt.Color;

import javax.swing.JPanel;

import game.chess.util.Reference;
import game.chess.util.Vector2;

@SuppressWarnings("serial")
public class Tile extends JPanel
{
	private Color tileColor;
	public boolean isOccupied;

	public Vector2 position;
	
	public Tile(Color tileColor, Vector2 position)
	{
		this.tileColor = tileColor;
		this.position = position;
		this.setPreferredSize(Reference.tileDimention);
		this.setBackground(this.tileColor);
	}

	public Color getTileColor()
	{
		return tileColor;
	}
}
