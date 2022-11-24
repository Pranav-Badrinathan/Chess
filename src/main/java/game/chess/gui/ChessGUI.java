package game.chess.gui;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.chess.enums.ChessColor;
import game.chess.util.Player;
import game.chess.util.Reference;

public class ChessGUI extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	// Declare the main UI components
	private static JFrame frame = new JFrame("Chess");
	private static JPanel background = new JPanel(new GridBagLayout());
	private static Board board = new Board();
	
	public static Player player1 = new Player("player", ChessColor.WHITE);	
	public static Player player2 = new Player("opponent", ChessColor.BLACK);	
		
	/**
	 * Creates the {@code JFrame} and adds the chess board, {@link Board}, to the frame.<br>
	 * Initializes everything that follows.
	 */
	public static void createFrame() 
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		//Set stuff that JFrame needs
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	
		//Set stuff that JPanel needs
		background.setPreferredSize(Reference.APP_DIMENTIONS);
		
		frame.getContentPane().add(background);
		frame.pack();
		
		background.add(board);
		BoardHandler.init(board);
		
		//Set the location of the JFrame and set it visible
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
