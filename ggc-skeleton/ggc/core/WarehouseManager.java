package ggc.core;

//FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.nio.file.FileSystemNotFoundException;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.*;
import java.io.*;
import java.util.zip.*;

import ggc.app.exception.DuplicatePartnerKeyException;
import ggc.app.exception.InvalidDateException;
import ggc.app.exception.UnknownPartnerKeyException;
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
    List partnerStrings = new ArrayList();
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

  public List<String> showBatchesPartner(String id) throws NoSuchPartnerException{
    List stringBatches = new ArrayList();
    Partner p = getPartner(id);
    for (Batch b : p.getBatches()) {
      stringBatches.add(b.toString());
    }
    return stringBatches;
  }

  public List<String> showBatchesProduct(String id) throws BadEntryException{
    List stringBatches = new ArrayList();
    Product p = _warehouse.getProduct(id);
    for (Batch b : p.getBatches())
      stringBatches.add(b.toString());
    return stringBatches;
  }

  public List<String> showAllProducts(){
    List stringProducts = new ArrayList();
    for (Product pro : _warehouse.getProducts()) {
      stringProducts.add(pro.toString());
    }
    return stringProducts;
  }

  public List<String> showAllBatches(){
    List stringBatches = new ArrayList();
    for (Batch bat : _warehouse.getBatches()) {
      stringBatches.add(bat.toString());
    }
    return stringBatches;
  }

  public List<String> showBatchesUnderGivenPrice(double price){
    List stringBatches = new ArrayList();
    for (Batch b : _warehouse.getBatches()){
      if (b.getPrice() < price)
        stringBatches.add(b.toString());
    }
    return stringBatches;
  }

  public String getTransaction(int id) throws IndexOutOfBoundsException{
    return _warehouse.getTransactions().get(id).toString();
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
