package game.chess.gui;

import java.awt.Color;

import javax.swing.JPanel;

import game.chess.Reference;

@SuppressWarnings("serial")
public class Tile extends JPanel
{
	private Color tileColor;
	public boolean isOccupied;
	
	public Tile(Color tileColor) 
	{
		this.tileColor = tileColor;
		this.setPreferredSize(Reference.tileDimention);
		this.setBackground(this.tileColor);
	}

	public Color getTileColor() 
	{
		return tileColor;
	}
}
