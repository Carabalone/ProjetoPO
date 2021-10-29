package ggc.core;

import java.io.Serializable;
import java.util.*;

public abstract class Product implements Comparable<Product>, Serializable{
	
	private double _maxPrice;
	private double _lowestPrice;
	private String _id;
	private TreeSet<Batch> _batches;

	public Product(String id) {
		_id = id;
		_maxPrice = 0;
		_lowestPrice = 0;
		_batches = new TreeSet();
	}

	public int compareTo(Product p){
        return _id.compareTo(p.getId());
    }

	public ArrayList<Batch> getBatches(){
		return new ArrayList(_batches);
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

	// returns number of units available
	public int checkQuantity(){
		int qAvailable = 0;
		for(Batch batch : _batches){
			qAvailable += batch.getAvailableQuantity();
		}
		return qAvailable;
	}

	// returns price of units gathered (0 if not enough units)
	abstract double gatherUnits(int quantity);

	public String toString(){
		return String.format("%s|%d|%d", _id, Math.round(_maxPrice), this.checkQuantity());
	}
}