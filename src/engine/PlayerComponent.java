package engine;

import java.awt.Color;

public class PlayerComponent extends Component {

	MyGrid thisGrid;
	GameObject player;
	
	public PlayerComponent(GameObject player, MyGrid grid) {
		super(player);
		thisGrid = grid;
		this.player = player;
	}
	
	public void graphics() {
		// Make a red square, move if player moves (?)
		thisGrid.setColor(player.posY, player.posX, Color.RED);
	}
	
	public void logic() {
		// Can use WASD to move
			// Keylistener (instantiate in grid or main) - player comp would know to look at keylistener
//		 if (game.input)
		
		// Can shoot
			// Create bullet (in constructor for bullet, have initial direction that is passed in), 
			// list bullet to the the list of GameObjects, put in front of direction (might have to make Game public)
		
	}
	
}
