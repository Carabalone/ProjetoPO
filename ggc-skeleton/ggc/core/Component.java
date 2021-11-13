package ggc.core;

import java.io.Serializable;

public class Component implements Serializable{

	private int _quantity;
	private Product _product;

	protected Component(int quantity, Product product) {
		_quantity = quantity;
		_product = product;
	}

	protected int getQuantity() {
		return _quantity;
	}

	protected String getProductId(){
		return _product.getId();
	}

	protected Product getProduct(){
		return _product;
	}

	public String toString(){
		return String.format("%s:%d", getProductId(), _quantity);
	}
}