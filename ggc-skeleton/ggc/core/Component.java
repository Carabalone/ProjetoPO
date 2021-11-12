package ggc.core;

import java.io.Serializable;

public class Component implements Serializable{

	private int _quantity;
	private String _productId;

	public Component(int quantity, String productId) {
		_quantity = quantity;
		_productId = productId;
	}

	public int getQuantity() {
		return _quantity;
	}

	public String getProductId(){
		return _productId;
	}

	public String toString(){
		return String.format("%s:%d", _productId, _quantity);
	}
}