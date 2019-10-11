package engine;

import java.awt.Color;
import java.util.ArrayList;

public class Game {
	
	private int width;
	private int height;
	public MyGrid grid;
	public ArrayList<GameObject> gameObjects;
//	private InputHandler input;
	private GraphicsThread graphics;
	private LogicThread logic;
	
	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new MyGrid(height);
		this.gameObjects = new ArrayList<GameObject>();
//		this.input = new InputHandler(this);
//		grid.addKeyListener(input);
		grid.setFocusable(true);
		setInitialGridComponents();
	}
	
	public void runGame() {
		logic.start();
		graphics.start();
	}
	
	private void setInitialGridComponents() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grid.setColor(i, j, Color.WHITE);
			}
		}
	}

}

class GraphicsThread extends Thread {
	
	private Game game;
	
	public GraphicsThread(Game game) {
		super();
		this.game = game;
	}
	
	public void run() {
		while (true) {
			for (GameObject obj: game.gameObjects) {
				obj.graphics();
			}
			game.grid.repaint();
		}
	}
}

class LogicThread extends Thread {
	
	private Game game;
	
	public LogicThread(Game game) {
		super();
		this.game = game;
	}
	
	public void run() {
		while (true) {
			for (GameObject obj: game.gameObjects) {
				obj.logic();
			}
		}
	}
}