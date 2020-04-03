package game.chess.pieces;

import game.chess.enums.ChessColor;
import game.chess.enums.PieceType;
import game.chess.gui.BoardHandler;
import game.chess.gui.Tile;

public class Ghost extends Piece
{
	private Piece parent;
	public boolean newGhost = true;
	
	public Ghost(PieceType type, ChessColor pieceColor, Piece parent)
	{
		super(type, pieceColor);
		this.parent = parent;
	}
	
	public void killParentPiece() 
	{
		Tile parentTile = BoardHandler.getTiles(parent)[0];	
		parentTile.piece = null;
		parentTile.drawPieceSprite();
	}
}
