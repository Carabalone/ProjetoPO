package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.io.IOException;
import ggc.core.exception.BadEntryException;

import java.util.*;
import java.io.File;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  private Date _date = Date.now();
  private static int _nextTransactionId = 0;
  private TreeSet<Product> _products;
  private TreeSet<Partner> _partners;
  private List<Transaction> _transactions;
  private double _availableBalance;
  private double _accountingBalance;


  protected Warehouse(){
    _products = new TreeSet<Product>();
    _partners = new TreeSet<Partner>();
    _transactions = new ArrayList<Transaction>();
    _availableBalance = 0;
    _accountingBalance = 0;
  }

  protected List<Transaction> getTransactions(){
    return new ArrayList<>(_transactions);
  }

  protected static int getNextTransactionId(){
    return _nextTransactionId;
  }

  protected static void advanceTransactionId(){
    _nextTransactionId += 1;
  }

  protected static void setTransactionId(int id){
    _nextTransactionId = id;
  }

  protected void addTransaction(Transaction transaction){
    _transactions.add(transaction);
  }

  protected Product searchProduct(String id){
    for (Product p : _products){
      if (p.getId().equals(id))
        return p;
    }
    return null;
  }

  protected void addPartner(Partner partner){
    _partners.add(partner);
    for (Product p: _products){
      p.addObserver(partner);
    }
  }

  protected void addProduct(Product product){
    _products.add(product);
  }

  protected Partner getPartner(String id){
    for (Partner partner: _partners){
      if(partner.getId().compareToIgnoreCase(id) == 0){
        return partner;
      }
    }
    return null;
  }

  protected Set<Partner> getPartners(){
    return new TreeSet<Partner>(_partners);
  }

  protected Product getProduct(String id){
    for (Product product: _products){
      if(product.getId().compareToIgnoreCase(id) == 0)
        return product;
    }
    return null;
  }

  protected Set<Product> getProducts(){
    return new TreeSet<Product>(_products);
  }

  protected List<Batch> getBatches(){
    List<Batch> batches = new ArrayList<>();
    for (Product p : _products){
      batches.addAll(p.getBatches());
    }
    return batches;
  }

  protected int getAvailableBalance(){
    return (int) Math.round(_availableBalance);
  }

  protected int getAccountingBalance(){
    return (int) Math.round(_accountingBalance);
  }

  protected void removeFunds(double price){
    _accountingBalance -= price;
    _availableBalance -=price;
  }

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException{
    File impfile = new File(txtfile);
    Scanner sc = new Scanner(impfile);
    List<String> initialData = new ArrayList<>();

    while (sc.hasNextLine())
      initialData.add(sc.nextLine());

    for (String data : initialData) {
      String[] tokens = data.split("[|]");
      
      if (tokens[0].equals("PARTNER")){
        this.addPartner(new Partner(tokens[2], tokens[1], tokens[3]));
      }

      else {
        Product newProduct = this.searchProduct(tokens[1]);

        if (newProduct == null){

          if (tokens[0].equals("BATCH_S"))
            newProduct = new SimpleProduct(tokens[1], new TreeSet<Observer>(_partners));
          else
            newProduct = new DerivedProduct(tokens[1], new Recipe(tokens[6], Double.valueOf(tokens[5])),
                                                       new TreeSet<Observer>(_partners));
        }

          this.addProduct(newProduct);
          Batch btc = new Batch(Integer.valueOf(tokens[3]), Integer.valueOf(tokens[4]), newProduct, this.getPartner(tokens[2]));
          newProduct.addBatch(btc);
          getPartner(tokens[2]).addBatch(btc);
      }
    }
    // removing the notifications created by the load method
    for (Partner p: _partners){
      p.clearNotifications();
    }
  }
}
