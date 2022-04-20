package Maths;

import java.util.Random;

public abstract class Maths {

	// Incrementing values

	final static int DEFAULT_VALUE = 1;
	final static int MAX_VALUE = 3;

	private static Random rand = new Random();

	/*
	 * FUNCTIONS USED TO PRODUCE
	 * A STAGGERED MAP TYPE
	 */

	public static int zerowhenEven(int number) {

		if (number % 2 == 0) {

			return 0;

		}

		else {

			return 1;

		}

	}

	public static int ifBooleanTrue(boolean staggered) {

		if (staggered == false) {

			return 0;

		}

		else {

			return 1;

		}
	}

	public static int zeroOnLastColumnWhenOdd(int length, int rowindex, int colindex) {

		if ((rowindex % 2 != 0 & colindex < length - 1 || rowindex % 2 == 0)) {

			return 0;

		}

		else {

			return -1;

		}
	}

	/*
	 * FUNCTIONS USED TO
	 * INCREMENT VALUES
	 */

	public static int increaseValueInConstantIncrements(int currentValue) {

		if (currentValue < MAX_VALUE) {

			currentValue += 1;

		}

		return currentValue;

	}

	public static int decreaseValueInConstantIncrements(int currentValue) {

		if (currentValue > DEFAULT_VALUE) {

			currentValue -= 1;

		}

		return currentValue;

	}

	public static int randomValueConstantIncrements() {

		return rand.nextInt(MAX_VALUE) + 1;

	}

	public static int randomValueOneOrMinusOne() {

		int randomNumber = rand.nextInt(2) - 1;

		if (randomNumber == 0) {

			randomNumber += 1;

		}

		return randomNumber;

	}

}
