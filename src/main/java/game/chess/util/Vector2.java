package game.chess.util;

/**
 * A custom data type for holding the positions of {@link Tile} objects
 * 
 * @author Pranav Badrinathan
 */
public class Vector2
{
	public int x;
	public int y;
	
	/**
	 * makes a new {@link Vector2} with its {@code x} and {@code y} values set to 0
	 */
	public Vector2() {}
	
	/**
	 * makes a new {@link Vector2} with its {@code x} and {@code y} values set to the values
	 * passed into this constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Vector2(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
}
