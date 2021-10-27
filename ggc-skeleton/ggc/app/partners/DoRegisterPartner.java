package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> {

  DoRegisterPartner(WarehouseManager receiver) {
    super(Label.REGISTER_PARTNER, receiver);
    addStringField("id", Message.requestPartnerKey());
    addStringField("name", Message.requestPartnerName());
    addStringField("adress", Message.requestPartnerAddress()); 
  }

  @Override
  public void execute() throws CommandException {
    String id = stringField("id");
    String name = stringField("name");
    String adress = stringField("adress");
    _receiver.addPartner(id, name, adress);
  }

}
