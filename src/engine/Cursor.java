package engine;

import java.awt.Color;

public class Cursor {
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	public Cursor(int width, int height) {
		this.x = 0;
		this.y = 0;
		this.width = width;
		this.height = height;
		this.color = Color.RED;
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public Color getColor() {
		return this.color;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void moveLeft() {
		if (this.x - 1 >= 0)
			this.x--;
	}
	public void moveRight() {
		if (this.x + 1 < width)
			this.x++;
	}
	public void moveUp() {
		if (this.y - 1 >= 0)
			this.y--;
	}
	public void moveDown() {
		if (this.y + 1 < height)
			this.y++;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
