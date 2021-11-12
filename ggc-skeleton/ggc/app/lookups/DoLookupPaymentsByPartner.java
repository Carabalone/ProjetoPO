package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.NoSuchPartnerException;

import java.util.*;
//FIXME import classes

/**
 * Lookup payments by given partner.
 */
public class DoLookupPaymentsByPartner extends Command<WarehouseManager> {

  public DoLookupPaymentsByPartner(WarehouseManager receiver) {
    super(Label.PAID_BY_PARTNER, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String id = stringField("partnerId");
    List<String> payments;
    try{
      payments = new ArrayList<>(_receiver.showPaymentsByPartner(_receiver.getPartner(id)));
      _display.addAll(payments);
      _display.display();
    } catch (NoSuchPartnerException ex){
      throw new UnknownPartnerKeyException(ex.getId());
    }
  }

}
