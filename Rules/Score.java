package Rules;

public class Score {

	final int pointsPerBrick = 5;
	final int numberOfLivesperMap = 3;

	private int score = 0;

	private int currentNumberOfBricks;

	private int numberOfLives = numberOfLivesperMap;

	public void resetScore(int totalNumberOfBricks) {

		score = 0;
		currentNumberOfBricks = totalNumberOfBricks;
		numberOfLives = numberOfLivesperMap;
	}

	public void updateScore() {
		
		score += pointsPerBrick;
		currentNumberOfBricks -= 1;
	}

	public void loseLife() {

		numberOfLives -= 1;
	}

	public void gainLife() {

		if (numberOfLives < numberOfLivesperMap) {

			numberOfLives += 1;

		}

	}

	/*
	 * GETTERS AND
	 * SETTERS
	 */

	public int getScore() {
		return score;
	}

	public int getNumberOfLives() {
		return numberOfLives;
	}

	public int getCurrentNumberOfBricks() {
		return currentNumberOfBricks;
	}

}
