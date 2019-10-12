package engine;

/*
 * Who did what
 * 
 * Jason Kuo: Run.java, GameObject.java, Game.java, InputHandler.java
 * Fritz Wiltman: Player.java, PlayerComponent.java, Obstacle.java, ObstacleComponent.java
 * Paul Hendriksen: Adversary.java, AdversaryPath.java, Bullet.java, BulletMovement.java
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Game {
	
	private int width;
	private int height;
	public MyGrid grid;
	public ArrayList<GameObject> gameObjects;
	public Player player;
	public Adversary adversary;
	private InputHandler input;
	public LinkedList<Integer> keyPresses;
	private GraphicsThread graphics;
	private LogicThread logic;
	
	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new MyGrid(height);
		this.gameObjects = new ArrayList<GameObject>();
		this.input = new InputHandler(this);
		grid.addKeyListener(input);
		this.keyPresses = new LinkedList<Integer>();
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
//				grid.setColor(i, j, Color.WHITE);
				Tile temp = new Tile(j,i);
				temp.addComponent(new TileComponent(temp, grid));
				gameObjects.add(temp);
			}
		}
		
		//Create and add the player GameObject
		player = new Player(grid);
		player.addComponent(new PlayerComponent(player, grid, keyPresses));
		player.addComponent(new Collider(player));
//		Add Component here------------------------
		
		
		
		
//		---------------------------
		gameObjects.add(player);
		
		//Create and add the adversary GameObject
		adversary = new Adversary(width, height, grid);
//		adversary.addComponent(new AdversaryPath(adversary, grid));
		adversary.addComponent(new Collider(adversary));
		gameObjects.add(adversary);
		
		//Create and add the obstacles
		for (int i = 0; i < (int)(0.1f*height*width); i++) {
			Obstacle temp = new Obstacle((int)(width*Math.random()),(int)(height*Math.random()));
			temp.addComponent(new ObstacleComponent(temp,grid));
			temp.addComponent(new Collider(temp));
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
			ArrayList<GameObject> temp = new ArrayList<GameObject>();
			for (GameObject obj: game.gameObjects) {
				temp.add(obj);
			}
			temp.sort(null);
			for (GameObject obj: temp) {
				obj.graphics();
			}
			game.grid.repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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