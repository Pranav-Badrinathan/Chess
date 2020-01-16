package game.chess.gui;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JPanel;

import game.chess.enums.ChessColor;
import game.chess.pieces.Piece;
import game.chess.util.Reference;
import game.chess.util.SpriteRef;
import game.chess.util.Vector2;

/**
 * This class represents a single {@code Tile} on the {@link Board}
 * 
 * @author Pranav Badrinathan
 */

@SuppressWarnings("serial")
public class Tile extends JPanel
{
	private ChessColor tileColor;

	public Vector2 position;

	public Piece piece = null;
	
	public int index;
	
	/**
	 * Creates a new instance of the class {@link Tile} with its {@link ChessColor}, {@code background color} and
	 * {@code position} set to the parameters 
	 *  
	 * @param tileColor
	 * @param backgroundColor
	 * @param position
	 */
	public Tile(ChessColor tileColor, Color backgroundColor, Vector2 position, int index)
	{
		this.tileColor = tileColor;
		this.position = position;
		this.index = index;
		this.setBackground(backgroundColor);
		this.setLayout(new GridBagLayout());
	}

	/**
	 * Draws this {@link Tile}'s {@link Piece}'s image onto the screen
	 * @author Pranav Badrinathan
	 */
	public void drawPieceSprite()
	{
		int width = Reference.pieceSpriteDimention.width;
		int height = Reference.pieceSpriteDimention.height;
		
		if (piece != null)
		{
			Image sprite = SpriteRef.blackPawn.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			switch (piece.getColor())
			{
				case BLACK:
				{
					switch (piece.getType())
					{
						case PAWN:
							sprite = SpriteRef.blackPawn.getScaledInstance(width, height, Image.SCALE_SMOOTH);
							break;
						case KING:
							sprite = SpriteRef.blackKing.getScaledInstance(width, height, Image.SCALE_SMOOTH);
							break;
						case QUEEN:
							sprite = SpriteRef.blackQueen.getScaledInstance(width, height, Image.SCALE_SMOOTH);
							break;
						case KNIGHT:
							sprite = SpriteRef.blackKnight.getScaledInstance(width, height, Image.SCALE_SMOOTH);
							break;
						case ROOK:
							sprite = SpriteRef.blackRook.getScaledInstance(width, height, Image.SCALE_SMOOTH);
							break;
						case BISHOP:
							sprite = SpriteRef.blackBishop.getScaledInstance(width, height, Image.SCALE_SMOOTH);
							break;
					}
					
					break;
				}

				case WHITE:
				{
					switch (piece.getType())
					{
						case PAWN:
							sprite = SpriteRef.whitePawn.getScaledInstance(width, height, Image.SCALE_SMOOTH);
							break;
						case KING:
							sprite = SpriteRef.whiteKing.getScaledInstance(width, height, Image.SCALE_SMOOTH);
							break;
						case QUEEN:
							sprite = SpriteRef.whiteQueen.getScaledInstance(width, height, Image.SCALE_SMOOTH);
							break;
						case KNIGHT:
							sprite = SpriteRef.whiteKnight.getScaledInstance(width, height, Image.SCALE_SMOOTH);
							break;
						case ROOK:
							sprite = SpriteRef.whiteRook.getScaledInstance(width, height, Image.SCALE_SMOOTH);
							break;
						case BISHOP:
							sprite = SpriteRef.whiteBishop.getScaledInstance(width, height, Image.SCALE_SMOOTH);
							break;
					}
					
					break;
				}	
			}
			this.removeAll();
			this.add(piece.getSprite(sprite));
		}
		else 
		{
			if(this.getComponentCount() != 0)
			this.remove(getComponent(0));
		}
	}

	/**
	 * A Getter for the field tileColor
	 * 
	 * @return the {@link ChessColor} of this instance of the {@link Tile} class
	 * @author Pranav Badrinathan
	 */
	public ChessColor getTileColor()
	{
		return tileColor;
	}
}
