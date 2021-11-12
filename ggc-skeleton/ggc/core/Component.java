package ggc.core;

import java.io.Serializable;

public class Component implements Serializable{

	private int _quantity;
	private Product _product;

	public Component(int quantity, Product product) {
		_quantity = quantity;
		_product = product;
	}

	public int getQuantity() {
		return _quantity;
	}

	public String getProductId(){
		return _product.getId();
	}

	public Product getProduct(){
		return _product;
	}

	public String toString(){
		return String.format("%s:%d", getProductId(), _quantity);
	}
}