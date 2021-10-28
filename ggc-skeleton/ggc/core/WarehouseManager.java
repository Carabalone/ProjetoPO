package ggc.core;

//FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.*;

import ggc.app.exception.DuplicatePartnerKeyException;
import ggc.app.exception.InvalidDateException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.exception.BadEntryException;
import ggc.core.exception.DuplicatePartnerIdException;
import ggc.core.exception.ImportFileException;
import ggc.core.exception.InvalidDateInputException;
import ggc.core.exception.UnavailableFileException;
import ggc.core.exception.MissingFileAssociationException;
import ggc.core.exception.NoSuchPartnerException;

/** Façade for access. */
public class WarehouseManager {

  /** Name of file storing current warehouse. */
  private String _filename = "";

  /** The warehouse itself. */
  private Warehouse _warehouse = new Warehouse();

  //FIXME define other attributes
  //FIXME define constructor(s)
  //FIXME define other methods

  public int displayDate(){
    return Date.showNow();
  }

  public void advanceDate(int days) throws InvalidDateInputException{
    if (days < 0){
      throw new InvalidDateInputException(days);
    }
    Date.addNow(days);
  }

  public int displayGlobalBalance(){
    return _warehouse.getBalance();
  }

  public void addPartner(String name, String id, String adress) throws DuplicatePartnerIdException{
    if (_warehouse.getPartner(id) != null){
      throw new DuplicatePartnerIdException(id);
    }
    Partner partner = new Partner(name, id, adress);
    _warehouse.addPartner(partner);
  }

  public String showPartner(String id) throws NoSuchPartnerException{
    Partner p = _warehouse.getPartner(id);
    if (p == null){
      throw new NoSuchPartnerException(id);
    }
    return _warehouse.getPartner(id).toString();
  }

  public List<String> showPartners(){
    List partnerStrings = new ArrayList();
    for (Partner partner : _warehouse.getPartners()) {
      partnerStrings.add(partner.toString());
    };
    return partnerStrings;
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

  /**
   * @@throws IOException
   * @@throws FileNotFoundException
   * @@throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    //FIXME implement serialization method
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
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException  {
    //FIXME implement serialization method
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _warehouse.importFile(textfile);
    } catch (IOException | BadEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(textfile, e);
    }
  }

}
