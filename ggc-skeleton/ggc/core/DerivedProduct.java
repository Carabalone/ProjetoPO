package ggc.core;

import java.util.*;

public class DerivedProduct extends Product {

	private Recipe _recipe;

	public DerivedProduct(String id, Recipe recipe) {
		super(id);
		_recipe = recipe;
	}

	public Recipe getRecipe() {
		return _recipe;
	}

	//TODO
	@Override
	public double gatherUnits(int quantity){
		return 0;
	}

	//TODO check last field (agravamento)
	@Override
	public String toString(){
		return String.format("%s|%s", super.toString(), _recipe.toString());
	}
	
}