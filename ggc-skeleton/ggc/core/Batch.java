package ggc.core;

import java.io.Serializable;

public class Batch implements Serializable{

	private double _totalPrice;
	private int _quantity;
	private Product _product;
	private Partner _supplier;

	public Batch(double totalPrice, int quantity, Product product, Partner supplier){
		_totalPrice = totalPrice;
		_quantity = quantity;
		_product = product;
		_supplier = supplier;
		_product.updatePrices(totalPrice);
	}

	public double getPrice(){
		return _totalPrice;
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

	protected Batch makeCopy(){
        return new Batch(_totalPrice, _quantity, _product, _supplier);
    }

    // TODO check rounding of prices
    public String toString(){
    	return String.format("%s|%s|%d|%d", _product.getId(), _supplier.getId(), Math.round(_totalPrice), _quantity);
    }
}