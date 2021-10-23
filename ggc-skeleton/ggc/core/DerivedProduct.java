package ggc.core;

public class DerivedProduct extends Product {

	private Recipe _recipe;

	public DerivedProduct(String id, Recipe recipe) {
		super(id);
		_recipe = recipe;
	}

	public Recipe getRecipe() {
		return _recipe;
	}
	
}