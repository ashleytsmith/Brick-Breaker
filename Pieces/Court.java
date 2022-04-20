package Pieces;

import java.util.ArrayList;

import Navigate.Frame;

public class Court {

	private int width = Frame.getFrameWidth();
	private int height = Frame.getFrameHeight();
	private int borderWidth = width * 1 / 120;

	public void moveBalls(ArrayList<Ball> ballList) {

		for (int i = 0; i < ballList.size(); i++) {

			// Update ball position

			ballList.get(i).updateBallX();
			ballList.get(i).updateBallY();

		}

	}

	public void bounceBalls(ArrayList<Ball> ballList) {

		for (int i = 0; i < ballList.size(); i++) {

			int ballRadius = ballList.get(i).getBallRadius();
			int ballX = ballList.get(i).getBallX();
			int ballY = ballList.get(i).getBallY();

			// Ball colliding with the boundaries

			// Left boundary

			if (ballX < 0) {

				ballList.get(i).reverseBallXvel();

			}

			// Top boundary

			if (ballY < 0) {

				ballList.get(i).reverseBallYvel();

			}

			// Right boundary

			if (ballX + ballRadius > width - borderWidth) {

				ballList.get(i).reverseBallXvel();
			}

		}

	}

	public void removeBallIfOutOfPlay(ArrayList<Ball> ballList) {

		for (int i = 0; i < ballList.size(); i++) {

			int ballY = ballList.get(i).getBallY();

			// Ball going off screen

			if (ballY > height * 19 / 20) {

				ballList.remove(i);

				return;

			}

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

	public int getBorderWidth() {
		return borderWidth;
	}

}
