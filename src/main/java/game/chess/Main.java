package game.chess;

import game.chess.gui.ChessWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The class that contains the <br> {@code public static void main(String[] args)}<br> method
 * 
 * @author Pranav Badrinathan
 */
public class Main extends Application
{
	/**
	 * Entry Point of the application. It first initializes the images used by the pieces by calling {@link SpriteRef#initImages()} 
	 * and then draws the application to the screen by calling {@link ChessGUI#createFrame()}
	 * 
	 * @author Pranav Badrinathan
	 */
	public static void main(String[] args) 
	{
		// Built-in JavaFX function to launch the application.
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		new ChessWindow().createWindow(primaryStage);
	}
}
