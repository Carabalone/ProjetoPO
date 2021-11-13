package ggc.core;

import java.io.Serializable;
import java.util.*;

/**
* Class SimpleProduct implements a product that was not made from other products.
*/
public class SimpleProduct extends Product implements Serializable{

	/**
   * @param id Product Id.
   */
	protected SimpleProduct(String id, Set<Observer> observers) {
		super(id, observers);
	}


}