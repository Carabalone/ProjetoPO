package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.NoSuchPartnerException;

import java.util.*;
//FIXME import classes

/**
 * Show all transactions for a specific partner.
 */
class DoShowPartnerSales extends Command<WarehouseManager> {

  DoShowPartnerSales(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER_SALES, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String id = stringField("partnerId");
    List<String> sales;
    try{
      sales = new ArrayList<>(_receiver.showSalesByPartner(_receiver.getPartner(id)));
      _display.addAll(sales);
      _display.display();
    } catch (NoSuchPartnerException ex){
      throw new UnknownPartnerKeyException(ex.getId());
    }
  }

}
