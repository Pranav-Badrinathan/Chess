package game.chess.pieces;

import game.chess.gui.BoardHandler;
import game.chess.gui.Tile;

/**
 * A child-class of {@link Piece}. It is instantiated when a {@link Pawn} moves two paces in its first turn.
 * It is instantiated in the spot where the {@link Pawn} would have been if it had moved one space.
 * <p> 
 * If the {@code killParentPiece()} method is called before this piece is killed, then it successfully
 * enforces "En passant" by killing the Pawn that moved 2 paces.
 * 
 * @param pieceColor
 */
public class Ghost extends Piece
{
	private Piece parent;
	public boolean newGhost = true;
	
	/**
	 * Creates an instance of the {@link Ghost} piece
	 * 
	 * @param pieceColor
	 */
	public Ghost(Piece parent)
	{
		super(parent.type, parent.pieceColor);
		this.parent = parent;
	}
	
	/**
	 * Kills the {@code parent} {@link Piece}. This method is mainly used to enforce "En passant"
	 */
	public void killParentPiece() 
	{
		Tile parentTile = BoardHandler.getTiles(parent)[0];	
		parentTile.piece = null;
		parentTile.drawPieceSprite();
	}
}
