package engine;

import java.util.ArrayList;

public class Adversary extends GameObject{
	private int width, 
				height;
	private ArrayList<GameObject> gameObjects;
	public Adversary(int width, int height, MyGrid grid, ArrayList<GameObject> gameObjects) {
		this.components = new ArrayList<Component>();
		this.posX = width - 1;
		this.posY = 0;
		this.height = height;
		this.width = width;
		this.gameObjects = gameObjects;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
