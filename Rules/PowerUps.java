package Rules;

import Design.Map;
import Gameplay.eventHandler;
import Maths.Maths;
import Pieces.Ball;
import Pieces.Brick;
import Pieces.Court;
import Pieces.Paddle;

import java.util.ArrayList;

public class PowerUps {

	private eventHandler eventHandler;
	private Paddle paddle;
	private Map map;
	private Court court;
	private Score score;

	final int bricksDestroyedUntilNewBall = 10;
	int bricksLeftToDestroyUntilNewBall;

	public PowerUps(eventHandler associatedEventHandler, Paddle associatedPaddle, Map associatedMap,
			Court associatedCourt, Score associatedScore) {

		eventHandler = associatedEventHandler;
		paddle = associatedPaddle;
		map = associatedMap;
		court = associatedCourt;
		score = associatedScore;

		bricksLeftToDestroyUntilNewBall = bricksDestroyedUntilNewBall;

	}

	public void givePowerUp(Brick impactedBrick) {

		String powerUpType = impactedBrick.getBrickPowerUpType();

		// Ball PowerUps

		if (powerUpType.equals("NewBall")) {

			eventHandler.newBall();
		}

		if (powerUpType.equals("IncreaseSpeed")) {

			ArrayList<Ball> ballList = eventHandler.getBallList();

			for (int i = 0; i < ballList.size(); i++) {

				ballList.get(i).increaseBallSpeed();

			}

			paddle.increasePaddleSensitivity();
		}

		if (powerUpType.equals("DecreaseSpeed")) {

			ArrayList<Ball> ballList = eventHandler.getBallList();

			for (int i = 0; i < ballList.size(); i++) {

				ballList.get(i).decreaseBallSpeed();

			}

			paddle.decreasePaddleSensitivity();

		}

		if (bricksLeftToDestroyUntilNewBall < 1) {

			eventHandler.newBall();
			bricksLeftToDestroyUntilNewBall = bricksDestroyedUntilNewBall;
		}

		// Brick PowerUps

		bricksLeftToDestroyUntilNewBall -= 1;

		if (powerUpType.equals("SpaceInvaders")) {

			ArrayList<Brick> brickList = eventHandler.getBrickList();

			int brickHeight = map.getBrickHeight();

			for (int i = 0; i < brickList.size(); i++) {

				brickList.get(i).shfitBrickDown(brickHeight);

			}

		}

		if (powerUpType.equals("MovingBricks")) {

			ArrayList<Brick> brickList = eventHandler.getBrickList();

			int brickWidth = map.getBrickWidth();
			int courtWidth = court.getWidth();
			int sign;

			for (int i = 0; i < brickList.size(); i++) {

				Brick brick = brickList.get(i);

				if (brick.getBrickHitbox().x > courtWidth * 3 / 4) {

					sign = -1;

				}

				else if (brick.getBrickHitbox().x < courtWidth * 1 / 4) {

					sign = 1;
				}

				else {

					sign = Maths.randomValueOneOrMinusOne();
				}

				brickList.get(i).shfitBrickSideWays(sign * brickWidth);

			}
		}

		// Paddle PowerUps

		if (powerUpType.equals("ExpandPaddle")) {

			paddle.expandPaddle();

		}

		if (powerUpType.equals("ShrinkPaddle")) {

			paddle.shrinkPaddle();

		}

		// Score PowerUps

		if (powerUpType.equals("LoseLife")) {

			score.loseLife();

		}

		if (powerUpType.equals("GainLife")) {

			score.gainLife();

		}

	}

	public void resetPowerUps() {

		bricksLeftToDestroyUntilNewBall = bricksDestroyedUntilNewBall;

	}

}