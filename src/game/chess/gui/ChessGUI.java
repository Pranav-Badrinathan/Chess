package game.chess.gui;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessGUI extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private static Dimension appDimention = new Dimension(1000, 600);
	
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
		background.setPreferredSize(appDimention);
		
		frame.getContentPane().add(background);
		frame.pack();
		
		background.add(board);
		
		//Set the location of the JFrame and set it visible
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
