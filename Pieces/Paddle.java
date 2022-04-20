package Pieces;

import java.awt.Rectangle;

import java.util.ArrayList;

import Maths.Maths;

public class Paddle {

	private int courtWidth;
	private int courtHeight;
	private int courtBorderWidth;

	private int width;
	private int height;
	private int x;
	private int y;

	private int sensitivity;

	final int defaultpaddleX;
	final int sensitivityWeight;
	final int sizeWeight;

	private int paddleSpeedFactor = 1;
	private int paddleSizeFactor = 1;
	final int defaultSpeedFactor = 1;
	final int defaultSizeFactor = 1;

	public Paddle(Court court) {

		courtWidth = court.getWidth();
		courtHeight = court.getHeight();
		courtBorderWidth = court.getBorderWidth();

		sizeWeight = courtWidth * 1 / 6;
		width = sizeWeight;

		defaultpaddleX = courtWidth * 1 / 2 - width * 1 / 2;

		sensitivityWeight = courtWidth * 1 / 40;
		sensitivity = sensitivityWeight;

		height = courtWidth * 1 / 60;
		x = courtWidth * 1 / 2 - width * 1 / 2;
		y = courtHeight - 5*height;

	}

	public void bounceBalls(ArrayList<Ball> ballList) {

		for (int i = 0; i < ballList.size(); i++) {

			int ballRadius = ballList.get(i).getBallRadius();
			int ballYvel = ballList.get(i).getBallYvel();
			int ballX = ballList.get(i).getBallX();
			int ballY = ballList.get(i).getBallY();

			// Ball and paddle hitboxes

			Rectangle ballHitBox = new Rectangle(Math.round(ballX), Math.round(ballY), ballRadius, ballRadius);
			Rectangle paddleHitBox = new Rectangle(x, y, width, height);

			// Ball colliding with the paddle

			if (ballHitBox.intersects(paddleHitBox)) {

				// Top of the paddle

				if (y >= ballY + ballRadius - ballYvel) {

					ballList.get(i).reverseBallYvel();

				}

				// Side the paddle

				else {

					ballList.get(i).reverseBallXvel();
				}

			}

		}

	}

	// Moving the paddle

	public void moveRight() {

		if (x + width >= courtWidth - courtBorderWidth - sensitivity) {

			x = courtWidth - width - courtBorderWidth -1;

		}

		else {

			x += sensitivity;

		}

	}

	public void moveLeft() {

		if (x <= courtBorderWidth + sensitivity) {

			x = courtBorderWidth +1;

		}

		else {

			x -= sensitivity;

		}

	}

	// Change paddle properties

	public void increasePaddleSensitivity() {

		paddleSpeedFactor = Maths.increaseValueInConstantIncrements(paddleSpeedFactor);
		sensitivity = sensitivityWeight * paddleSpeedFactor;

	}

	public void decreasePaddleSensitivity() {

		paddleSpeedFactor = Maths.decreaseValueInConstantIncrements(paddleSpeedFactor);
		sensitivity = sensitivityWeight * paddleSpeedFactor;

	}

	public void expandPaddle() {

		paddleSizeFactor = Maths.increaseValueInConstantIncrements(paddleSizeFactor);
		width = sizeWeight * paddleSizeFactor;

	}

	public void shrinkPaddle() {

		paddleSizeFactor = Maths.decreaseValueInConstantIncrements(paddleSizeFactor);
		width = sizeWeight * paddleSizeFactor;

	}

	public void resetPaddle() {

		x = defaultpaddleX;
		sensitivity = sensitivityWeight;
		width = sizeWeight;
		paddleSpeedFactor = defaultSpeedFactor;
		paddleSizeFactor = defaultSizeFactor;

	}

	/*
	 * MOVE PADDLE
	 * IN MAIN MENU
	 */

	public void followBall(ArrayList<Ball> ballList) {

		int ballX = ballList.get(0).getBallX();

		if (ballX > width * 1 / 2 && ballX < courtWidth - width * 1 / 2) {

			x = ballX - width * 1 / 2;

		}

	}

	/*
	 * GETTERS AND
	 * SETTERS
	 */

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
