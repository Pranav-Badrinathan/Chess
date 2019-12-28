package game.chess.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardGUI extends JPanel
{
	GridLayout chessBoard = new GridLayout(8, 8);
	Dimension boardDims = new Dimension(500, 500);
	
	public BoardGUI() 
	{
		this.setLayout(chessBoard);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(boardDims);
	}
}
