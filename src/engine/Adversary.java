package engine;

import java.util.ArrayList;

public class Adversary extends GameObject{
	
	public Adversary(int width, int height, MyGrid grid) {
		this.components = new ArrayList<Component>();
		this.posX = width - 1;
		this.posY = 0;
	}
	
	

}
