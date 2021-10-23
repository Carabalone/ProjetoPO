package ggc.core;

public class Batch{

	private double _unitPrice;
	private int _quantity;
	private Product _product;
	private Partner _supplier;

	public Batch(double unitPrice, int quantity, Product product, Partner supplier){
		_unitPrice = unitPrice;
		_quantity = quantity;
		_product = product;
		_supplier = supplier;
	}

	public double getUnitPrice(){
		return _unitPrice;
	} 

	public int getAvailableQuantity(){
		return _quantity;
	}

	public Product getProduct(){
		return _product;
	}

	public Partner getSupplier(){
		return _supplier;
	}

	public int removeUnits(int ammount){
		_quantity -= ammount;
		return _quantity;
	}
}