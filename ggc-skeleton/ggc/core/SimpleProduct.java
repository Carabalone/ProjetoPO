package ggc.core;

import java.io.Serializable;
import java.util.*;

/**
* Class SimpleProduct implements a product that was not made from other products.
*/
public class SimpleProduct extends Product implements Serializable{

	/**
   * @param id Product Id.
   */
	public SimpleProduct(String id, TreeSet<Observer> observers) {
		super(id, observers);
	}

	/**
   * Implements the abstract method described in super class.
   * @see ggc.core.Product.gatherUnits super class method
   * @param quantity number of units to allocate
   * @return price of buying allocated units (0 if there weren't enough units).
   */
	@Override
	public double gatherUnits(int quantity){
		double price = 0;
		Iterator<Batch> it = super.getBatches().iterator();
		Partner supplier;

		if (this.checkQuantity() == 0){
			return 0;
		}

		while (it.hasNext()){

			Batch bt = it.next();
			if (bt.getAvailableQuantity() > quantity){
				price += (bt.getPriceOfUnits(quantity));
				bt.removeUnits(quantity);
				return price;
			}

			quantity -= bt.getAvailableQuantity();
			it.remove();
			price += bt.getPriceOfUnits(bt.getAvailableQuantity());

		}

		return price;
	}
}