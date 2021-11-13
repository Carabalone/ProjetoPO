package ggc.core;

import java.io.Serializable;
import java.util.*;
import ggc.core.exception.NotEnoughProductException;

/**
* Class DerivedProduct implements a product that was made from other products.
*/
public class DerivedProduct extends Product implements Serializable{

	private Recipe _recipe;

	/**
   * @param id Product Id.
   * @param recipe Recipe object that includes components necessary to make a unit of this product
   */
	public DerivedProduct(String id, Recipe recipe, Set<Observer> observers){
		super(id, observers);
		_recipe = recipe;
	}

	@Override
	protected Recipe getRecipe(){
		return _recipe;
	}

	@Override
	protected void checkQuantity(int amount) throws NotEnoughProductException{

		int available = checkQuantity();
		if (amount > available){
			amount -= available;
			for (Component c : getRecipe().getComponents()){
				int cmpQuantity = amount * c.getQuantity();
				c.getProduct().checkQuantity(cmpQuantity);
			}
		}
	}

	@Override
	protected double gatherUnits(int quantity) throws NotEnoughProductException{
		
		checkQuantity(quantity);

		return 0;
	}

	@Override
	protected double gatherUnitsSimple(int quantity) throws NotEnoughProductException{
		return super.gatherUnits(quantity);
	}

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