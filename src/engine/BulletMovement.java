package engine;

import java.awt.Color;
import java.util.ArrayList;

public class BulletMovement extends Component{
	
	private MyGrid grid;
	private int lastDirection;
	Game game;
	
	public BulletMovement(Bullet parent, MyGrid grid, Game game) {
		super(parent);
		lastDirection = parent.getDirection();
		this.grid = grid;
		this.game = game;
	}
	
	public void graphics() {
		grid.setColor(parent.posY, parent.posX, Color.BLUE);
	}
	
	public void logic() {
		int tempX = parent.posX;
		int tempY = parent.posY;
		int nextX = parent.posX;
		int nextY = parent.posY;
		if (lastDirection == 1) {
			nextY--;
			tempY = nextY;
			nextY--;
//		} else if (lastDirection == 2) {
//			parent.posX++;
//			parent.posY--;
		} else if (lastDirection == 3) {
			nextX++;
			tempX = nextX;
			nextX++;
//		} else if (lastDirection == 4) {
//			parent.posX++;
//			parent.posY++;
		} else if (lastDirection == 5) {
			nextY++;
			tempY = nextY;
			nextY++;
//		} else if (lastDirection == 6) {
//			parent.posY++;
//			parent.posX--;
		} else if (lastDirection == 7) {
			nextX--;
			tempX = nextX;
			nextX--;
//		} else {
//			parent.posX--;
//			parent.posY--;
		}
		if (outOfBounds(nextX, nextY)) {
			destroyBullet();
			return;
		}
		parent.posX = nextX;
		parent.posY = nextY;
		ArrayList<GameObject> collisions = Collider.collidesWith(tempX, tempY);
		for (GameObject obj: collisions) {
			if (obj instanceof Obstacle) {
				destroyBullet();
				return;
			}
			if (obj instanceof Adversary) {
				destroyAdversary((Adversary) obj);
				destroyBullet();
				return;
			}
		}
		collisions = Collider.collidesWith(parent.posX, parent.posY);
		for (GameObject obj: collisions) {
			if (obj instanceof Obstacle) {
				destroyBullet();
				return;
			}
			if (obj instanceof Adversary) {
				destroyAdversary((Adversary) obj);
				destroyBullet();
				return;
			}
		}
	}
	
	public void destroyBullet() {
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects = (ArrayList<GameObject>) game.gameObjects.clone();
		gameObjects.remove(parent);
		game.gameObjects = gameObjects;
	}
	
	public void destroyAdversary(Adversary adv) {
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects = (ArrayList<GameObject>) game.gameObjects.clone();
		gameObjects.remove(adv);
		game.gameObjects = gameObjects;
	}
	
	public boolean outOfBounds(int x, int y) {
		return (x < 0 || y < 0 || x >= grid.getWd() || y >= grid.getHt());
	}
}
