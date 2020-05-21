package game.chess.util;

import java.awt.Color;
import java.awt.Dimension;

/**
 * A class that stores all the 'Global', or all the {@code static final} variables that will never be
 * updated
 * 
 * @author Pranav Badrinathan
 */

public final class Reference
{
	public static final Dimension APP_DIMENTIONS = new Dimension(1000, 600);
	public static final Dimension BOARD_DIMENTIONS = new Dimension(500, 500);
	public static final Dimension PIECE_SPRITE_DIMENTIONS = new Dimension(62, 62);

	public static final String BLACK_PIECES_URL = "bin/assets/textures/black/";
	public static final String WHITE_PIECES_URL = "bin/assets/textures/white/";

	public static final String[] PIECE_POSITIONS = { "rook", "knight", "bishop", "k/q", "k/q", "bishop", "knight",
			"rook" };
	
	public static Color hoverColor = Color.decode("#455eff"); 
	public static Color selectColor = Color.decode("#00ff88"); 
}
