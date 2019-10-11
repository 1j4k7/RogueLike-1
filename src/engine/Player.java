package engine;

public class Player extends GameObject {
	
	int posX, posY;
	
	// Starting Player in bottom left position
	public Player() {
		this.x = 0;
		this.y = MyGrid.HEIGHT-1;
	}
}
