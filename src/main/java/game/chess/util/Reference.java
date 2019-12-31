package game.chess.util;

import java.awt.Dimension;

public final class Reference
{
	public static final Dimension appDimention = new Dimension(1000, 600);
	public static final Dimension boardDimention = new Dimension(500, 500);
	public static final Dimension pieceSpriteDimention = new Dimension(62, 62);

	public static final String blackPiecesUrl = "bin/assets/textures/black/";
	public static final String whitePiecesUrl = "bin/assets/textures/white/";

	public static final String[] piecePositions = { "rook", "knight", "bishop", "k/q", "k/q", "bishop", "knight",
			"rook" };
}
