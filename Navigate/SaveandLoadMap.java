package Navigate;

import java.awt.Color;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

import Design.MapInfo;

public class SaveandLoadMap {

	private String mapsFolder = "Maps/";
	private MapInfo mapinfo = new MapInfo();

	private int rows = 8;
	private int columns = 8;
	private int occupiedOrNot[][] = new int[rows][columns];
	private String powerUpsMatrix[][] = new String[rows][columns];
	private String brickColorsMatrixStringForm[][] = new String[rows][columns];
	private Color brickColorsMatrix[][] = new Color[rows][columns];

	public void save(String filename, int occupiedOrNot[][], String powerUpsMatrix[][], Color brickColorsMatrix[][],
			boolean staggered) {

		try {

			BufferedWriter outputWriter = null;
			outputWriter = new BufferedWriter(new FileWriter(mapsFolder + filename));

			for (int i = 0; i < occupiedOrNot.length; i++) {

				for (int j = 0; j < occupiedOrNot[0].length; j++) {

					outputWriter.write(occupiedOrNot[i][j] + ",");

				}

				outputWriter.newLine();

			}

			for (int i = 0; i < powerUpsMatrix.length; i++) {

				for (int j = 0; j < powerUpsMatrix[0].length; j++) {

					outputWriter.write(powerUpsMatrix[i][j] + ",");

				}

				outputWriter.newLine();

			}

			brickColorsMatrixStringForm = ConvertColorsToStrings(brickColorsMatrix);

			for (int i = 0; i < brickColorsMatrixStringForm.length; i++) {

				for (int j = 0; j < brickColorsMatrixStringForm[0].length; j++) {

					outputWriter.write(brickColorsMatrixStringForm[i][j] + ",");

				}

				outputWriter.newLine();

			}

			outputWriter.write(String.valueOf(staggered));
			outputWriter.flush();
			outputWriter.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public MapInfo load(String filename) {

		Scanner sc;

		try {

			sc = new Scanner(new BufferedReader(new FileReader(mapsFolder + filename)));

			for (int i = 0; i < occupiedOrNot.length; i++) {

				String[] line = sc.nextLine().trim().split(",");

				for (int j = 0; j < line.length; j++) {

					occupiedOrNot[i][j] = Integer.parseInt(line[j]);

				}
			}

			for (int i = 0; i < powerUpsMatrix.length; i++) {

				String[] line = sc.nextLine().trim().split(",");

				for (int j = 0; j < line.length; j++) {

					powerUpsMatrix[i][j] = line[j];

				}
			}

			for (int i = 0; i < brickColorsMatrixStringForm.length; i++) {

				String[] line = sc.nextLine().trim().split(",");

				for (int j = 0; j < line.length; j++) {

					brickColorsMatrixStringForm[i][j] = line[j];

				}
			}

			brickColorsMatrix = ConvertStringsToColors(brickColorsMatrixStringForm);

			Boolean staggered = Boolean.valueOf(sc.nextLine().trim());

			mapinfo.setOccupationMatrix(occupiedOrNot);
			mapinfo.setPowerUpsMatrix(powerUpsMatrix);
			mapinfo.setBrickColorsMatrix(brickColorsMatrix);
			mapinfo.setBooleanStaggered(staggered);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return mapinfo;
	}

	public Color[][] ConvertStringsToColors(String brickColorsMatrixStringForm[][]) {

		Hashtable<String, Color> lookup = new Hashtable<String, Color>();
		lookup.put("blue", Color.BLUE);
		lookup.put("red", Color.RED);
		lookup.put("yellow", Color.YELLOW);

		for (int i = 0; i < brickColorsMatrixStringForm.length; i++) {

			for (int j = 0; j < brickColorsMatrixStringForm[0].length; j++) {

				brickColorsMatrix[i][j] = lookup.get(brickColorsMatrixStringForm[i][j].toLowerCase());

			}
		}

		return brickColorsMatrix;

	}

	public String[][] ConvertColorsToStrings(Color brickColorsMatrix[][]) {

		Hashtable<Color, String> lookup = new Hashtable<Color, String>();
		lookup.put(Color.blue, "blue");
		lookup.put(Color.red, "red");
		lookup.put(Color.yellow, "yellow");

		for (int i = 0; i < brickColorsMatrix.length; i++) {

			for (int j = 0; j < brickColorsMatrix[0].length; j++) {

				brickColorsMatrixStringForm[i][j] = lookup.get(brickColorsMatrix[i][j]);

			}
		}

		return brickColorsMatrixStringForm;

	}

}
