package Gameplay;

import Pieces.Ball;
import Pieces.Brick;
import Pieces.Court;
import Pieces.Paddle;
import Rules.Score;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;

import Design.Map;

import java.awt.BasicStroke;

public class Draw {

	private int courtWidth;
	private int courtHeight;
	private int courtBorderWidth;

	private int brickBorderWidth;
	private int brickBorderArclength;
	private BasicStroke brickBorderStroke;

	public Draw() {

	}

	/*
	 * PAINTING THE GAME
	 */

	public void paintGame(Graphics g, boolean play, ArrayList<Ball> ballList, Score score, Paddle paddle, Court court,
			ArrayList<Brick> brickList, Map map, String powerUpType) {

		// Set fields

		courtWidth = court.getWidth();
		courtHeight = court.getHeight();
		courtBorderWidth = court.getBorderWidth();

		brickBorderWidth = map.getBrickBorderWidth();
		brickBorderArclength = map.getBrickBorderArclength();
		brickBorderStroke = new BasicStroke(brickBorderWidth);

		// Background and borders

		this.drawBackground(g);
		this.drawBorders(g);

		// Score

		this.drawScoreInfo(g, score);

		// Paddle

		this.drawPaddle(g, paddle);

		// Balls

		if (ballList.size() > 0) {

			this.drawBalls(g, ballList);

		}

		// Bricks

		this.drawBricks((Graphics2D) g, brickList);

		// Writing to sceen

		this.writeGameMessages(g, play, score, ballList);

		this.writePowerUpMessage(g, powerUpType);

		g.dispose();
	}

	/*
	 * PAINTING THE MENU
	 */

	public void paintMenu(Graphics g, int menuWidth, int menuHeight, Paddle paddle, ArrayList<Ball> ballList,
			int[] buttonsInfo) {

		// Set fields

		courtWidth = menuWidth;
		courtHeight = menuHeight;

		// Background

		this.drawBackground(g);

		// Writing to sceen

		String string_1 = "";
		String string_2 = "";
		String string_3 = "Click to play a level";
		this.drawMessage(g, string_1, string_2, string_3);

		// Paddle

		this.drawPaddle(g, paddle);

		// Balls

		if (ballList.size() > 0) {

			this.drawBalls(g, ballList);

		}

		this.drawButtons(g, buttonsInfo);

		g.dispose();
	}

	/*
	 * PAINTING METHODS
	 */

	public void drawBricks(Graphics2D g, ArrayList<Brick> brickList) {

		for (int i = 0; i < brickList.size(); i++) {

			g.setColor(brickList.get(i).getBrickColor());

			g.fillRect(brickList.get(i).getBrickHitbox().x, brickList.get(i).getBrickHitbox().y,
					brickList.get(i).getBrickHitbox().width, brickList.get(i).getBrickHitbox().height);
			g.setStroke(brickBorderStroke);
			g.setColor(Color.white);
			g.drawRoundRect(brickList.get(i).getBrickHitbox().x, brickList.get(i).getBrickHitbox().y,
					brickList.get(i).getBrickHitbox().width, brickList.get(i).getBrickHitbox().height,
					brickBorderArclength, brickBorderArclength);

		}

	}

	public void drawPaddle(Graphics g, Paddle paddle) {

		int paddleWidth = paddle.getWidth();
		int paddleHeight = paddle.getHeight();
		int paddleX = paddle.getX();
		int paddleY = paddle.getY();

		g.setColor(Color.WHITE);
		g.draw3DRect(paddleX, paddleY, paddleWidth, paddleHeight, true);

	}

	public void drawBalls(Graphics g, ArrayList<Ball> ballList) {

		g.setColor(Color.WHITE);

		for (int i = 0; i < ballList.size(); i++) {

			int ballRadius = ballList.get(i).getBallRadius();
			int ballX = ballList.get(i).getBallX();
			int ballY = ballList.get(i).getBallY();

			g.drawOval(ballX, ballY, ballRadius, ballRadius);

		}

	}

	public void drawBackground(Graphics g) {

		g.setColor(Color.black);
		g.fillRect(0, 0, courtWidth, courtHeight);

	}

	public void drawBorders(Graphics g) {

		g.setColor(Color.BLUE);
		g.fillRect(0, 0, courtBorderWidth, courtHeight);
		g.fillRect(0, 0, courtWidth, courtBorderWidth);
		g.fillRect(courtWidth - courtBorderWidth, 0, courtBorderWidth, courtHeight);

	}

	public void drawScoreInfo(Graphics g, Score score) {

		int ballRadius = courtWidth * 1 / 35;

		g.setFont(new Font("Helvetica", Font.BOLD, courtWidth * 1 / 25));
		g.setColor(Color.white);
		g.drawString("" + score.getScore(), courtWidth * 1 / 20, courtHeight * 37 / 40);

		for (int i = 0; i < score.getNumberOfLives(); i++) {

			g.drawOval(courtWidth * (i + 16) / 20, courtHeight * 36 / 40, ballRadius, ballRadius);

		}

	}

	public void writeGameMessages(Graphics g, boolean play, Score score, ArrayList<Ball> ballList) {

		if (!play) {

			// Start message

			if (score.getCurrentNumberOfBricks() > 0) {

				String string_1 = "";
				String string_2 = "Press Enter to Start";
				String string_3 = "Press Esc to go to the Menu";

				this.drawMessage(g, string_1, string_2, string_3);

			}

			// Defeat message

			if (ballList.size() == 0 && score.getNumberOfLives() < 1) {

				String string_1 = "Game over";
				String string_2 = "Press Enter to Start";
				String string_3 = "Press Esc to go to the Menu";

				this.drawMessage(g, string_1, string_2, string_3);

			}

			// Victory message

			if (score.getCurrentNumberOfBricks() <= 0) {

				String string_1 = "You Won";
				String string_2 = "Press Enter to Start";
				String string_3 = "Press Esc to go to the Menu";

				this.drawMessage(g, string_1, string_2, string_3);

			}

		}

	}

	public void drawMessage(Graphics g, String string_1, String string_2, String string_3) {

		// draw string in middle of screen

		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", Font.BOLD, courtWidth * 1 / 25));

		int string_width_1 = g.getFontMetrics().stringWidth(string_1);
		int string_width_2 = g.getFontMetrics().stringWidth(string_2);
		int string_width_3 = g.getFontMetrics().stringWidth(string_3);

		g.drawString(string_1, courtWidth * 1 / 2 - string_width_1 * 1 / 2, courtHeight * 1 / 2);
		g.drawString(string_2, courtWidth * 1 / 2 - string_width_2 * 1 / 2, courtHeight * 5 / 8);
		g.drawString(string_3, courtWidth * 1 / 2 - string_width_3 * 1 / 2, courtHeight * 6 / 8);

	}

	public void writePowerUpMessage(Graphics g, String powerup) {

		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", Font.BOLD, courtWidth * 1 / 50));

		if (powerup.equals("None")) {

			powerup = "";

		}

		powerup = splitStringOnCapitals(powerup);

		int string_width = g.getFontMetrics().stringWidth(powerup);
		g.drawString(powerup, courtWidth * 7 / 8 - string_width * 1 / 2, courtHeight * 7 / 8);

	}

	public void drawButtons(Graphics g, int[] buttonsInfo) {

		g.setColor(Color.white);
		int arclength = courtWidth * 1 / 120;
		g.setFont(new Font("Helvetica", Font.BOLD, courtWidth * 1 / 25));

		int rows = buttonsInfo[0];
		int columns = buttonsInfo[1];
		int buttonSize = buttonsInfo[2];
		int gapBetweenButtons = buttonsInfo[3];
		int buttonsPanelX = buttonsInfo[4];
		int buttonsPanelY = buttonsInfo[5];

		for (int j = 0; j < rows; j++) {

			for (int i = 0; i < columns; i++) {

				g.drawRoundRect(i * (buttonSize + gapBetweenButtons) + gapBetweenButtons / 2 + buttonsPanelX,
						j * (buttonSize + gapBetweenButtons) + gapBetweenButtons / 2 + buttonsPanelY, buttonSize,
						buttonSize, arclength, arclength);

			}
		}

		int count = 1;

		for (int j = 0; j < rows; j++) {

			for (int i = 0; i < columns; i++) {

				String mapNumber = Integer.toString(count);
				int string_width = g.getFontMetrics().stringWidth(mapNumber);

				g.drawString(mapNumber,
						i * (buttonSize + gapBetweenButtons) + gapBetweenButtons / 2 + buttonSize / 2 + buttonsPanelX
								- string_width / 2,
						j * (buttonSize + gapBetweenButtons) + gapBetweenButtons / 2 + buttonSize / 2 + buttonsPanelY);

				count += 1;

			}
		}

	}

	public String splitStringOnCapitals(String string) {

		String newString = "";

		for (int i = 0; i < string.length(); i++) {

			Character ch = string.charAt(i);

			if (Character.isUpperCase(ch)) {

				newString += " " + ch;
			}

			else {

				newString += ch;
			}

		}

		return newString;

	}

}
