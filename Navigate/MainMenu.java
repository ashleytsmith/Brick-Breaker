package Navigate;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;

import Gameplay.Draw;
import Pieces.Ball;
import Pieces.Brick;
import Pieces.Court;
import Pieces.Paddle;

class MainMenu extends JPanel implements ActionListener {

	private JPanel buttonsPanel;

	private int menuWidth;
	private int menuHeight;

	private int buttonsPanelWidth;
	private int buttonsPanelHeight;

	// Associated Draw class

	private Draw draw = new Draw();

	// Button info

	private JButton buttons[];
	private int numberofLevels = 8;
	private int rows = 2;
	private int columns = 4;
	private int buttonSize;
	private int gapBetweenButtons;
	private int buttonsPanelX;
	private int buttonsPanelY;
	private int[] buttonsInfo;

	// Bot game

	private Court court = new Court();
	private Paddle paddle;
	private Ball ball;
	private ArrayList<Ball> ballList = new ArrayList<Ball>();
	private Timer timer;
	private Brick brick = new Brick();

	public MainMenu() {

		// Set fields

		menuWidth = court.getWidth();
		menuHeight = court.getHeight();

		buttonsPanelWidth = menuWidth * 3 / 4;
		buttonsPanelHeight = menuHeight * 3 / 8;

		buttonSize = buttonsPanelWidth / columns * 4 / 5;
		gapBetweenButtons = buttonsPanelWidth / columns * 1 / 5;

		buttonsPanelX = menuWidth * 1 / 2 - buttonsPanelWidth * 1 / 2;
		buttonsPanelY = menuHeight * 1 / 2 - buttonsPanelHeight;

		buttonsInfo = new int[] { rows, columns, buttonSize, gapBetweenButtons, buttonsPanelX, buttonsPanelY };

		// Add a buttons paddle in the middle of the screen

		this.setLayout(null);

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(null);
		buttonsPanel.setSize(buttonsPanelWidth, buttonsPanelHeight);
		buttonsPanel.setLocation(buttonsPanelX, buttonsPanelY);

		buttons = new JButton[numberofLevels];
		addButtons();

		this.add(buttonsPanel);

		// Variables required for the bot game 

		int delay = 4800 / menuWidth;
		timer = new Timer(delay, this);
		timer.start();

		paddle = new Paddle(court);
		ball = new Ball(menuWidth, menuHeight, false);
		ballList.add(ball);

		Rectangle hitbox = new Rectangle(buttonsPanelX, buttonsPanelY, buttonsPanelWidth, buttonsPanelHeight);
		brick.setBrickHitbox(hitbox); // Add a brick the same size as the buttons panel

	}

	private void addButtons() {

		int count = 1;

		for (int j = 0; j < rows; j++) {

			for (int i = 0; i < columns; i++) {

				buttons[i] = new JButton(String.valueOf(count));
				buttons[i].setBounds(i * (buttonSize + gapBetweenButtons) + gapBetweenButtons / 2,
						j * (buttonSize + gapBetweenButtons) + gapBetweenButtons / 2, buttonSize, buttonSize);
				final int index = count;

				buttons[i].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						String filename = "Level" + Integer.toString(index) + ".txt";
						timer.stop();
						Frame.displayGame(filename);

					}
				});

				buttonsPanel.add(buttons[i]);
				count += 1;

			}
		}

	}

	/*
	 * BOT
	 * GAME
	 */

	public void actionPerformed(ActionEvent e) {

		court.moveBalls(ballList);
		court.bounceBalls(ballList);

		paddle.followBall(ballList);
		paddle.bounceBalls(ballList);

		brick.bounceBalls(ballList);

		repaint();

	}

	public void restartTimer() {

		timer.start();

	}

	/*
	 * PAINTING
	 * TO SCREEN
	 * 
	 */

	public void paint(Graphics g) {

		draw.paintMenu(g, menuWidth, menuHeight, paddle, ballList, buttonsInfo);	

	}

}
