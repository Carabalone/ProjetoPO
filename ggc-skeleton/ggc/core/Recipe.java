package ggc.core;

import java.util.*;

public class Recipe{

	private double _commission;
	private ArrayList<Component> _components;

	public Recipe(String strComponents, double commission) {
		_commission = commission;
		_components = new ArrayList();
		String[] cmp = strComponents.split("#");
		for (String c : cmp){
			String[] attributes = c.split(":");
			_components.add(new Component(Integer.valueOf(attributes[1]), attributes[0]));
		}
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