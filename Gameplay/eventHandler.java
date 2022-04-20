package Gameplay;

import Design.Map;
import Maths.Maths;
import Pieces.Ball;
import Pieces.Brick;
import Pieces.Court;
import Pieces.Paddle;
import Rules.PowerUps;
import Rules.Score;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;

public class eventHandler extends JPanel implements ActionListener {

	// Court

	private Court court = new Court();

	private int courtWidth = court.getWidth();
	private int courtHeight = court.getHeight();

	// Map

	private Map map = new Map(court);

	// Paddle

	private Paddle paddle = new Paddle(court);

	// Score

	private Score score = new Score();

	// PowerUps

	private PowerUps powerups;
	private String powerUpType = "";

	// List of balls

	private ArrayList<Ball> ballList = new ArrayList<Ball>();
	private boolean randomBallVelocity;

	// List of bricks

	private ArrayList<Brick> brickList = new ArrayList<Brick>();

	// Framerate

	private int delay =  4800 / courtWidth;  // Scales well with changing court size for 400,600, and 800 pixels. 
	private Timer timer; 					 // The JVM should keep the ball speed the same independant of the device.

	// Gamestate

	private boolean play = false;

	// Associated keyHandler

	private keyHandler keyHandler;

	// Associated Draw class

	private Draw draw = new Draw();

	/*
	 * CLASS
	 * CONSTRUCTOR
	 * 
	 */

	public eventHandler(int occupiedOrNot[][], String powerUps[][], Color brickColors[][], boolean staggered) {

		keyHandler = new keyHandler(this, paddle); // Add a keyHandler and give it acesss to this instance of the eventHandler
		this.addKeyListener(keyHandler);

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		brickList = loadBricklist(occupiedOrNot, powerUps, brickColors, staggered);
		ballList = newBall();
		powerups = new PowerUps(this, paddle, map, court, score);

		timer = new Timer(delay, this);
		timer.start();

	}

	/*
	 * MOVE THE BALL
	 * AND HANDLE COLLISIONS
	 * 
	 */

	public void actionPerformed(ActionEvent e) {

		if (play) {

			// Balls interacting with the court

			court.moveBalls(ballList);
			court.bounceBalls(ballList);
			court.removeBallIfOutOfPlay(ballList);

			// Stop game if all balls out of play

			if (ballList.size() == 0) {

				play = false;
				score.loseLife();

				// Paint defeat message
				repaint();

				return;
			}

			// Balls colliding with the paddle

			paddle.bounceBalls(ballList);

			// Balls interacting with the bricks

			for (int j = 0; j < brickList.size(); j++) {

				boolean collides = brickList.get(j).bounceBalls(ballList);

				if (collides == true) {

					// Use the PowerUp

					Brick brick = brickList.get(j);
					powerups.givePowerUp(brick);
					powerUpType = brick.getBrickPowerUpType();
					removeBrick(j);
					repaint();

					// Break out of loop to stop all bricks being tested every time. Only bottomost
					// or leftmost brick is destroyed when ball hitbox collides with two bricks.
					break;

				}

			}

			repaint();

		}

		// End when all bricks have been hit

		if (score.getCurrentNumberOfBricks() <= 0) {

			play = false;

		}

		// End when all lives lost

		if (score.getNumberOfLives() < 1) {

			play = false;

		}

	}

	/*
	 * ADD NEW
	 * BALLS
	 */

	public void clearBallList() {

		ballList.clear();

	}

	public ArrayList<Ball> newBall() {

		if (ballList.size() == 0) {

			randomBallVelocity = false;
		}

		else {

			randomBallVelocity = true;
		}

		Ball ball = new Ball(courtWidth, courtHeight, randomBallVelocity);
		ballList.add(ball);

		return ballList;

	}

	/*
	 * INITIALISE
	 * BRICKLIST
	 */

	public ArrayList<Brick> loadBricklist(int occupiedOrNot[][], String powerUpsMatrix[][], Color brickColorsMatrix[][],
			boolean staggered) {

		map.setOccupationMatrix(occupiedOrNot);
		map.setPowerUpsMatrix(powerUpsMatrix);
		map.setBrickColorsMatrix(brickColorsMatrix);
		map.setBooleanStaggered(staggered);

		int totalNumberOfBricks = map.getNumberofBricks();

		score.resetScore(totalNumberOfBricks);

		brickList.clear();

		int brickWidth = map.getBrickWidth();
		int brickHeight = map.getBrickHeight();

		int mapShiftX = map.getMapShiftX();
		int mapShiftY = map.getMapShiftY();

		// go through rows in reverse order so that bricks at the bottom are tested for collisions first
		

		for (int i = occupiedOrNot.length - 1; i > -1; i--) {

			for (int j = 0; j < occupiedOrNot[0].length; j++) {

				if (occupiedOrNot[i][j] > 0) {

					Brick brick = new Brick();

					int brickX = j * brickWidth + mapShiftX
							+ Maths.ifBooleanTrue(staggered) * brickWidth / 2 * Maths.zerowhenEven(i);
					int brickY = i * brickHeight + mapShiftY;

					Rectangle hitbox = new Rectangle(brickX, brickY, brickWidth, brickHeight);
					brick.setBrickHitbox(hitbox);

					brick.setBrickPowerUp(powerUpsMatrix[i][j]);
					brick.setBrickColor(brickColorsMatrix[i][j]);

					brickList.add(brick);



				}

			}

		

		}

		

		return brickList;

	}

	/*
	 * REMOVE
	 * BRICK
	 */

	public void removeBrick(int brickIndex) {

		brickList.remove(brickIndex);
		score.updateScore();

	}

	/*
	 * RELOAD MAP
	 */

	void reloadMap() {

		play = true;

		clearBallList();
		newBall();

		paddle.resetPaddle();

		setPowerUpType("None");
		powerups.resetPowerUps();

		if (score.getNumberOfLives() < 1 || brickList.size() == 0) {

			int[][] occupiedOrNot = map.getOccupationMatrix();
			String[][] powerUps = map.getPowerUpsMatrix();
			Color[][] brickColors = map.getBrickColorsMatrix();
			boolean staggered = map.getBooleanStaggered();

			loadBricklist(occupiedOrNot, powerUps, brickColors, staggered);

		}

	}

	/*
	 * PAINTING
	 * TO SCREEN
	 */

	public void paint(Graphics g) {

		draw.paintGame(g, play, ballList, score, paddle, court, brickList, map, powerUpType);

	}

	/*
	 * GETTERS AND
	 * SETTERS
	 */

	public boolean getBooleanPlay() {
		return play;
	}

	public void setPowerUpType(String powerUp) {
		powerUpType = powerUp;
	}

	public ArrayList<Ball> getBallList() {
		return ballList;
	}

	public ArrayList<Brick> getBrickList() {
		return brickList;
	}

}
