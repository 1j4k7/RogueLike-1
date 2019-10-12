package engine;

import java.awt.Color;
import java.util.ArrayList;

public class Game {
	
	private int width;
	private int height;
	public MyGrid grid;
	public ArrayList<GameObject> gameObjects;
	public Player player;
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
		logic = new LogicThread(this);
		graphics = new GraphicsThread(this);
		setInitialGridComponents();
	}
	
	public void runGame() {
		logic.start();
		graphics.start();
	}
	
	private void setInitialGridComponents() {
		
		//Set all tiles to white initially
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grid.setColor(i, j, Color.WHITE);
			}
		}
		
		//Create and add the player GameObject
		player = new Player(grid);
		player.addComponent(new PlayerComponent(player, grid));
//		Add Component here------------------------
		
		
		
		
//		---------------------------
		gameObjects.add(player);
		
		//Create and add the adversary GameObject
		
		//Create and add the obstacles
		for (int i = 0; i < (int)(0.1f*height*width); i++) {
			Obstacle temp = new Obstacle((int)(width*Math.random()),(int)(height*Math.random()));
			temp.addComponent(new ObstacleComponent(temp,grid));
			gameObjects.add(temp);
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
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}