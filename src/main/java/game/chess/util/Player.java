package game.chess.util;

import game.chess.enums.ChessColor;

public class Player
{
	public String name;
	public ChessColor color;
	public int points;
	
	public Player(String name, ChessColor color)
	{
		this.name = name;
		this.color = color;
	}
}
