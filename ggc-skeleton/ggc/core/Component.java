package ggc.core;

public class Component {

	private int _quantity;
	private Product _product;

	public Component(int quantity, Product product) {
		_quantity = quantity;
		_product = product;
	}

	public int getQuantity() {
		return _quantity;
	}

	public String toString(){
		return String.format("%s:%d", _product.getId(), _quantity);
	}
}