package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	
	private Game game;
	
	public InputHandler(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if (code == KeyEvent.VK_P) {
			game.pause();
		} else if (code == KeyEvent.VK_LEFT) {
			game.getCursor().moveLeft();
		} else if (code == KeyEvent.VK_RIGHT) {
			game.getCursor().moveRight();
		} else if (code == KeyEvent.VK_UP) {
			game.getCursor().moveUp();
		} else if (code == KeyEvent.VK_DOWN) {
			game.getCursor().moveDown();
		} else if (code == KeyEvent.VK_SPACE) {
			game.toggleTile(game.getCursor().getX(), game.getCursor().getY());
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
