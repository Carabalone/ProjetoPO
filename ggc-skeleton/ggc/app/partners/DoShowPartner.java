package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.NoSuchPartnerException;

import java.util.*;
/**
 * Show partner.
 */
class DoShowPartner extends Command<WarehouseManager> {

  DoShowPartner(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER, receiver);
    addStringField("id", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String id = stringField("id");
    List<String> partnerNotifications;
    try{
      _display.popup(_receiver.showPartner(id));
      partnerNotifications = _receiver.showNotificationStrings(id);
      _display.addAll(partnerNotifications);
      _display.display();
      
    } catch(NoSuchPartnerException ex){
      throw new UnknownPartnerKeyException(ex.getId()); 
    }
  }

}
