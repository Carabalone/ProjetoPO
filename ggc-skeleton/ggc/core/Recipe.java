package ggc.core;

import java.io.Serializable;
import java.util.*;

public class Recipe implements Serializable{

	private double _commission;
	private List<Component> _components;
	private DerivedProduct _product;

	public Recipe(List<Component> components, double commission) {
		_commission = commission;
		_components = components;
	}

	public void addProduct(DerivedProduct product){
		_product = product;
	}

	public DerivedProduct getProduct(){
		return _product;
	}

	public List <Component> getComponents(){
		return _components;
	}

	public double getCommission() {
		return _commission;
	}

	public String toString() {
		String recipeStr = "";
		for (Component cmp : _components) {
			recipeStr += cmp.toString() + "#";
		}
		return recipeStr.substring(0, recipeStr.length() - 1);
	}
}