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
  // FIXME define contructor(s)
  // FIXME define methods
  //TODO define exception
  protected Partner getPartner(String id){
    for (Partner partner: _partners){
      if(partner.getId() == id){
        return partner;
      }
    }
    //FIXME must throw exception instead of returning null;
    return null;
  }
  protected List<Partner> getPartners(){
    return _partners;
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
