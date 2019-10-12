
/*
 * Who did what
 * 
 * Jason Kuo: Run.java, GameObject.java, Game.java, InputHandler.java
 * Fritz Wiltman: Player.java, PlayerComponent.java, Obstacle.java, ObstacleComponent.java
 * Paul Hendriksen: Adversary.java, AdversaryPath.java, Bullet.java, BulletMovement.java
 */

import java.awt.Color;
import java.util.ArrayList;

public class Game {
	
	private int width;
	private int height;
	public MyGrid grid;
	public boolean[][] hasBlock;
	public ArrayList<GameObject> gameObjects;
	public Player player;
	public Adversary adversary;
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
		hasBlock = new boolean[grid.getHt()][grid.getWd()];
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
				hasBlock[i][j] = false;
			}
		}
		
		//Create and add the player GameObject
		player = new Player(grid);
		player.addComponent(new PlayerComponent(player, grid));
		player.addComponent(new Collider(player));
//		Add Component here------------------------
		
		
		
		
//		---------------------------
		gameObjects.add(player);
		
		//Create and add the obstacles
		for (int i = 0; i < (int)(0.1f*height*width); i++) {
			int y = (int)(height*Math.random()),
				x = (int)(width*Math.random());
			System.out.println("x is " + x + "and y is " + y);
			Obstacle temp = new Obstacle(x, y);
			temp.addComponent(new ObstacleComponent(temp,grid));
			temp.addComponent(new Collider(temp));
			gameObjects.add(temp);
			hasBlock[y][x] = true;
		}
		
		
		//Create and add the adversary GameObject
		adversary = new Adversary(width, height, grid);
		adversary.addComponent(new AdversaryPath(adversary, grid, hasBlock));
		adversary.addComponent(new Collider(adversary));
		gameObjects.add(adversary);
		
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