package Pieces;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Color;

public class Brick {

	private Rectangle hitbox;
	private Color color;
	private String powerup;

	public boolean bounceBalls(ArrayList<Ball> ballList) {

		boolean collides = false;

		// Balls interacting with the bricks

		for (int i = 0; i < ballList.size(); i++) {

			int ballRadius = ballList.get(i).getBallRadius();
			int ballXvel = ballList.get(i).getBallXvel();
			int ballX = ballList.get(i).getBallX();
			int ballY = ballList.get(i).getBallY();

			// Ball hitbox

			Rectangle ballHitBox = new Rectangle(ballX, ballY, ballRadius, ballRadius);

			if (ballHitBox.intersects(hitbox)) {

				// Ball colliding with left and right edges

				if (ballX + ballRadius - ballXvel <= hitbox.x ||
						ballX - ballXvel >= hitbox.x + hitbox.width) {

					ballList.get(i).reverseBallXvel();

				}

				// Ball colliding with top and bottom edges

				else {

					ballList.get(i).reverseBallYvel();

				}

				collides = true;
			}
		}

		return collides;

	}

	/*
	 * MOVE
	 * BRICK
	 */

	public void shfitBrickDown(int shift) {
		hitbox.y = hitbox.y + shift;
	}

	public void shfitBrickSideWays(int shift) {
		hitbox.x = hitbox.x + shift;
	}

	/*
	 * GETTERS AND
	 * SETTERS
	 */

	public Rectangle getBrickHitbox() {
		return hitbox;
	}

	public String getBrickPowerUpType() {
		return powerup;
	}

	public Color getBrickColor() {
		return color;
	}

	public void setBrickHitbox(Rectangle Hitbox) {
		hitbox = Hitbox;
	}

	public void setBrickPowerUp(String Powerup) {
		powerup = Powerup;
	}

	public void setBrickColor(Color Color) {
		color = Color;
	}

}
