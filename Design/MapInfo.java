package Design;

import java.awt.Color;

/*
* DATA CONTAING CLASS USED
* FOR SAVING AND LOADING MAP DATA
 */

public class MapInfo {

	private int occupiedorNot[][];
	private String powerUpsMatrix[][];
	private Color brickColorsMatrix[][];
	private boolean staggered;

	public void setOccupationMatrix(int OccupationMatrixFromFile[][]) {
		occupiedorNot = OccupationMatrixFromFile;
	}

	public void setPowerUpsMatrix(String powerUpsMatrixFromFile[][]) {
		powerUpsMatrix = powerUpsMatrixFromFile;
	}

	public void setBrickColorsMatrix(Color brickColorsMatrixFromFile[][]) {
		brickColorsMatrix = brickColorsMatrixFromFile;
	}

	public void setBooleanStaggered(boolean BooleanStaggeredFromFile) {
		staggered = BooleanStaggeredFromFile;
	}

	public int[][] getOccupationMatrix() {
		return occupiedorNot;
	}

	public String[][] getPowerUpsMatrix() {
		return powerUpsMatrix;
	}

	public Color[][] getBrickColorsMatrix() {
		return brickColorsMatrix;
	}

	public boolean getBooleanStaggered() {
		return staggered;
	}

}
