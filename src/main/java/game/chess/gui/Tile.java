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

@SuppressWarnings("serial")
public class Tile extends JPanel
{
	private ChessColor tileColor;

	public Vector2 position;

	public Piece piece = null;
	
	public Tile(ChessColor tileColor, Color backgroundColor, Vector2 position)
	{
		this.tileColor = tileColor;
		this.position = position;
		this.setBackground(backgroundColor);
		this.setLayout(new GridBagLayout());
	}

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
			this.add(piece.getSprite(sprite));
		}
	}

	public ChessColor getTileColor()
	{
		return tileColor;
	}
}
