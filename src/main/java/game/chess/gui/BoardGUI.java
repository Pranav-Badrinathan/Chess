package game.chess.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import game.chess.Reference;

@SuppressWarnings("serial")
public class BoardGUI extends JPanel
{
	GridLayout chessBoard = new GridLayout(8, 8);
	
	public BoardGUI() 
	{
		this.setLayout(chessBoard);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(Reference.boardDimention);
		addTiles();
	}
	
	/*
	 * This Method adds Tile objects to the BoardGUI object, horizontally from the left to right
	 * and vertically from the top to the bottom, in a 8x8 grid;
	 */
	private void addTiles() 
	{
		//Black is represented by the false and White by true
		boolean prvTileWasWhite = false;
		
		for (int i = 0; i < 64; i++) 
		{
			if(prvTileWasWhite)
				this.add(new Tile(Color.BLACK));
			else
				this.add(new Tile(Color.WHITE));
			
			/* This here makes sure to not change the color of the tile if it is at the end 
			 * of a horizontal line, as the last Tile in a line and first in the next line are 
			 * the same color.
			 */
			if (i != 7 && i != 15 && i != 23 && i != 31 && i != 39 && i != 47 && i != 55)
				prvTileWasWhite = !prvTileWasWhite;
		}
	}
}
