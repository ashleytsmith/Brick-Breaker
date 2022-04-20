package Gameplay;

import Navigate.Frame;
import Pieces.Paddle;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class keyHandler implements KeyListener {

	private eventHandler eventHandler;
	private Paddle paddle;

	public keyHandler(eventHandler associatedEventHandler, Paddle associatedPaddle) {

		eventHandler = associatedEventHandler;
		paddle = associatedPaddle;

	}

	/*
	 * STOP AND START THE LEVEL
	 * AND MOVE THE PADDLE
	 * 
	 */

	public void keyPressed(KeyEvent e) {

		boolean play = eventHandler.getBooleanPlay();

		// Initialise the game

		if (!play) {

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {

				eventHandler.reloadMap();

			}

			// Go back to the menu

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

				paddle.resetPaddle();
				Frame.returnToMenu();
			}
		}

		// Right boundary

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			paddle.moveRight();

		}

		// Left boundary

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			paddle.moveLeft();

		}

	}

	/*
	 * EMPTY METHODS
	 * REQUIRED BY THE KeyListener INTERFACE
	 * 
	 */

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
