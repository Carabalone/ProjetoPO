package ggc.core;

import java.util.*;

public abstract class Product {
	
	private double _maxPrice;
	private double _lowestPrice;
	private String _id;
	private ArrayList<Batch> _batches;

	public Product(String id) {
		_id = id;
		_maxPrice = 0;
		_lowestPrice = 0;
		_batches = new ArrayList();
	}

	public String getId() {
		return _id;
	}

	public double getMaxPrice() {
		return _maxPrice;
	}

	public void updatePrices(double newPrice) {
		if (newPrice < _lowestPrice) {
			_lowestPrice = newPrice;
		}
		else if (newPrice > _maxPrice) {
			_maxPrice = newPrice;
		}
	}

	public void addBatch(Batch newBatch){
		_batches.add(newBatch);
	}

	public void removeBatch(Batch emptyBatch){
		_batches.remove(emptyBatch);
	}

	// returns True if there are at least "quantity" units
	abstract boolean checkQuantity(int quantity);

	// returns price of units gathered (0 if not enough units)
	abstract double gatherUnits(int quantity);
}