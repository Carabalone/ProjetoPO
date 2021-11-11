package ggc.core;

//FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.nio.file.FileSystemNotFoundException;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.*;
import java.io.*;
import java.util.zip.*;

import ggc.core.exception.*;

/** Façade for access. */
public class WarehouseManager {

  /** Name of file storing current warehouse. */
  private String _filename = "";

  /** The warehouse itself. */
  private Warehouse _warehouse = new Warehouse();

  /* Variable that controls if any change has been made since last Save */
  private boolean _alteredSinceLastSave = false;

  public int displayDate(){
    return Date.showNow();
  }

  /*
  * @@throws InvalidDateInputException
  */
  public void advanceDate(int days) throws InvalidDateInputException{
    if (days < 0){
      throw new InvalidDateInputException(days);
    }
    Date.addNow(days);
    _alteredSinceLastSave = true;
  }

  public int displayGlobalBalance(){
    return _warehouse.getBalance();
  }

  public boolean productExists(String id){
    for (Product p : _warehouse.getProducts()){
      if (id.compareToIgnoreCase(p.getId()) == 0)
        return true;
    }
    return false;
  }

  public void addProduct(String id, String type){
    TreeSet<Observer> observers = new TreeSet<>(_warehouse.getPartners());
    if(type.equals("SIMPLE"))
      _warehouse.addProduct(new SimpleProduct(id, observers));
  }

  public void newBatch(double price, int amount, String productId, String supplierId) throws NoSuchPartnerException{
    Product pro = _warehouse.getProduct(productId);
    Partner sup = _warehouse.getPartner(supplierId);

    if (sup == null){
      throw new NoSuchPartnerException(supplierId);
    }

    Batch bat = new Batch(price, amount, pro, sup);
    pro.addBatch(bat);
    sup.addBatch(bat);
  }

  /*
  * @@throws DuplicatePartnerIdException
  */
  public void addPartner(String name, String id, String adress) throws DuplicatePartnerIdException{
    if (_warehouse.getPartner(id) != null){
      throw new DuplicatePartnerIdException(id);
    }
    Partner partner = new Partner(name, id, adress);
    _warehouse.addPartner(partner);
    _alteredSinceLastSave = true;
  }

  /*
  * @@throws NoSuchPartnerException
  */
  public String showPartner(String id) throws NoSuchPartnerException{
    return getPartner(id).toString();
  }

  public List<String> showPartners(){
    List<String> partnerStrings = new ArrayList<>();
    for (Partner partner : _warehouse.getPartners()) {
      partnerStrings.add(partner.toString());
    };
    return partnerStrings;
  }

  public Partner getPartner(String id) throws NoSuchPartnerException{
    Partner p = _warehouse.getPartner(id);
    if (p == null)
      throw new NoSuchPartnerException(id);
    return p;
  }

  public List<String> showNotificationStrings(String id) throws NoSuchPartnerException{
    List <String> NotificationStrings = new ArrayList<>();
    Partner p = _warehouse.getPartner(id);
    if(p == null)
      throw new NoSuchPartnerException(id);
    for (Notification n: p.getNotifications()){
      NotificationStrings.add(n.toString());
    }
    return NotificationStrings;
  }

  public List<String> showBatchesPartner(String id) throws NoSuchPartnerException{
    List<String> stringBatches = new ArrayList<>();
    Partner p = getPartner(id);
    for (Batch b : p.getBatches()) {
      stringBatches.add(b.toString());
    }
    return stringBatches;
  }

  public List<String> showBatchesProduct(String id){
    List<String> stringBatches = new ArrayList<>();
    Product p = _warehouse.getProduct(id);
    for (Batch b : p.getBatches())
      stringBatches.add(b.toString());
    return stringBatches;
  }

  public List<String> showAllProducts(){
    List<String> stringProducts = new ArrayList<>();
    for (Product pro : _warehouse.getProducts()) {
      stringProducts.add(pro.toString());
    }
    return stringProducts;
  }

  public List<String> showAllBatches(){
    List<String> stringBatches = new ArrayList<>();
    for (Batch bat : _warehouse.getBatches()) {
      stringBatches.add(bat.toString());
    }
    return stringBatches;
  }

  public List<String> showBatchesUnderGivenPrice(double price){
    List<String> stringBatches = new ArrayList<>();
    for (Batch b : _warehouse.getBatches()){
      if (b.getPrice() < price)
        stringBatches.add(b.toString());
    }
    return stringBatches;
  }

  public String getTransaction(int id) throws IndexOutOfBoundsException{
    return _warehouse.getTransactions().get(id).toString();
  }

  public void addAcquisition(String partner, String product, double price, int amount) throws NoSuchPartnerException{
    Partner prt = _warehouse.getPartner(partner);

    if (prt == null)
      throw new NoSuchPartnerException(partner);
    
    Acquisition acq = new Acquisition(_warehouse.getProduct(product), amount, price, prt);
    _warehouse.addTransaction(acq);
    prt.addAcquisition(acq);
}

  public void addSale(String partnerId, String productId, int deadline, int amount) throws NoSuchPartnerException{
    Partner partner = _warehouse.getPartner(partnerId);
    Product product = _warehouse.getProduct(productId);

    if (partner == null)
      throw new NoSuchPartnerException(partnerId);

    double value = product.gatherUnits(amount);

    Sale sale = new SaleByCredit(product, amount, new Date(deadline), partner, value);
    _warehouse.addTransaction(sale);
    partner.addSale(sale);
  }

  public int checkProductAvailability(String id) throws NoSuchProductException{
    Product product = _warehouse.getProduct(id);

    if (product == null)
      throw new NoSuchProductException(id);

    return product.checkQuantity();
  }

  public List<String> getPartnerAcquisitions(String id) throws NoSuchPartnerException{
    List<String> acquisitions = new ArrayList<>();
    Partner ptr = this.getPartner(id);
    for (Acquisition a: ptr.getAcquisitions()){
      acquisitions.add(String.format("COMPRA|%d|%s|%s|%d|%d|%d", a.getId(), ptr.getId(), 
                                                                a.getProduct().getId(),
                                                                a.getQuantity(), (int) a.getValue(),
                                                                 a.getPaymentDate()));
    }
    return acquisitions;

  }

  public boolean alteredSinceLastSave(){
    return _alteredSinceLastSave;
  }

  public String getFileName(){
    return new String(_filename);
  }

  /**
   * @@throws IOException
   * @@throws FileNotFoundException
   * @@throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    ObjectOutputStream obOut = null;
    try {
      FileOutputStream fpout = new FileOutputStream(_filename);
      DeflaterOutputStream dOut = new DeflaterOutputStream(fpout);
      obOut = new ObjectOutputStream(dOut);
      obOut.writeObject(_warehouse);
      obOut.writeObject((Integer) Date.showNow());
    } finally {
      if (obOut != null)
        obOut.close();
        _alteredSinceLastSave = false;
      }
  }

  /**
   * @@param filename
   * @@throws MissingFileAssociationException
   * @@throws IOException
   * @@throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @@param filename
   * @@throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException, UnavailableFileException, IOException  {
    ObjectInputStream obIn = null;
    try {
      FileInputStream fpin = new FileInputStream(filename);
      InflaterInputStream inflateIn = new InflaterInputStream(fpin);
      obIn = new ObjectInputStream(inflateIn);
      _warehouse = (Warehouse)obIn.readObject();
      int data = (Integer)obIn.readObject();
      Date.addNow(data);
      _filename = filename;

    } catch (FileNotFoundException ex){
      throw new UnavailableFileException(filename);
    } finally {
      if (obIn != null){
        obIn.close();
      }
      _alteredSinceLastSave = true;
    }
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException{
    try {
      _warehouse.importFile(textfile);
    }catch (IOException | BadEntryException e) {
      throw new ImportFileException(textfile, e);
    }
  }

}
