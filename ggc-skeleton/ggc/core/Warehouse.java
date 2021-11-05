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
  private int _nextTransactionId = 0;
  private TreeSet<Product> _products;
  private TreeSet<Partner> _partners;
  private List<Transaction> _transactions;
  private double _balance;


  protected Warehouse(){
    _products = new TreeSet();
    _partners = new TreeSet();
    _transactions = new ArrayList();
    _balance = 0;
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
  }

  protected void addProduct(Product product){
    _products.add(product);
  }

  protected void addTransaction(Transaction transaction){
    _transactions.add(transaction);
  }

  protected Partner getPartner(String id){
    for (Partner partner: _partners){
      if(partner.getId().equals(id)){
        return partner;
      }
    }
    return null;
  }

  protected TreeSet<Partner> getPartners(){
    return new TreeSet(_partners);
  }

  protected TreeSet<Product> getProducts(){
    return new TreeSet(_products);
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
  void importFile(String txtfile) throws IOException, BadEntryException{
    File impfile = new File(txtfile);
    Scanner sc = new Scanner(impfile);
    List<String> initialData = new ArrayList();

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
            newProduct = new SimpleProduct(tokens[1]);
          else
            newProduct = new DerivedProduct(tokens[1], new Recipe(tokens[6], Double.valueOf(tokens[5])));
        }

          this.addProduct(newProduct);
          newProduct.addBatch(new Batch(Integer.valueOf(tokens[3]), Integer.valueOf(tokens[4]), newProduct, this.getPartner(tokens[2])));
      }
    }
  }
}
