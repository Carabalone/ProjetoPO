package ggc.core;

import java.util.*;

public class Recipe{

	private double _commission;
	private ArrayList<Component> _components;

	public Recipe(double commission) {
		_commission = commission;
		_components = new ArrayList();
	}

	public void addComponent(Component newComponent){
		_components.add(newComponent);
	}

	public ArrayList <Component> getComponents(){
		return _components;
	}

	public double getCommission() {
		return _commission;
	}

	public String toString() {
		String recipeStr = "#";
		for (Component cmp : _components) {
			recipeStr += cmp.toString() + "#";
		}
		return recipeStr.substring(0, recipeStr.length() - 1);
	}
}