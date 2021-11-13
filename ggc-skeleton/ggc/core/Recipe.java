package ggc.core;

import java.io.Serializable;
import java.util.*;

public class Recipe implements Serializable{

	private double _commission;
	private List<Component> _components;
	private DerivedProduct _product;

	protected Recipe(List<Component> components, double commission) {
		_commission = commission;
		_components = components;
	}

	protected void addProduct(DerivedProduct product){
		_product = product;
	}

	protected DerivedProduct getProduct(){
		return _product;
	}

	protected List <Component> getComponents(){
		return _components;
	}

	protected double getCommission() {
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