package ggc.core;

import java.util.*;
public class SimpleProduct extends Product {

	public SimpleProduct(String id) {
		super(id);
	}

	@Override
	public boolean checkQuantity(int qNeeded){
		int qAvailable = 0;
		for(Batch batch : super.getBatches()){
			qAvailable += batch.getAvailableQuantity();
		}
		return qAvailable < qNeeded;
	}

	@Override
	public double gatherUnits(int quantity){
		double price = 0;
		Iterator<Batch> it = super.getBatches().iterator();
		Partner supplier;

		if (!this.checkQuantity(quantity)){
			return 0;
		}

		while (it.hasNext()){

			Batch bt = it.next();
			if (bt.getAvailableQuantity() > quantity){
				price += (quantity * bt.getUnitPrice());
				bt.removeUnits(quantity);
				return price;
			}

			quantity -= bt.getAvailableQuantity();
			price += (bt.getAvailableQuantity() * bt.getUnitPrice());
			//bt.getSupplier().removeBatch(bt);
			it.remove();

		}

		return price;
	}

}