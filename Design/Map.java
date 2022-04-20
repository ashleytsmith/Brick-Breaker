package Design;

import Pieces.Court;

import java.awt.Color;

import Maths.Maths;

/*
* CONTAINS ALL DATA
* FOR THE LEVEL GEOMETRY
 */

public class Map {

	private int courtWidth;
	private int courtHeight;
	private int courtBorderWidth;

	// map variables

	private int brickBorderWidth;

	private int mapShiftX;
	private int mapShiftY;

	private int mapWidth;
	private int mapHeight;

	private int occupiedorNot[][];
	private boolean staggered = false;
	private int totalNumberOfBricks;

	private String powerUps[][];
	private Color brickColors[][];

	private int rows = 8;
	private int columns = 8;

	private int brickWidth;
	private int brickHeight;

	// Info needed for drawing bricks

	private int brickBorderArclength;

	/*
	 * INITIALISE OCCUPATION MATRIX
	 * AND PROPERTIES LIST
	 * 
	 */

	public Map(Court associatedCourt) {

		courtWidth = associatedCourt.getWidth();
		courtHeight = associatedCourt.getHeight();
		courtBorderWidth = associatedCourt.getBorderWidth();

		brickBorderWidth = courtWidth * 1 / 200;

		mapWidth = courtWidth - 2 * courtBorderWidth - 2 * brickBorderWidth;
		mapHeight = courtHeight * 1 / 3;

		brickWidth = mapWidth / columns;
		brickHeight = mapHeight / rows;

		int residual = courtWidth - 2 * courtBorderWidth - 2 * brickBorderWidth - brickWidth * columns; // make sure bricks well centered for different map sizes

		mapShiftX = courtBorderWidth + brickBorderWidth + residual / 2;
		mapShiftY = courtBorderWidth + brickBorderWidth;

		occupiedorNot = fillOccupationMatix(rows, columns);
		powerUps = fillPowerUpsMatrix(rows, columns);
		brickColors = fillbrickColorsMatrix(rows, columns);

		brickBorderArclength = courtWidth * 1 / 120;

	}

	/*
	 * CONSTRUCT THE
	 * DEFAULT MAP
	 */

	public int[][] fillOccupationMatix(int row, int col) {

		occupiedorNot = new int[rows][columns];

		for (int i = 0; i < occupiedorNot.length; i++) {

			for (int j = 0; j < occupiedorNot[0].length; j++) {

				occupiedorNot[i][j] = 1
						+ Maths.ifBooleanTrue(staggered) * Maths.zeroOnLastColumnWhenOdd(occupiedorNot[0].length, i, j);

			}
		}

		return occupiedorNot;

	}

	public String[][] fillPowerUpsMatrix(int row, int col) {

		powerUps = new String[rows][columns];

		for (int i = 0; i < powerUps.length; i++) {

			for (int j = 0; j < powerUps[0].length; j++) {

				powerUps[i][j] = "None";

			}
		}

		return powerUps;

	}

	public Color[][] fillbrickColorsMatrix(int row, int col) {

		brickColors = new Color[rows][columns];

		for (int i = 0; i < brickColors.length; i++) {

			for (int j = 0; j < brickColors[0].length; j++) {

				brickColors[i][j] = Color.blue;

			}
		}

		return brickColors;

	}

	/*
	 * EDIT BRICK VALUES
	 * AND COUNTING BRICKS
	 */

	public int getNumberofBricks() {

		int count = 0;

		for (int i = 0; i < occupiedorNot.length; i++) {

			for (int j = 0; j < occupiedorNot[0].length; j++) {

				count += occupiedorNot[i][j];

			}

		}

		return count;

	}

	/*
	 * GETTERS AND
	 * SETTERS
	 */

	public int getMapShiftX() {
		return mapShiftX;
	}

	public int getMapShiftY() {
		return mapShiftY;
	}

	public int getBrickWidth() {
		return brickWidth;
	}

	public int getBrickHeight() {
		return brickHeight;
	}

	public int getBrickBorderWidth() {
		return brickBorderWidth;
	}

	public int getBrickBorderArclength() {
		return brickBorderArclength;
	}

	public int[][] getOccupationMatrix() {
		return occupiedorNot;
	}

	public String[][] getPowerUpsMatrix() {
		return powerUps;
	}

	public Color[][] getBrickColorsMatrix() {
		return brickColors;
	}

	public boolean getBooleanStaggered() {
		return staggered;
	}

	public int gettotalBricks() {
		return totalNumberOfBricks;
	}

	public void setOccupationMatrix(int OccupationMatrix[][]) {
		occupiedorNot = OccupationMatrix;
	}

	public void setPowerUpsMatrix(String powerUpsMatrix[][]) {
		powerUps = powerUpsMatrix;
	}

	public void setBrickColorsMatrix(Color brickColorsMatrix[][]) {
		brickColors = brickColorsMatrix;
	}

	public void setBooleanStaggered(boolean booleanStaggered) {
		staggered = booleanStaggered;
	}

}
