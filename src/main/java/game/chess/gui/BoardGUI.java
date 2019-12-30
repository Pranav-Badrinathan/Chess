package game.chess.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import game.chess.enums.ChessColor;
import game.chess.pieces.Queen;
import game.chess.util.Reference;
import game.chess.util.Vector2;

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
	 * This Method adds Tile objects to the BoardGUI object, horizontally from the
	 * left to right and vertically from the top to the bottom, in a 8x8 grid;
	 */
	private void addTiles()
	{
		// Black is represented by the false and White by true
		boolean prvTileWasWhite = false;
		int x = 1;
		int y = 1;

		for (int i = 0; i < 64; i++)
		{
			Tile toAdd;
			
			if (prvTileWasWhite)
				toAdd = new Tile(ChessColor.BLACK ,Color.GRAY, new Vector2(x, y));
			else
				toAdd = new Tile(ChessColor.WHITE, Color.WHITE, new Vector2(x, y));
			/*
			 * Setup the positions: if x is 8 then reset it to 1 and add 1 to y
			 */
			x++;
			if (x == 9)
			{
				x = 1;
				y++;
			}
			
			//Piece Initialization
			toAdd.piece = new Queen(ChessColor.BLACK);
			toAdd.drawPieceSprite();
			
			this.add(toAdd);

			/*
			 * This here makes sure to not change the color of the tile if it is at the end
			 * of a horizontal line, as the last Tile in a line and first in the next line
			 * are the same color.
			 */
			if (i != 7 && i != 15 && i != 23 && i != 31 && i != 39 && i != 47 && i != 55)
				prvTileWasWhite = !prvTileWasWhite;
		}
	}
	
  /*private void test()
	{
		Component[] comps = this.getComponents();
		Tile[] tiles = new Tile[comps.length];
		for (int i = 0; i < comps.length; i++)
		{
			tiles[i] = (Tile) comps[i];
		}
		
		for (int i = 0; i < tiles.length; i++)
		{
			System.out.println("Tile " + (i + 1) + ": x = " + tiles[i].position.x + "; y = " + tiles[i].position.y + ";");
		}
	}*/
}
