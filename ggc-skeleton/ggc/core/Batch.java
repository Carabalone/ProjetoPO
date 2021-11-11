package ggc.core;
import java.util.*;

import java.io.Serializable;

/**
* Class Batch implements a batch of products.
*/
public class Batch implements Comparable<Batch>, Serializable{

	private int _quantity;
	private Product _product;
	private Partner _supplier;
	private double _unitPrice;

	/**
   * @param totalPrice price of the batch.
   * @param quantity number of units in batch.
   * @param product this batch has units of this product.
   * @param supplier partner that provided this batch.
   */
	public Batch(double unitPrice, int quantity, Product product, Partner supplier){
		_quantity = quantity;
		_product = product;
		_supplier = supplier;
		_product.updatePrices(unitPrice);
		_unitPrice = unitPrice;
	}

	/**
   * Compares two batches to see which comes first.
   * @param b batch to compare with.
   * @return negative number if this batch comes before b, positive otherwise.
   */
	public int compareTo(Batch b){
        int equal = _product.getId().compareToIgnoreCase(b.getProduct().getId());
        if (equal != 0)
        	return equal;
        equal = _supplier.getId().compareToIgnoreCase(b.getSupplier().getId());
        if (equal != 0)
        	return equal;
        equal = Double.compare(_unitPrice, b.getPrice());
        if (equal != 0)
        	return equal;
        return Double.compare(_quantity, b.getAvailableQuantity());
    }

	public int getAvailableQuantity(){
		return _quantity;
	}

	public double getPrice(){
		return _unitPrice;
	}

	public double getPriceOfUnits(int units){
		return _unitPrice * units; 
	}

	public Product getProduct(){
		return _product;
	}

	public Partner getSupplier(){
		return _supplier;
	}

	/**
   * Removes a certain ammount of units from the batch.
   * @param ammount number of units to remove.
   * @return quantity still available.
   */
	public int removeUnits(int amount){
		_quantity -= amount;
		return _quantity;
	}

	/**
   * Makes a copy of the Batch.
   * @return a new Batch with same values as this.
   */
	protected Batch makeCopy(){
        return new Batch(_unitPrice, _quantity, _product, _supplier);
    }

    /**
   * Converts batch into its display form.
   * @return a string in the form of "ProductId|SupplierID|Price|AvailableUnits".
   */
    public String toString(){
    	return String.format("%s|%s|%d|%d", _product.getId(), _supplier.getId(), Math.round(_unitPrice), _quantity);
    }
}