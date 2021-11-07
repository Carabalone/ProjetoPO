package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.*;

import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.NoSuchPartnerException;

/**
 * Show all transactions for a specific partner.
 */
class DoShowPartnerAcquisitions extends Command<WarehouseManager> {

  DoShowPartnerAcquisitions(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER_ACQUISITIONS, receiver);
    addStringField("id", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String id = stringField("id");
    try{
      List<String> acquisitions = _receiver.getPartnerAcquisitions(id);
      for (String s: acquisitions){
        _display.addLine(s);
        _display.display();
      }
    } catch (NoSuchPartnerException ex){
      throw new UnknownPartnerKeyException(id);
    }
  }

}
