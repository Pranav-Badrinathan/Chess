package game.chess.util;

import java.awt.Color;
import java.awt.Dimension;
import java.util.EnumMap;
import java.util.Map;

import game.chess.enums.ChessColor;

/**
 * A class that stores all the 'Global', or all the {@code static final} variables that will never be
 * updated
 * 
 * @author Pranav Badrinathan
 */

public final class Reference
{
	// TODO: Make these dimensions resolution independent. Use percentages or something.
	public static final Dimension APP_DIMENTIONS = new Dimension(1000, 600);
	public static final Dimension BOARD_DIMENTIONS = new Dimension(500, 500);
	public static final Dimension PIECE_SPRITE_DIMENTIONS = new Dimension(62, 62);

	public static final String BLACK_PIECES_URL = "/assets/textures/black/";
	public static final String WHITE_PIECES_URL = "/assets/textures/white/";

	public static final String[] PIECE_POSITIONS = { "rook", "knight", "bishop", "k/q", "k/q", "bishop", "knight",
			"rook" };
	
	public static Color hoverColor = Color.decode("#455eff"); 
	public static Color selectColor = Color.decode("#00ff88"); 
	
	public static EnumMap<ChessColor, Color> visibleColor = new EnumMap<>(Map.of(
				ChessColor.BLACK, Color.GRAY,
				ChessColor.WHITE, Color.WHITE
			));
}
