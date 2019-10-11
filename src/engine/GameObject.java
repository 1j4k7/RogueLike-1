package engine;

import java.util.ArrayList;

public abstract class GameObject {
	
	private ArrayList<Component> components;
	private int x,y;
	
	public void graphics() {
		for (Component component: components) {
			component.graphics();
		}
	}
	
	public void logic() {
		for (Component component: components) {
			component.logic();
		}
	}
	
	public void addComponent(Component component) {
		components.add(component);
	}
	
	public void removeComponent(Component component) {
		components.remove(component);
	}

}
