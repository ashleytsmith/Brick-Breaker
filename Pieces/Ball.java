package Pieces;

import Maths.Maths;

public class Ball {

	private int radius;
	private int X;
	private int Y;

	private int Xvel;
	private int Yvel;

	// Ball speed control

	private int XSpeedFactor;
	private int YSpeedFactor;

	public Ball(int courtWidth, int courtHeight, boolean randomBallVelocity) {

		radius = courtWidth * 1 / 35;
		X = courtWidth * Maths.randomValueConstantIncrements() * 1 / 4;
		Y = courtHeight * 3 / 4;

		if (randomBallVelocity == false) {

			XSpeedFactor = 1;
			YSpeedFactor = 2;

		}

		else {

			XSpeedFactor = Maths.randomValueConstantIncrements();
			YSpeedFactor = Maths.randomValueConstantIncrements();

		}

		Xvel = -XSpeedFactor;
		Yvel = -YSpeedFactor;

	}

	public void increaseBallSpeed() {

		int signX = (int) Math.signum(Xvel);
		int signY = (int) Math.signum(Yvel);

		XSpeedFactor = Maths.increaseValueInConstantIncrements(XSpeedFactor);
		YSpeedFactor = Maths.increaseValueInConstantIncrements(YSpeedFactor);

		Xvel = signX * XSpeedFactor;
		Yvel = signY * YSpeedFactor;

	}

	public void decreaseBallSpeed() {

		int signX = (int) Math.signum(Xvel);
		int signY = (int) Math.signum(Yvel);

		XSpeedFactor = Maths.decreaseValueInConstantIncrements(XSpeedFactor);
		YSpeedFactor = Maths.decreaseValueInConstantIncrements(YSpeedFactor);

		Xvel = signX * XSpeedFactor;
		Yvel = signY * YSpeedFactor;

	}

	/*
	 * GETTERS AND
	 * SETTERS
	 */

	public int getBallRadius() {
		return radius;
	}

	public int getBallX() {
		return X;
	}

	public int getBallY() {
		return Y;
	}

	public int getBallXvel() {
		return Xvel;
	}

	public int getBallYvel() {
		return Yvel;
	}

	public int updateBallX() {
		X = X + Xvel;
		return X;
	}

	public int updateBallY() {
		Y = Y + Yvel;
		return Y;
	}

	public void reverseBallXvel() {
		Xvel = -Xvel;
	}

	public void reverseBallYvel() {
		Yvel = -Yvel;
	}

}
