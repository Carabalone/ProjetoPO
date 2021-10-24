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

	@Override
	public boolean checkQuantity(int qNeeded){
		int qAvailable = 0;
		for(Batch batch : super._batches){
			qAvailable += batch.getAvailableQuantity();
		}
		return qAvailable < qNeeded;
	}
	
}