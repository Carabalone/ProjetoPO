package ggc.core;

public class Component {

	private int _quantity;
	private String _productId;

	public Component(int quantity, String productId) {
		_quantity = quantity;
		_productId = productId;
	}

	public int getQuantity() {
		return _quantity;
	}

	public String toString(){
		return String.format("%s:%d", _productId, _quantity);
	}
}