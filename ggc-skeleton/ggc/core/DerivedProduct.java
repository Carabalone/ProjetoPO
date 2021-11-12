package ggc.core;

import java.io.Serializable;
import java.util.*;

/**
* Class DerivedProduct implements a product that was made from other products.
*/
public class DerivedProduct extends Product implements Serializable{

	private Recipe _recipe;

	/**
   * @param id Product Id.
   * @param recipe Recipe object that includes components necessary to make a unit of this product
   */
	public DerivedProduct(String id, Recipe recipe, TreeSet<Observer> observers){
		super(id, observers);
		_recipe = recipe;
	}

	public Recipe getRecipe(){
		return _recipe;
	}

	//TODO
	/*@Override
	public double gatherUnits(int quantity){
		return 0;
	}*/

	/**
   * Converts DerivedProduct into its display form.
   * @return a string in the form of "ProductId|MAXPrice|NumberAvailableUnits|StringRecipe".
   * @see ggc.core.Recipe.toString() for more information on display version of Recipe.
   */
	@Override
	public String toString(){
		return String.format("%s|%s", super.toString(), _recipe.toString());
	}	
}