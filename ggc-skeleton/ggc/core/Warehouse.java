package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.io.IOException;
import ggc.core.exception.BadEntryException;
import java.util.*;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  // FIXME define attributes
  private Date _date = Date.now();
  private int _nextTransactionId = 0;
  private List<Product> _products;
  private List<Partner> _partners;
  private List<Transaction> _transactions;
  private double _balance;


  // FIXME define contructor(s)
  protected Warehouse(){
    _products = new ArrayList();
    _partners = new ArrayList();
    _transactions = new ArrayList();
    _balance = 0;
  }

  protected void addPartner(Partner partner){
    _partners.add(partner);
  }

  protected void addProduct(Product product){
    _products.add(product);
  }

  protected void addTransaction(Transaction transaction){
    _transactions.add(transaction);
  }

  // FIXME define methods
  //TODO define exception
  protected Partner getPartner(String id){
    for (Partner partner: _partners){
      if(partner.getId().equals(id)){
        return partner;
      }
    }
    //FIXME must throw exception instead of returning null;
    return null;
  }

  protected List<Partner> getPartners(){
    return new ArrayList(_partners);
  }

  protected List<Product> getProducts(){
    return new ArrayList(_products);
  }

  protected List<Batch> getBatches(){
    List<Batch> batches = new ArrayList();
    for (Product p : _products){
      batches.addAll(p.getBatches());
    }
    return batches;
  }

  protected int getBalance(){
    return (int) Math.round(_balance);
  }
  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
  }

}
