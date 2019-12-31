package game.chess.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import game.chess.util.Reference;

@SuppressWarnings("serial")
public class Board extends JPanel
{
	GridLayout chessBoard = new GridLayout(8, 8);

	public Board()
	{
		this.setLayout(chessBoard);
		this.setPreferredSize(Reference.boardDimention);
	}
}
