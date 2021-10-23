package ggc.core;


public abstract class Product {
	
	private double _maxPrice;
	private double _lowestPrice;
	private String _id;

	public Product(String id) {
		_id = id;
		_maxPrice = 0;
		_lowestPrice = 0;
	}

	public String getId() {
		return _id;
	}

	public double getMaxPrice() {
		return _maxPrice;
	}

	public void UpdatePrices(double newPrice) {
		if (newPrice < _lowestPrice) {
			_lowestPrice = newPrice;
		}
		else if (newPrice > _maxPrice) {
			_maxPrice = newPrice;
		}
	}
}