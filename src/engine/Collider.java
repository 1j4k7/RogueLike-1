package engine;

import java.util.ArrayList;

public class Collider extends Component {
	
	public static ArrayList<Collider> colliders;

	public Collider(GameObject object) {
		super(object);
		if (colliders == null) colliders = new ArrayList<Collider>();
		colliders.add(this);
	}
	
	public GameObject isCollision(int x, int y) {
		for (Collider collider: colliders) {
			if (collider.parent.posX == x && collider.parent.posY == y) {
				return collider.parent;
			}
		}
		return null;
	}

}
