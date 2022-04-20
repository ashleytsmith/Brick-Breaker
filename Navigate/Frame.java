package Navigate;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Design.Map;
import Design.MapInfo;
import Gameplay.eventHandler;
import Pieces.Court;

public class Frame {

	private static int width = 600;
	private static int height = 600;

	private static int padding = width * 1 / 40;
	private static int whiteSpace = width * 1 / 20;  // simple fix to stop border being cut off if resize the game. Works for 400, 600 and 800 pixels. Would need to increase whitespace for much smaller sizes. 
													
	private static JFrame frame = new JFrame();

	private static JPanel containerPanel = new JPanel();
	private static CardLayout panes = new CardLayout();
	private static EmptyBorder paddedBorder = new EmptyBorder(padding,padding,padding,padding);

	private static MainMenu menuPanel = new MainMenu();

	private static eventHandler eventHandlerPlayer1;

	/*
	 * WINDOW TO DRAW
	 * GAME INSIDE
	 * 
	 */
	public static void main(String[] args) {

		// Initialise the event handler with data for the first map

		SaveandLoadMap saveandloadmap = new SaveandLoadMap();
		MapInfo mapinfo = saveandloadmap.load("Level1.txt");

		int[][] occupiedOrNot = mapinfo.getOccupationMatrix();
		String[][] powerUps = mapinfo.getPowerUpsMatrix();
		Color[][] brickColors = mapinfo.getBrickColorsMatrix();
		boolean staggered = mapinfo.getBooleanStaggered();

		eventHandlerPlayer1 = new eventHandler(occupiedOrNot, powerUps, brickColors, staggered);

		// Add the menu panel and the game panel to the container and cycle between them using CardLayout()

		containerPanel.setLayout(panes);
		containerPanel.add(menuPanel);
		containerPanel.add(eventHandlerPlayer1);
		containerPanel.setBorder(paddedBorder);

		frame.setContentPane(containerPanel);
		panes.first(containerPanel);

		initialiseWindow();

	}

	public static void initialiseWindow() {

		frame.setTitle("Brick Breaker");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width + 2*padding, height + 2*padding + whiteSpace);
		frame.validate();

	}

	public static void displayGame(String filename) {

		SaveandLoadMap saveandloadmap = new SaveandLoadMap();
		MapInfo mapinfo = saveandloadmap.load(filename);

		int[][] occupiedOrNot = mapinfo.getOccupationMatrix();
		String[][] powerUpsMatrix = mapinfo.getPowerUpsMatrix();
		Color[][] brickColorsMatrix = mapinfo.getBrickColorsMatrix();
		boolean staggered = mapinfo.getBooleanStaggered();

		eventHandlerPlayer1.loadBricklist(occupiedOrNot, powerUpsMatrix, brickColorsMatrix, staggered);

		panes.next(containerPanel);
		eventHandlerPlayer1.requestFocusInWindow();

	}

	public static void displayDefaultMap() {

		Court court = new Court();
		Map map = new Map(court);

		int[][] occupiedOrNot = map.getOccupationMatrix();
		String[][] powerUpsMatrix = map.getPowerUpsMatrix();
		Color[][] brickColorsMatrix = map.getBrickColorsMatrix();
		boolean staggered = map.getBooleanStaggered();

		eventHandlerPlayer1.loadBricklist(occupiedOrNot, powerUpsMatrix, brickColorsMatrix, staggered);

		panes.next(containerPanel);
		eventHandlerPlayer1.requestFocusInWindow();

	}

	public static void returnToMenu() {

		panes.first(containerPanel);
		menuPanel.restartTimer();

	}

	/*
	 * GETTERS AND
	 * SETTERS
	 */

	public static int getFrameWidth() {
		return width;
	}

	public static int getFrameHeight() {
		return height;
	}

}
