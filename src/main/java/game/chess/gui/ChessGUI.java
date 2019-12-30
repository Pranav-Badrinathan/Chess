package game.chess.gui;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.chess.util.Reference;

public class ChessGUI extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	public static JFrame frame = new JFrame("Chess");
	public static JPanel background = new JPanel(new GridBagLayout());
	public static BoardGUI board = new BoardGUI();
	
	public static int width;
	public static int height;
	
	public static void createFrame() 
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		//Set stuff that JFrame needs
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	
		//Set stuff that JPanel needs
		background.setPreferredSize(Reference.appDimention);
		
		frame.getContentPane().add(background);
		frame.pack();
		
		background.add(board);
		
		//Set the location of the JFrame and set it visible
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}