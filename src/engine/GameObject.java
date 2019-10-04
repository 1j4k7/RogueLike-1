package engine;

import java.util.ArrayList;

public abstract class GameObject {
	
	private ArrayList<Component> components;
	
	public void update() {
		for (Component component: components) {
			component.update();
		}
	}
	
	public void addComponent(Component component) {
		components.add(component.ID, component);
	}
	
	public void removeComponent(int ID) {
		components.removeIf(component -> (component.ID == ID));
	}

}
