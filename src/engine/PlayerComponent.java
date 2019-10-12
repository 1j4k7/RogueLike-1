package engine;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class PlayerComponent extends Component {

	MyGrid thisGrid;
	GameObject player;
	LinkedList<Integer> keyPresses;
	
	public PlayerComponent(GameObject player, MyGrid grid, LinkedList<Integer> keyPresses) {
		super(player);
		thisGrid = grid;
		this.player = player;
		this.keyPresses = keyPresses;
		this.Priority = 2;
		player.priority = Math.max(this.Priority, player.priority);
	}
	
	public void graphics() {
		// Make a red square, move if player moves (?)
		thisGrid.setColor(player.posY, player.posX, Color.RED);
	}
	
	public void logic() {
		if (!keyPresses.isEmpty()) {
			int code = keyPresses.pollFirst();
			if (code == KeyEvent.VK_W) {
				player.posY--;
			} else if (code == KeyEvent.VK_A) {
				player.posX--;
			} else if (code == KeyEvent.VK_S) {
				player.posY++;
			} else if (code == KeyEvent.VK_D) {
				player.posX++;
			}
		}
		
		// Can shoot
			// Create bullet (in constructor for bullet, have initial direction that is passed in), 
			// list bullet to the the list of GameObjects, put in front of direction (might have to make Game public)
		
	}
	
}
