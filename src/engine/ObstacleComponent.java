package engine;

import java.awt.Color;

public class ObstacleComponent extends Component {

	MyGrid thisGrid;
	GameObject obstacle;
	
	public ObstacleComponent(GameObject obstacle, MyGrid grid) {
		super(obstacle);
		thisGrid = grid;
	}

	public void graphics() {
		// Set the x,y coordinate of obstacle to green
		thisGrid.setColor(obstacle.x, obstacle.y, Color.GREEN);
	}
	
	public void logic() {
		// Obstacle does nothing, just set the appropriate coordinate to green
	}
		
}
