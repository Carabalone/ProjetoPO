package ggc.core;

import java.io.Serializable;
import java.util.*;
import ggc.core.exception.NotEnoughProductException;

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
	private DeliveryMethod _deliveryMethod;
	private boolean _newProduct;

	/**
   * @param id Product Id.
   */
	public Product(String id, TreeSet<Observer> observers) {
		_id = id;
		_maxPrice = 0;
		_lowestPrice = 0;
		_batches = new TreeSet<Batch>();
		_observers = new TreeSet<>(observers);
		_deliveryMethod = new RegisterInApp(_observers);
		_newProduct = true;
	}
	public Product(String id, TreeSet<Observer> observers, DeliveryMethod dMethod) {
		_id = id;
		_maxPrice = 0;
		_lowestPrice = 0;
		_batches = new TreeSet<Batch>();
		_observers = new TreeSet<>(observers);
		_deliveryMethod = dMethod;
		_newProduct = true;

	}

	/**
   * Compares two products to see which comes first (alphabetically by Id).
   * @param p product to compare with.
   * @return negative number if this product comes before p, positive otherwise (0 if equal).
   */
	public int compareTo(Product p){
        return _id.compareToIgnoreCase(p.getId());
    }

	public Set<Batch> getBatches(){
		return _batches;
	}

	public String getId() {
		return _id;
	}

	public double getMaxPrice() {
		return _maxPrice;
	}

	public double getLowestPrice(){
		if (!_batches.isEmpty())
			return _lowestPrice;
		else
			return _maxPrice;
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
			if(!_newProduct)
				notify(Type.NEW);
			_lowestPrice = newBatch.getPrice();
			_maxPrice = _lowestPrice;
		}
		_batches.add(newBatch);
		updatePrices(newBatch.getPrice());
		_newProduct = false;
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
	public void checkQuantity(int quantity) throws NotEnoughProductException{
		int available = checkQuantity();
		if (available < quantity)
			throw new NotEnoughProductException(_id, quantity, available);
	}

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
	public double gatherUnits(int quantity) throws NotEnoughProductException{
		double price = 0;
		Iterator<Batch> it = getBatches().iterator();

		int available = checkQuantity();
		if (available < quantity)
			throw new NotEnoughProductException(_id, quantity, available);

		while (it.hasNext()){

			Batch bt = it.next();
			if (bt.getAvailableQuantity() > quantity){
				price += (bt.getPriceOfUnits(quantity));
				bt.removeUnits(quantity);
				return price;
			}

			quantity -= bt.getAvailableQuantity();
			it.remove();
			price += bt.getPriceOfUnits(bt.getAvailableQuantity());

		}

		return price;
	}

	public double gatherUnitsSimple(int quantity) throws NotEnoughProductException{
		return 0;
	}

	public Recipe getRecipe(){
		return null;
	}

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
		Notification n = new Notification(type, this);
		_deliveryMethod.deliver(n);
	}
}