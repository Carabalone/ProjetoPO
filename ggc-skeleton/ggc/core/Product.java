package ggc.core;

import java.io.Serializable;
import java.util.*;

/**
* Class Product serves only to implement generic attributes and methods.
* @see ggc.core.SimpleProduct
* @see ggc.core.DerivedProduct
*/
public abstract class Product implements Comparable<Product>, Serializable, Subject{
	
	private double _maxPrice;
	private double _lowestPrice;
	private String _id;
	private Set<Batch> _batches;
	//TODO we can substitute this arrayList to a HashMap, since we are going to be removing/adding partners a lot of times
	private TreeSet<Observer> _observers;

	/**
   * @param id Product Id.
   */
	public Product(String id, TreeSet<Observer> observers) {
		_id = id;
		_maxPrice = 0;
		_lowestPrice = 0;
		_batches = new TreeSet<Batch>();
		_observers = new TreeSet<>(observers);
	}

	/**
   * Compares two products to see which comes first (alphabetically by Id).
   * @param p product to compare with.
   * @return negative number if this product comes before p, positive otherwise (0 if equal).
   */
	public int compareTo(Product p){
        return _id.compareToIgnoreCase(p.getId());
    }

	public List<Batch> getBatches(){
		return new ArrayList<Batch>(_batches);
	}

	public String getId() {
		return _id;
	}

	public double getMaxPrice() {
		return _maxPrice;
	}

	public double getLowestPrice(){
		return _lowestPrice;
	}

	/**
   * Checks if a new price is now the highest or lowest and updates it if it is.
   * @param newPrice price to check.
   */
	public void updatePrices(double newPrice) {
		if (newPrice < _lowestPrice){
			_lowestPrice = newPrice;
			notify(Type.BARGAIN);
		}
		else if (newPrice > _maxPrice){
			_maxPrice = newPrice;
		}
	}

	protected void addBatch(Batch newBatch){
		if (_batches.isEmpty()){
			notify(Type.NEW);
			_lowestPrice = newBatch.getPrice();
			_maxPrice = _lowestPrice;
		}
		_batches.add(newBatch);
		updatePrices(newBatch.getPrice());
	}

	protected void removeBatch(Batch emptyBatch){
		_batches.remove(emptyBatch);
		// prices are reseted so we when a new batch comes, the prices will be updated accordingly 
		if(_batches.isEmpty()){
			_lowestPrice = 0;
			_maxPrice = 0;
		}
	}

	protected void addObserver(Observer o){
		_observers.add(o);
	}

	protected void removeObserver(Observer o){
		_observers.remove(o);
	}

	protected boolean hasObserver(Observer o){
		return _observers.contains(o);
	}

	/**
   * Checks how many availble units there are in all batches of this product.
   * @return total available units.
   */
	public int checkQuantity(){
		int qAvailable = 0;
		for(Batch batch : _batches){
			qAvailable += batch.getAvailableQuantity();
		}
		return qAvailable;
	}

	/**
   * Allocates a certain ammount of units by removing them from the Batches and deleting empty batches.
   * @param quantity number of units to allocate
   * @return price of buying allocated units (0 if there weren't enough units).
   */
	abstract double gatherUnits(int quantity);

	/**
   * Converts product into its display form.
   * @return a string in the form of "ProductId|MAXPrice|NumberAvailableUnits".
   */
	public String toString(){
		return String.format("%s|%d|%d", _id, Math.round(_maxPrice), this.checkQuantity());
	}

	@Override
	public void registerObserver(Observer o){
		_observers.add(o);
	}

	public void unregisterObserver(Observer o){
		_observers.remove(o);
	}

	public void notify(Type type){
		Notification not = new Notification(type, this);
		for (Observer obs: _observers){
			obs.update(not);
		}
	}
}